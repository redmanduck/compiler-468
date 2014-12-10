/*
    This program is part of an assignment for ECE468 at Purdue University, IN.
    Copying, modifying or reusing this program may result in disciplinary actions.
    
    Copyright (C) 2014-2075 S. Sabpisal

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Hashtable;

public class TinyGenerator {
	IRList IR;
	HashMap<Instruction, Instruction[]> map_ISA;
	Hashtable<String, Register> reg_map_ir_tiny;
	LinkedHashMap<String, SymbolTable> SymbolTable_Map;

	private int LCSize, TmpSize;

	public static Register[] RegisterFile = new Register[Micro.CONST_NUM_REG_USE];

	private static int SAVE = 1;
	private static int RESTORE = 2;

	public TinyGenerator(IRList _irb, LinkedHashMap<String, SymbolTable> SMap) {
		this.IR = _irb;
		// generate IR -> asm map
		this.LCSize = 0;
		this.TmpSize = 15; // Assume constant Temp link size for simplicity!
							// TODO: fix later
		this.SymbolTable_Map = SMap; // Symboltable as indexed by function nmame
		map_ISA = new HashMap<Instruction, Instruction[]>();
		reg_map_ir_tiny = new Hashtable<String, Register>();
		loadIRMapping();

		// Allocate finite registers
		for (int i = 0; i < RegisterFile.length; i++) {
			Register R = new Register('r', i);
			R.clear();
			RegisterFile[i] = R;
		}
	}

	private void loadIRMapping() {
		map_ISA.put(ISA.ADDI, new Instruction[] { ISA.addi });
		map_ISA.put(ISA.ADDF, new Instruction[] { ISA.addr });
		map_ISA.put(ISA.SUBI, new Instruction[] { ISA.subi });
		map_ISA.put(ISA.SUBF, new Instruction[] { ISA.subr });
		map_ISA.put(ISA.WRITEI, new Instruction[] { ISA.sys_writei });
		map_ISA.put(ISA.WRITEF, new Instruction[] { ISA.sys_writer });
		map_ISA.put(ISA.WRITES, new Instruction[] { ISA.sys_writes });

		map_ISA.put(ISA.READI, new Instruction[] { ISA.sys_readi });
		map_ISA.put(ISA.READF, new Instruction[] { ISA.sys_readr });

		map_ISA.put(ISA.STOREI, new Instruction[] { ISA.move });
		map_ISA.put(ISA.STOREF, new Instruction[] { ISA.move });
		map_ISA.put(ISA.MULTI, new Instruction[] { ISA.muli });
		map_ISA.put(ISA.MULTF, new Instruction[] { ISA.mulr });
		map_ISA.put(ISA.DIVI, new Instruction[] { ISA.divi });
		map_ISA.put(ISA.DIVF, new Instruction[] { ISA.divr });

		map_ISA.put(ISA.GEI, new Instruction[] { ISA.cmpi, ISA.jge });
		map_ISA.put(ISA.GEF, new Instruction[] { ISA.cmpr, ISA.jge });
		map_ISA.put(ISA.LEI, new Instruction[] { ISA.cmpi, ISA.jle });
		map_ISA.put(ISA.LEF, new Instruction[] { ISA.cmpr, ISA.jle });

		map_ISA.put(ISA.LTI, new Instruction[] { ISA.cmpi, ISA.jlt }); // what
																		// about
																		// LEI?
		map_ISA.put(ISA.GTI, new Instruction[] { ISA.cmpi, ISA.jgt });

		map_ISA.put(ISA.EQI, new Instruction[] { ISA.cmpi, ISA.jeq });

		map_ISA.put(ISA.NEI, new Instruction[] { ISA.cmpi, ISA.jne });

		map_ISA.put(ISA.PUSH, new Instruction[] { ISA.push });
		map_ISA.put(ISA.PUSH_E, new Instruction[] { ISA.push });
		map_ISA.put(ISA.POP, new Instruction[] { ISA.pop });
		map_ISA.put(ISA.POP_E, new Instruction[] { ISA.pop });

		map_ISA.put(ISA.JSR, new Instruction[] { ISA.jsr });
		map_ISA.put(ISA.JUMP, new Instruction[] { ISA.jmp }); // unconditional
																// jump
		map_ISA.put(ISA.RET, new Instruction[] { ISA.unlnk, ISA.ret });
		map_ISA.put(ISA.LINK, new Instruction[] { ISA.link });
		map_ISA.put(ISA.LABEL, new Instruction[] { ISA.label });
	}

	public String translate() {
		StringBuffer code = new StringBuffer();

		for (IRNode n : IR) {
			String S = generate_asm(n);
			code.append(S);
		}

		return (code.toString());
	}

	private String getField(String st, int i) {
		String[] s = st.split(" ");
		return s[i];
	}

	private String doLink(Instruction tiny, IRNode irn) {
		TinyActivationRecord.reset(); // reset stack count etc
		TempRegisterFactory.reset();

		TinyActivationRecord.saveRegisters(Micro.CONST_NUM_REG_USE);

		LCSize = SymbolTable_Map.get(irn.fn_key).count_local();
		int paramsize = IR.getParamCount();

		TinyActivationRecord.initParamLocal(paramsize, LCSize);

		if (SymbolTable_Map.containsKey(irn.fn_key)) {
			return tiny.getName() + " " + (LCSize + TmpSize);
		}

		return tiny.getName() + " " + 0;
	}

	/*
	 * Generate assembly given an IR Node
	 */
	private String generate_asm(IRNode irn) {

		TinyOutputBuffer CodeBuffer = new TinyOutputBuffer();
		CodeBuffer.add(";------------------" + irn.toString() + " ----------------");
		CodeBuffer.add(";  " + Utils.printRegisters() + "\n");
		Instruction irx = irn.getInstruction();
		Instruction[] possible_instructions = map_ISA.get(irx);

		String ircode = irn.toString();

		if (possible_instructions == null) {
			System.err.println(";No Instruction Mapping is defined for "
					+ irx.getName());
			return null;
		}

		Instruction tinyOp = possible_instructions[0];
		if (tinyOp == ISA.__skip)
			return null;
		Instruction IROp = irn.getInstruction();

		System.out
				.println(";-------------------------------------------------------");
		System.out.println("; ir node " + ircode + "!" + " (FRMT-"
				+ irn.getFormat() + ")");
		System.out.println("; reg state : " + Utils.printRegisters());

		if (irn.getInstruction().equals(ISA.RET)) {
			// generate store dirty for registers
			flushRegisters(CodeBuffer, irn.LIVE_OUT);
		}

		if (ISA.InstructionSpecies(irn.getInstruction(), ISA._NONJSRJUMP)){
			spillRegisters(CodeBuffer, irn.LIVE_OUT);
		}
		
		if (irn.getFormat() == IRNode.FORMAT_D) {
			/*
			 * Could be sys calls : WRITE (use) READ (def)
			 */

			String VAR = getField(ircode, 1);

			if (ISA.InstructionSpecies(IROp, ISA._WRITE)) {
				// USE VAR
				// if VAR already in register use it
				// if VAR is not in register bring it in and then use it

				Id Vid = SymbolTable_Map.get("main").search(VAR);
				if (Vid != null && Vid.getType().equals("STRING")) {
					/*
					 * We can write directly to memory
					 */
					CodeBuffer.add(tinyOp.getName() + " " + VAR);
				} else {
					Register R_use = ensure(VAR, irn.LIVE_OUT, CodeBuffer);
					/*
					 * WRITE $P1: move M[$P1] rx ; gen above sys write* rx ; gen
					 * below
					 */

					CodeBuffer.add(tinyOp.getName() + " " + R_use.toTiny());
				}

			} else if (ISA.InstructionSpecies(IROp, ISA._READ)) {
				// DEF VAR
				Register R_def = ensure(VAR, irn.LIVE_OUT, CodeBuffer);
				/*
				 * READ helloworld; move M[helloworld] rx; sys read rx;
				 */
				R_def.markDirty();
				CodeBuffer.add(tinyOp.getName() + " " + R_def.toTiny());

			} else if (irn.getInstruction().equals(ISA.PUSH)) {

				Register R_use = ensure(VAR, irn.LIVE_OUT, CodeBuffer);
				CodeBuffer.add(tinyOp.getName() + " " + R_use.toTiny());

			} else if (irn.getInstruction().equals(ISA.POP)) {
				Register R_pop = ensure(VAR, irn.LIVE_OUT, CodeBuffer);
				R_pop.markDirty(); // TODO : how to automatically Mark Dirty
				CodeBuffer.add(tinyOp.getName() + " " + R_pop.toTiny());
			} else {
				// OTHER possibilites ** not sure
				System.err.println(ircode);
				System.err.println("Instruction not supported. Aborting");
				System.exit(1);
			}

		} else if (irn.getFormat() == IRNode.FORMAT_DD) {

			/*
			 * Note we cannot do move id1, id2 , we have to do move id1, r1 move
			 * r1, id2
			 */

			String IdSRC1 = getField(ircode, 1);
			String IdSRC2 = getField(ircode, 2);
	
			Register src = ensure(IdSRC1, irn.LIVE_OUT, CodeBuffer); // this
																		// will
																		// do
																		// the
																		// first
																		// move
																		// (load)
																		// into
																		// some
																		// r
			Register dest = ensure(IdSRC2, irn.LIVE_OUT, CodeBuffer);

			dest.markDirty();
			String asms = tinyOp.getName() + " " + src.toTiny() + " "
					+ dest.toTiny();

			CodeBuffer.add(asms);

		} else if (irn.getFormat() == IRNode.FORMAT_IR
				|| irn.getFormat() == IRNode.FORMAT_FR) {
			/*
			 * Literals to register *move* ops
			 */

			String RDest = getField(ircode, 2);
			String literal = getField(ircode, 1);
			Register dest = ensure(RDest, irn.LIVE_OUT, CodeBuffer); // TempRegisterFactory.createTiny();

			reg_map_ir_tiny.put(getField(ircode, 2), dest); // TODO: To be
															// removed
			dest.markDirty();
			String Z = tinyOp.getName() + " " + literal + " " + dest.toTiny();
			CodeBuffer.add(Z);
		} else if (irn.getFormat() == IRNode.FORMAT_RD) {
			/*
			 * Single Step Movement STORE $T8 duck
			 */

			String R = getField(ircode, 1);
			String D = getField(ircode, 2);

			Register Src = ensure(R, irn.LIVE_OUT, CodeBuffer);
			Register Dest = ensure(D, irn.LIVE_OUT, CodeBuffer);
			Dest.markDirty();
			String Z = tinyOp.getName() + " " + Src.toTiny() + " "
					+ Dest.toTiny();
			CodeBuffer.add(Z);

			// return tinyOp.getName() + " " +
			// TempRegisterFactory.previous().toTiny() + " " +
			// irn.getIdOperand(3).getTiny();

			if(!irn.LIVE_OUT.contains(Src)){
				free(Src, irn.LIVE_OUT, CodeBuffer);
			}

		} else if (irn.getFormat() == IRNode.FORMAT_DDR) {

			/*
			 * OP Id Id Reg may expands into: MOV a T2 --ensure MOV b T3
			 * --ensure Add T2 T3 --OP MOV Reg R --ensure MOV T3 R --MOVE
			 */

			String IdSrc1 = getField(ircode, 1);
			String IdSrc2 = getField(ircode, 2);
			String TDest = getField(ircode, 3);

			Register A = ensure(IdSrc1, irn.LIVE_OUT, CodeBuffer);
			Register B = ensure(IdSrc2, irn.LIVE_OUT, CodeBuffer);
			CodeBuffer.add(tinyOp.getName() + " " + A.toTiny() + " "
					+ B.toTiny());
			Register C = ensure(TDest, irn.LIVE_OUT, CodeBuffer);
			C.markDirty();
			CodeBuffer.add(ISA.move.getName() + " " + B.toTiny() + " "
					+ C.toTiny());

			if(!irn.LIVE_OUT.contains(B)){
				free(C, irn.LIVE_OUT, CodeBuffer);
			}
			if(!irn.LIVE_OUT.contains(A)){
				free(A, irn.LIVE_OUT, CodeBuffer);
			}

		} else if (irn.getFormat() == IRNode.FORMAT_RRR) {

			/*
			 * OP R1 R2 RD may expands into: MOV r1 rtemp OP r2 rD
			 */

			String T1 = getField(ircode, 1);
			String T2 = getField(ircode, 2);
			String T3 = getField(ircode, 3);

			Register A = ensure(T1, irn.LIVE_OUT, CodeBuffer);
			Register B = ensure(T2, irn.LIVE_OUT, CodeBuffer);
			CodeBuffer.add(tinyOp.getName() + " " + A.toTiny() + " "
					+ B.toTiny());
			Register C = ensure(T3, irn.LIVE_OUT, CodeBuffer);
			C.markDirty();
			CodeBuffer.add(ISA.move.getName() + " " + B.toTiny() + " "
					+ C.toTiny());

			if(!irn.LIVE_OUT.contains(B)){
				free(C, irn.LIVE_OUT, CodeBuffer);
			}
			if(!irn.LIVE_OUT.contains(A)){
				free(A, irn.LIVE_OUT, CodeBuffer);
			}

		} else if (irn.getFormat() == IRNode.FORMAT_RDR) {
			/*
			 * OP R1 ID RZ may expands into : MOV r1 rtemp OP rtemp rZ
			 */


			String T1 = getField(ircode, 1);
			String T2 = getField(ircode, 2);
			String T3 = getField(ircode, 3);

			Register A = ensure(T1, irn.LIVE_OUT, CodeBuffer);
			Register B = ensure(T2, irn.LIVE_OUT, CodeBuffer);
			CodeBuffer.add(tinyOp.getName() + " " + A.toTiny() + " " + B.toTiny());
			Register C = ensure(T3, irn.LIVE_OUT, CodeBuffer);
			C.markDirty();
			CodeBuffer.add(ISA.move.getName() + " " + B.toTiny() + " "
					+ C.toTiny());

			if(!irn.LIVE_OUT.contains(B)){
				free(C, irn.LIVE_OUT, CodeBuffer);
			}
			if(!irn.LIVE_OUT.contains(A)){
				free(A, irn.LIVE_OUT, CodeBuffer);
			}


		} else if (irn.getFormat() == IRNode.FORMAT_DRR) {

			/*
			 * OP ID R2 RZ may expands into: MOV ID rtemp OP r2 rZ
			 */
			

			String T1 = getField(ircode, 1);
			String T2 = getField(ircode, 2);
			String T3 = getField(ircode, 3);

			Register A = ensure(T1, irn.LIVE_OUT, CodeBuffer);
			Register B = ensure(T2, irn.LIVE_OUT, CodeBuffer);
			
			CodeBuffer.add(tinyOp.getName() + " " + B.toTiny() + " " + A.toTiny());

			Register C = ensure(T3, irn.LIVE_OUT, CodeBuffer);
			C.markDirty();

			CodeBuffer.add(ISA.move.getName() + " " + A.toTiny() + " " + C.toTiny());


			if(!irn.LIVE_OUT.contains(B)){
				free(C, irn.LIVE_OUT, CodeBuffer);
			}
			if(!irn.LIVE_OUT.contains(A)){
				free(A, irn.LIVE_OUT, CodeBuffer);
			}

		} else if (irn.getFormat() == IRNode.FORMAT_O) {
			/*
			 * Other ops
			 */

			// handle link separately
			if (irn.getInstruction().equals(ISA.LINK)) {
				return doLink(tinyOp, irn) + "\n";
			} else if (irn.getInstruction().equals(ISA.PUSH_E)) {
				this.flushRegisters(CodeBuffer, irn.LIVE_IN);
			}

			String str = "";
			for (int i = 0; i < possible_instructions.length; i++) {
				if (i > 0)
					str += "\n";
				str += possible_instructions[i].getName();
			}
			CodeBuffer.add(str);



		} else if (irn.getFormat() == IRNode.FORMAT_S) {
			/*
			 * S-type ? Wtf is ths
			 */
			CodeBuffer.add(tinyOp.getName() + " " + getField(ircode, 1));

		} else if (irn.getFormat() == IRNode.FORMAT_DRT) {
			/*
			 * DRT Jump target
			 */

			String f1 = getField(ircode, 1);
			String f2 = getField(ircode, 2);


			//TODO: in some case its not doing ensure correctly or something
			Register T1 = ensure(f1, irn.LIVE_OUT, CodeBuffer);
			Register T2 = ensure(f2, irn.LIVE_OUT, CodeBuffer);

			String asms = possible_instructions[0].getName() + " " + T1.toTiny() + " " + T2.toTiny() + "\n";
			asms += possible_instructions[1].getName() + " " + getField(ircode, 3);

			CodeBuffer.add(asms);

		} else if (irn.getFormat() == IRNode.FORMAT_DDT) {
			/*
			 * DDT Jump Target
			 */
			String f1 = getField(ircode, 1);
			String f2 = getField(ircode, 2);

			Register T1 = ensure(f1, irn.LIVE_OUT, CodeBuffer);
			Register T2 = ensure(f2, irn.LIVE_OUT, CodeBuffer);

			String asms = possible_instructions[0].getName() + " "
					+ T1.toTiny() + " " + T2.toTiny() + "\n";
			asms += possible_instructions[1].getName() + " "
					+ getField(ircode, 3);

			CodeBuffer.add(asms);

		} else if (irn.getFormat() == IRNode.FORMAT_T) {
			/*
			 * Unconditional JUMP TARGETS
			 */
			String output = "";
			output += tinyOp.getName() + " " + getField(ircode, 1) + "\n";

			if (irn.getInstruction().equals(ISA.JSR)) {
				output = save_regs() + "\n" + output;
				output += restore_regs();
			}
			CodeBuffer.add(output);

		} else if (irn.getFormat() == IRNode.FORMAT_DR) {
			/*
			 * Single motion moving Id to register
			 */


			String IdSRC1 = getField(ircode, 1);
			String IdSRC2 = getField(ircode, 2);

			Register src = ensure(IdSRC1, irn.LIVE_OUT, CodeBuffer);
			Register dest = ensure(IdSRC2, irn.LIVE_OUT, CodeBuffer);
			dest.markDirty();
			String asms = tinyOp.getName() + " " + src.toTiny() + " "
					+ dest.toTiny();

			CodeBuffer.add(asms);

		} else if (irn.getFormat() == IRNode.FORMAT_R) {
			/*
			 * This could be PUSH (use) or POP (def)
			 */


			String C = getField(ircode, 1);

			if (irn.getInstruction().equals(ISA.PUSH)) {

				Register R_use = ensure(C, irn.LIVE_OUT, CodeBuffer);
				CodeBuffer.add(tinyOp.getName() + " " + R_use.toTiny());

			} else if (irn.getInstruction().equals(ISA.POP)) {
				Register R_use = ensure(C, irn.LIVE_OUT, CodeBuffer);
				R_use.markDirty();
				CodeBuffer.add(tinyOp.getName() + " " + R_use.toTiny());
			} else {
				System.err.println("Abort! Unknown operation for FORMAT_R : "
						+ ircode);
				System.exit(1);
			}

		} else if (irn.getFormat() == IRNode.FORMAT_RS) {
			// `S` is not really a register
			Register R = ensure(getField(ircode, 1), irn.LIVE_OUT, CodeBuffer);
			String generate = tinyOp.getName() + " " + R.toTiny() + " $"
					+ TinyActivationRecord.getReturnStackAddress();
			CodeBuffer.add(generate);
		} else {
			/*
			 * Unhandled cases
			 */
			CodeBuffer.add(";<unknown format> code: " + irn.getFormat());
			System.err.println("Unknown Format Error");
			System.exit(1);
		}

		

		/*
		 * Finally print the generated code sequence
		 */
		
		CodeBuffer.add(";  " + Utils.printRegisters());

		String outputc = "";
		for (String strc : CodeBuffer) {
			outputc += strc;
		}
		return outputc;
	}

	/*
	 * 
	 * Save or Restore Register pop push
	 * 
	 * @param save_or_restore - int
	 */
	private String save_restore_register(int save_or_restore) {
		/*
		 * for step6, there is no register allocation yet so we'll just hard
		 * code this
		 */
		StringBuffer sbuffer = new StringBuffer();
		int i = 0;
		int offset = 0;
		Instruction pp = ISA.push;

		if (save_or_restore == RESTORE) {
			offset = 0;
			pp = ISA.pop;
		}

		for (i = 0; i < Micro.CONST_NUM_REG_USE; i++) {
			if (save_or_restore == SAVE) {
				offset = sbuffer.length();
			}
			String eol = "";
			if (i != (save_or_restore == RESTORE ? 0
					: Micro.CONST_NUM_REG_USE - 1))
				eol = "\n";
			sbuffer.insert(offset,
					String.format("%s r%d%s", pp.getName(), i, eol));

		}
		return sbuffer.toString();
	}

	private String save_regs() {
		return save_restore_register(SAVE);
	}

	private String restore_regs() {
		return save_restore_register(RESTORE);
	}

	/*
	 * Ensure
	 */
	private Register ensure(String opr, HashSet<String> liveness,
			TinyOutputBuffer G) {
		// ensure(a) --> load a rx
		// ensure($T1) --> load $(Scope_LC+T#) rx
		// ensure($L1) --> load $1 rx
		// ensure($P1) --> load $-1 rx

		String mem_name = TinyActivationRecord.getStackRef(opr, LCSize);

		System.out.println("; attempting to ensure " + opr);
		Register r = Utils.varInRegister(opr);
		if (r != null) {
			System.out.println("; ensuring " + opr + " use " + r.toTiny());
			return r;
		} else {

			r = allocate(opr, liveness, G);
			if (Micro.TINYGEN_VERBOSE)
				System.out.println("; ensuring " + opr + " @(" + mem_name
						+ ") " + " gets " + r.toTiny());

			String load_cmd = String.format("%s %s %s ; load ensure %s\n",
					ISA.move.getName(), mem_name, r.toTiny(), opr);

			G.add(load_cmd);
			return r;
		}
	}

	/*
	 * @param opr - $Tx $Lx $Px or global var
	 */
	private Register allocate(String opr, HashSet<String> liveness,
			TinyOutputBuffer G) {
		System.out.println("; Attempting to allocate " + opr);
		Register r = Utils.varInRegister(opr);

		// if r is not in Register
		if (r == null) {
			r = Utils.getFreeReg();
			if (r == null) {
				// there is no free r
				// choose r by most distant use

				r = Utils.getMostDistantUsedReg();

				System.out
						.println("; No free register! Choosing most distant used reg : "
								+ r.toTiny());

				free(r, liveness, G);
			}
		}

		System.out.println("; allocating " + opr + "@("
				+ TinyActivationRecord.getStackRef(opr, LCSize) + ") to "
				+ r.toTiny());
		r.occupy(opr);

		return r;
	}

	private void free(Register r, HashSet<String> liveness, TinyOutputBuffer G) {
		String variable = r.opr;
		String gen_cmd = "";
		System.out.println("; evicting " + r.toTiny() + " for opr " + r.opr);
		Boolean isAlive = Utils.varIsLive(variable, liveness);
		System.out.println("; free: " + r.isFree() + ", dirty:" + r.isDirty()
				+ ", live: " + isAlive);
		if (r.isDirty()) {
			// generate store (spill out)
			System.out.println("; spilling " + r.toTiny());
			gen_cmd = String.format("%s %s %s ; spill %s\n",
					ISA.move.getName(), r.toTiny(),
					TinyActivationRecord.getStackRef(variable, LCSize), r.opr);
		}
		r.clear();
		G.add(gen_cmd);
	}

	private void flushRegisters(TinyOutputBuffer G, HashSet<String> L) {
		G.add("\n;Flushing registers\n");

		for (int j = 0; j < RegisterFile.length; j++) {
			Boolean isAlive = Utils.varIsLive(RegisterFile[j].opr, L); // TODO:
																		// isAlive
																		// and
																		// with
																		// isDirty
			if (RegisterFile[j].isDirty()) {
				String memloc = TinyActivationRecord.getStackRef(
						RegisterFile[j].opr, LCSize);
				G.add(ISA.move.getName() + " r" + j + " " + memloc + "\n");
				RegisterFile[j].clear();
			}

		}

		G.add(";Flush done\n");
	}

	private void  spillRegisters(TinyOutputBuffer CodeBuffer, HashSet<String> L ){
		for(int j = 0; j < RegisterFile.length; j++){
			if (RegisterFile[j].isDirty()) {
				String memloc = TinyActivationRecord.getStackRef(
						RegisterFile[j].opr, LCSize);
				CodeBuffer.add(ISA.move.getName() + " r" + j + " " + memloc + " ; spilling \n");
				RegisterFile[j].markClean();
			}
		}
	}
}

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
public class TinyGenerator {
	IRList IR;
	HashMap<Instruction, Instruction[]> map_ISA;
	Hashtable<String, Register> reg_map_ir_tiny;
	LinkedHashSet<Id> usedSymbols; //contains all LVALUES
	LinkedHashMap<String, SymbolTable> SymbolTable_Map;
	
	public static Register[] RegisterFile = new Register[Micro.CONST_NUM_REG_USE];
	
	private static int SAVE = 1;
	private static int RESTORE = 2;

	public TinyGenerator(IRList _irb, LinkedHashMap<String, SymbolTable> SMap) {
		IR = _irb;
		//generate IR -> asm map
		this.SymbolTable_Map = SMap;
		map_ISA = new HashMap<Instruction, Instruction[]>(); 
		reg_map_ir_tiny = new Hashtable<String, Register>();
		usedSymbols = new LinkedHashSet<Id>(); //these are bunch of used stuff 
		loadIRMapping();
		//allocate finite registers
		for(int i = 0; i < RegisterFile.length ; i++){
			Register R = new Register('r', i);
			R.free = true;
			RegisterFile[i] = R;
		}
	}
	
	private void loadIRMapping(){
		map_ISA.put(ISA.ADDI, new Instruction []{ISA.addi});
		map_ISA.put(ISA.ADDF, new Instruction []{ISA.addr});
		map_ISA.put(ISA.SUBI, new Instruction []{ISA.subi});
		map_ISA.put(ISA.SUBF, new Instruction []{ISA.subr});
		map_ISA.put(ISA.WRITEI, new Instruction []{ISA.sys_writei});
		map_ISA.put(ISA.WRITEF, new Instruction []{ISA.sys_writer});
		map_ISA.put(ISA.WRITES, new Instruction []{ISA.sys_writes});

		map_ISA.put(ISA.READI, new Instruction []{ISA.sys_readi});
		map_ISA.put(ISA.READF, new Instruction []{ISA.sys_readr});

		map_ISA.put(ISA.STOREI, new Instruction []{ISA.move});
		map_ISA.put(ISA.STOREF, new Instruction []{ISA.move});
		map_ISA.put(ISA.MULTI, new Instruction []{ISA.muli});
		map_ISA.put(ISA.MULTF, new Instruction []{ISA.mulr});
		map_ISA.put(ISA.DIVI, new Instruction []{ISA.divi});
		map_ISA.put(ISA.DIVF, new Instruction []{ISA.divr});
		
		map_ISA.put(ISA.GEI, new Instruction [] {ISA.cmpi, ISA.jge});
		map_ISA.put(ISA.GEF, new Instruction [] {ISA.cmpr, ISA.jge});
		map_ISA.put(ISA.LEI, new Instruction [] {ISA.cmpi, ISA.jle});
		map_ISA.put(ISA.LEF, new Instruction [] {ISA.cmpr, ISA.jle});

		map_ISA.put(ISA.LTI, new Instruction [] {ISA.cmpi, ISA.jlt}); //what about LEI?
		map_ISA.put(ISA.GTI, new Instruction [] {ISA.cmpi, ISA.jgt});

		map_ISA.put(ISA.EQI, new Instruction [] {ISA.cmpi, ISA.jeq});
		
		map_ISA.put(ISA.NEI, new Instruction [] {ISA.cmpi, ISA.jne});
	
		
		map_ISA.put(ISA.PUSH, new Instruction [] {ISA.push});
		map_ISA.put(ISA.PUSH_E, new Instruction [] {ISA.push});
		map_ISA.put(ISA.POP, new Instruction [] {ISA.pop});
		map_ISA.put(ISA.POP_E, new Instruction [] {ISA.pop});

		map_ISA.put(ISA.JSR, new Instruction [] {ISA.jsr});
		map_ISA.put(ISA.JUMP, new Instruction [] {ISA.jmp}); //unconditional jump
		map_ISA.put(ISA.RET, new Instruction[]{ISA.unlnk, ISA.ret});
		map_ISA.put(ISA.LINK, new Instruction[]{ISA.link});
		map_ISA.put(ISA.LABEL, new Instruction []{ISA.label});
	}

	public String translate() {
		StringBuffer code = new StringBuffer();

		for(IRNode n : IR){
			String S = generate_asm(n);
			if(S == null) continue;
			code.append(S + "\n");
		}
		
		return (code.toString());
	}

	private String getField(String st, int i){
		String [] s = st.split(" ");
		return s[i];
	}

	private boolean varAlive(String var, HashSet<String> liveness){
		System.out.println("; Checking Liveness " + var);
		System.out.println("; live out: " + liveness.toString());
		//primary checking
		if(liveness.contains(var)){
			System.out.println("; " + var + " is live");
			return true;
		}
		//secondary checking
		//need to do this because, the liveness set are referred by IR Name 
		//but IR allocation written here only know about tiny name
		//so we need to reverse lookup the IR-Tiny Conversion Map
		String rn = TinyActivationRecord.getReverseName(var.substring(1));
		System.out.print("; map bijection search result: ");
		System.out.println(rn);
		if(rn == null){
			System.out.println("; " + var + " is dead");
			return false;
		}

		//check liveness again
		if(liveness.contains(var)){
			System.out.println("; " + var + " is live");
			return true;
		}
		System.out.println("; " + var + " is dead");
		
		return false;
	}
	
	private String getRegistersState(){
		String longtext = "{";
		for(int i = 0; i < RegisterFile.length; i++){
			longtext += "r" + i + "=" + RegisterFile[i].opr + ( RegisterFile[i].dirty ? "*" : "") + ", ";
		}
		longtext += "}";
		return longtext;
	}

	/*
	 * Do bottom up register allocation
	 * and code generation
	 */
	private String generate_asm(IRNode irn){	
		
		
		
		Instruction irx = irn.getInstruction();
		Instruction [] possible_instructions = map_ISA.get(irx);
		
		String ircode = irn.toString();
		System.out.println(";-------------------------------------------------------");
		System.out.println("; ir node " + ircode + "!" + " (FRMT-" + irn.getFormat() +  ")");
		System.out.println("; reg state : " + getRegistersState() );
		
		TinyOutputBuffer Generated = new TinyOutputBuffer();
		
		if(possible_instructions == null){
			System.err.println(";No Instruction Mapping is defined for " + irx.getName());
			return null;
		}		
		
		Instruction tiny = possible_instructions[0]; //first 
		if(tiny == ISA.__skip) return null;
		
		if(irn.getFormat() == IRNode.FORMAT_D){
			//these includes sys calls etc
			
			Register reg = null;
			String X = irn.getIdOperand(4).getTiny();
			
			Statement stmt = null;

			//these includes sys calls etc
			if(irn.getInstruction().equals(ISA.WRITEF) || irn.getInstruction().equals(ISA.WRITEI)){
				//this is a USE
				stmt = ensure(X, irn.LIVE_OUT);
				reg = stmt.return_reg;
				Generated.add(stmt.generated_asm);
				
				String temp = tiny.getName() + " " + reg.toTiny();
				Generated.add(temp);
				
			}else if(irn.getInstruction().equals(ISA.READF) || irn.getInstruction().equals(ISA.READI)){
				//this is a DEF
				stmt = allocate(X, irn.LIVE_OUT); 
				reg = stmt.return_reg;
				reg.dirty = true;
				Generated.add(stmt.generated_asm);
				
				String temp = tiny.getName() + " " + reg.toTiny();
				Generated.add(temp);
				
			}else{
				String temp = tiny.getName() + " " + irn.getIdOperand(4).getTiny();
				Generated.add(temp);
			}
			
			usedSymbols.add(irn.getIdOperand(4));
			
		//	return tiny.getName() + " " + irn.getIdOperand(4).getTiny();
			
		}else if(irn.getFormat() == IRNode.FORMAT_DD){
			
			//Note we cannot do move id1, id2 , we have to do
			//move id1, r1
			//move r1, id2
			
			String A = irn.getIdOperand(IRNode.OP_ID_SRC1).getTiny();
			String C = irn.getIdOperand(IRNode.OP_ID_DEST).getTiny();
			
			Statement stmt_en = ensure(A, irn.LIVE_OUT); 
			Register Rx = stmt_en.return_reg;
			Generated.add(stmt_en.generated_asm);
			
			//TODO: do if dead free check
			if(!varAlive(A, irn.LIVE_OUT)){
				Generated.add( free(Rx, irn.LIVE_OUT));
			}
			
			Statement stmt_alloc = allocate(C, irn.LIVE_OUT);
			Register Rz = stmt_alloc.return_reg;
			Rz.dirty = true;
			usedSymbols.add(irn.getIdOperand(3)); //destination
			
			Register dest = Rz;
			
			String C2 = irn.getIdOperand(3).getTiny();
			Statement stmt_C2 = allocate(C2, irn.LIVE_OUT);
			Register Rz2 = stmt_C2.return_reg;
			Generated.add(stmt_C2.generated_asm);
			Rz2.dirty = true;
			String asms = ISA.move.getName() + " " + irn.getIdOperand(1).getTiny() +  " " + dest.toTiny() + "\n";
			asms += tiny.getName() + " " + dest.toTiny() + " " + Rz2.toTiny();
			
			Generated.add(asms);
			
		}else if(irn.getFormat() == IRNode.FORMAT_IR || irn.getFormat() == IRNode.FORMAT_FR){
			
			String C = getField(ircode, 2);
			Statement deststmt = allocate(C, irn.LIVE_OUT) ; 
			Generated.add(deststmt.generated_asm);
			Register dest = deststmt.return_reg;
			dest.dirty = true;
			
			reg_map_ir_tiny.put(C, dest);

			Generated.add(tiny.getName() + " " + getField(ircode, 1) + " " + dest.toTiny());
			
		}else if(irn.getFormat() == IRNode.FORMAT_RD){
			usedSymbols.add(irn.getIdOperand(3));

			String A = getField(ircode, 1);
			Statement stmt_ensure = this.ensure(A, irn.LIVE_OUT); 
			Generated.add(stmt_ensure.generated_asm);
			Register Rx = stmt_ensure.return_reg;
			
			//TODO: do if dead free check
			if(!varAlive(A, irn.LIVE_OUT)){ Generated.add(free(Rx, irn.LIVE_OUT)); }

			
			String C = irn.getIdOperand(3).getTiny();
			Statement deststmt = allocate(C, irn.LIVE_OUT) ; 
			Generated.add(deststmt.generated_asm);
			Register Rz = deststmt.return_reg;
			Rz.dirty = true;

			String output = tiny.getName() + " " + Rx.toTiny() + " " + Rz.toTiny();
			Generated.add(output);
			
		}else if(irn.getFormat() == IRNode.FORMAT_DDR){

			String move_op = ISA.move.getName();
//			Register reg = TempRegisterFactory.allocate_tiny();
			
			Id d2 = irn.getIdOperand(2);
			Id d1 = irn.getIdOperand(1);
			
			usedSymbols.add(d2);
			usedSymbols.add(d1);
			
			Statement EN1 = this.ensure(d1.getTiny(), irn.LIVE_OUT);  //d1.toString()
			Statement EN2 = this.ensure(d2.getTiny(), irn.LIVE_OUT);  //d2.toString()
			
			Generated.add(EN1.generated_asm);
			Generated.add(EN2.generated_asm);
			Register Rx = EN1.return_reg;
			Register Ry = EN2.return_reg;
			
			//TODO: do if dead free check
			if(!varAlive(d1.getTiny(), irn.LIVE_OUT)){ 
				Generated.add(free(Rx, irn.LIVE_OUT)); 
				
			}
			if(!varAlive(d2.getTiny(), irn.LIVE_OUT)){ 
				Generated.add(free(Ry, irn.LIVE_OUT));
			}

			Statement AL = allocate(this.getField(ircode, 3), irn.LIVE_OUT);
			Register Rz = AL.return_reg;
			Rz.dirty = true;
			Generated.add(AL.generated_asm);
			
			reg_map_ir_tiny.put(getField(ircode, 3), Rz);
	
			String asms = move_op + " " + Rx.toTiny() + " " + Rz.toTiny() + "\n";
			asms += tiny.getName() + " " +  Ry.toTiny() + " " + Rz.toTiny();

			Generated.add(asms);
			
		}else if(irn.getFormat() == IRNode.FORMAT_RRR){
			//TODO: Unverified case
			String move_op = ISA.move.getName();

			Statement stmt_r1 = this.ensure(getField(ircode, 1), irn.LIVE_OUT); 
			Statement stmt_r2 = this.ensure(getField(ircode, 2), irn.LIVE_OUT); 
			Statement stmt_r3 = allocate(this.getField(ircode, 3), irn.LIVE_OUT);
			
			Generated.add(stmt_r1.generated_asm);
			Generated.add(stmt_r2.generated_asm);
			Generated.add(stmt_r3.generated_asm);
			
			Register Rx = stmt_r1.return_reg;
			Register Ry = stmt_r2.return_reg;
			Register Rz = stmt_r3.return_reg; //TempRegisterFactory.allocate_tiny(); //: use Rz
			Rz.dirty = true;
			
			
			if(!varAlive(getField(ircode, 1), irn.LIVE_OUT)){ 
				Generated.add(free(Rx, irn.LIVE_OUT));
			}
			if(!varAlive(getField(ircode, 2), irn.LIVE_OUT)){ 
				Generated.add(free(Ry, irn.LIVE_OUT)); 
			}

			
			reg_map_ir_tiny.put(getField(ircode, 3), Rz);

			
			String asms = move_op + " " + Rx.toTiny() + " " + Rz.toTiny() + "\n";
			asms += tiny.getName() + " " +  Ry.toTiny() + " " + Rz.toTiny();

			Generated.add(asms);
		}else if(irn.getFormat() == IRNode.FORMAT_RDR){
			//TODO: Unverified case

			String move_op = ISA.move.getName();
			
			Id d = irn.getIdOperand(2);
			usedSymbols.add(d);
			
			
			Statement stmt_r1 = this.ensure(getField(ircode, 1), irn.LIVE_OUT); 
			Statement stmt_d2 = this.ensure(irn.getIdOperand(IRNode.OP_ID_SRC2).getTiny(), irn.LIVE_OUT); 
			Statement stmt_r3 = this.allocate(getField(ircode, 3), irn.LIVE_OUT); 

			Generated.add(stmt_r1.generated_asm);
			Generated.add(stmt_d2.generated_asm);
			Generated.add(stmt_r3.generated_asm);
			
			Register Rx = stmt_r1.return_reg;
			Register Ry = stmt_d2.return_reg;
			Register Rz = stmt_r3.return_reg;
			Rz.dirty = true;
			reg_map_ir_tiny.put(getField(ircode, 3), Rz);
			
			if(!varAlive(getField(ircode, 1), irn.LIVE_OUT)){ 
				Generated.add(free(Rx, irn.LIVE_OUT));
			}
			if(!varAlive(irn.getIdOperand(IRNode.OP_ID_SRC2).getTiny()
					, irn.LIVE_OUT)){ 
				Generated.add(free(Ry, irn.LIVE_OUT)); 
			}


			String asms = move_op + " " + Rx.toTiny() + " " + Rz.toTiny() + "\n";
			asms += tiny.getName() + " " + Ry.toTiny()  + " " + Rz.toTiny();
			
			Generated.add(asms);
		}else if(irn.getFormat() == IRNode.FORMAT_DRR){
			
			
			Statement stmt_d1 = this.ensure(irn.getIdOperand(IRNode.OP_ID_SRC1).getTiny(), irn.LIVE_OUT); 
			Statement stmt_r2 = this.ensure(getField(ircode, 2), irn.LIVE_OUT); 
			Statement stmt_r3 = this.allocate(getField(ircode, 3), irn.LIVE_OUT); 
			
			Generated.add(stmt_d1.generated_asm);
			Generated.add(stmt_r2.generated_asm);
			Generated.add(stmt_r3.generated_asm);

			String move_op = ISA.move.getName();

			Register Rx = stmt_d1.return_reg;
			Register Ry = stmt_r2.return_reg;
			Register Rz = stmt_r3.return_reg;
			Rz.dirty = true;
			
			reg_map_ir_tiny.put(getField(ircode, 3), Rz);
			usedSymbols.add(irn.getIdOperand(1));
			
			if(!varAlive(irn.getIdOperand(IRNode.OP_ID_SRC1).getTiny(), irn.LIVE_OUT)){
				Generated.add(free(Rx, irn.LIVE_OUT)); 
			}
			if(!varAlive(getField(ircode, 2),irn.LIVE_OUT)){ 
				Generated.add(free(Ry, irn.LIVE_OUT)); 
			}

			
			String asms = move_op + " " + Rx.toTiny() + " " + Rz.toTiny() + "\n";
			asms += tiny.getName() + " " + Ry.toTiny() + " " + Rz.toTiny();
			
			Generated.add(asms);
			
		}else if(irn.getFormat() == IRNode.FORMAT_O){
			
			//handle link separately 
			if(irn.getInstruction().equals(ISA.LINK)){
				return doLink(tiny, irn);
			}else if(irn.getInstruction().equals(ISA.PUSH_E)){
				this.flushDirty(Generated);
			}
			String str = "";
			for(int i = 0; i < possible_instructions.length ; i++){
				if(i > 0) str += "\n";
				str += possible_instructions[i].getName();
			}

			Generated.add(str);
			
		}else if(irn.getFormat() == IRNode.FORMAT_S){

			String output = tiny.getName() + " " + getField(ircode, 1);
			Generated.add(output);
		}else if(irn.getFormat() == IRNode.FORMAT_DRT){
			
			String asms =  possible_instructions[0].getName() + " " + irn.getIdOperand(1).getTiny() + " " +
					reg_map_ir_tiny.get(getField(ircode,2)).toTiny() + "\n";
			asms += possible_instructions[1].getName() + " " + getField(ircode, 3);
			Generated.add(asms);
		}else if(irn.getFormat() == IRNode.FORMAT_DDT){
			//TODO: incomplete 
			
			Statement stmt_d1 = this.ensure(irn.getIdOperand(IRNode.OP_ID_SRC1).getTiny(), irn.LIVE_OUT); 
			Statement stmt_d2 = this.ensure(irn.getIdOperand(IRNode.OP_ID_SRC2).getTiny(), irn.LIVE_OUT); 
			
			Generated.add(stmt_d1.generated_asm);
			Generated.add(stmt_d2.generated_asm);
			
			Register d1 = stmt_d1.return_reg;
			Register d2 = stmt_d2.return_reg;
			
			Statement stmt_tempdest = this.allocate("$Tx", irn.LIVE_OUT); //this doesn't matter, its going to be killed immediately

			Register tmpdest = stmt_tempdest.return_reg; //TempRegisterFactory.allocate_tiny(); //FIX this
			tmpdest.dirty = false;
			tmpdest.free = true; //become free immediately
			
			String target=  getField(ircode, 3);
			String asms = ISA.move.getName() + " " + d2.toTiny() + " " + tmpdest.toTiny()  + "\n";
			
		    asms += possible_instructions[0].getName() + " " +  d1.toTiny() +
					" " + tmpdest.toTiny() + "\n";
			asms += possible_instructions[1].getName() + " " + target;
			
			Generated.add(asms);
			
		}else if(irn.getFormat() == IRNode.FORMAT_T){
			String buff = "";
			buff += tiny.getName() +  " " + getField(ircode, 1) + "\n";
			
			//function call
			if(irn.getInstruction().equals(ISA.JSR)){
				buff = save_regs() + "\n" + buff;
				buff += restore_regs();
			}
			Generated.add(buff);

		}else if(irn.getFormat() == IRNode.FORMAT_DR){
			
			Statement D_STMT = this.ensure(irn.getIdOperand(IRNode.OP_ID_SRC1).getTiny(), irn.LIVE_OUT); 
			
			Generated.add(D_STMT.generated_asm);
			Register Rx = D_STMT.return_reg;
			
			//TODO: do if dead free check
			if(!varAlive(irn.getIdOperand(IRNode.OP_ID_SRC1).getTiny(), irn.LIVE_OUT)){
				Generated.add(free(Rx, irn.LIVE_OUT)); 
			}

			Statement R_STMT = this.allocate(getField(ircode,2 ), irn.LIVE_OUT);
			Register Rz = R_STMT.return_reg;
			Rz.dirty = true;
			Generated.add(R_STMT.generated_asm);
			
			reg_map_ir_tiny.put(getField(ircode, 2), Rz);

			String output= tiny.getName() + " " + Rx.toTiny() + " " + Rz.toTiny();
			Generated.add(output);
			
			
		}else if(irn.getFormat() == IRNode.FORMAT_R){
			
			String C = getField(ircode, 1);
			
			if(irn.getInstruction().equals(ISA.PUSH)){
				Statement use = ensure(C, irn.LIVE_OUT); 
				
				Generated.add(use.generated_asm);
				Register Rx = use.return_reg;
				
				Generated.add(tiny.getName() +  " " + Rx.toTiny());

			}else if(irn.getInstruction().equals(ISA.POP)){
				Statement deststmt = allocate(C, irn.LIVE_OUT); 
				
				Generated.add(deststmt.generated_asm);
				Register Rz = deststmt.return_reg;
				Rz.dirty = true;
				
				Generated.add(tiny.getName() +  " " + Rz.toTiny());
			}else{
				System.err.println("Unknown operation for FORMAT_R : " + ircode);
			}
			
		}else if(irn.getFormat() == IRNode.FORMAT_RS){
			//`S` is not really a register, its $R
			this.ensure(getField(ircode, 1), irn.LIVE_OUT); 

			String generate = tiny.getName() + " " + reg_map_ir_tiny.get(getField(ircode, 1)).toTiny() + " $" +
					TinyActivationRecord.getReturnStackAddress();
			Generated.add(generate);
		}else{
			Generated.add( ";<unknown format> code: " + irn.getFormat());
			System.err.println("Unknown Format Error");
		}
		
		if(irn.getInstruction().equals(ISA.RET)){
			//generate  store dirty for registers
			flushDirty(Generated);

		}
		
		
		String outputc = "";
		for(String k : Generated){
			outputc += k;
		}
		return outputc;
	}
	
	private void flushDirty(TinyOutputBuffer Generated){
		Generated.add("\n;flushing registers\n");
		for(int j = 0; j< RegisterFile.length; j++){
			if(RegisterFile[j].dirty){ 
				//TODO:  (a) hold local/global variable condition as well
				String memloc = RegisterFile[j].opr;
				if(memloc.contains("$T")) continue;
				Generated.add(ISA.move.getName() + " r" + j + " " + memloc + "\n");
			}
		}
		Generated.add(";flush done\n");
	}

	private String doLink(Instruction tiny, IRNode irn) {
		TinyActivationRecord.reset(); //reset stack count etc 
		TempRegisterFactory.reset();
		
		TinyActivationRecord.saveRegisters(Micro.CONST_NUM_REG_USE);
		
		if(SymbolTable_Map.containsKey(irn.fn_key)){
			return tiny.getName() + " " + SymbolTable_Map.get(irn.fn_key).count_local();
		}
		return tiny.getName() + " " + 0;
	}
	
	
	private String save_restore_register(int save_or_restore){
		/*
		 * for step6, there is no register allocation yet so we'll just hard code this
		 */
		StringBuffer sbuffer = new StringBuffer();
		int i;
		int offset = 0;
		Instruction pp = ISA.push;
		
		if(save_or_restore == RESTORE){
			offset = 0;
			pp = ISA.pop;
		}
		
		for(i = 0; i< Micro.CONST_NUM_REG_USE; i++){
			if(save_or_restore == SAVE){
				offset = sbuffer.length();
			}
			String eol = "";
			if(i != (save_or_restore == RESTORE ? 0 :  Micro.CONST_NUM_REG_USE - 1)) eol = "\n";
			sbuffer.insert(offset, String.format("%s r%d%s", pp.getName(), i, eol));
			
		}
		return sbuffer.toString();
	}
	
	public Register in_register(String opr){
		for(int i =0; i<RegisterFile.length; i++){
			if(RegisterFile[i].opr.equals(opr)){
				System.out.println("; found " + opr + " in r" + i);
				return RegisterFile[i];
			}
		}
		System.out.println("; " + opr + ": not loaded in any register..");
		return null;
	}
	
	private Statement ensure(String opr, HashSet<String> liveness){
		System.out.println("; attempting to ensure " + opr);
		Register r = in_register(opr);
		if(r != null){
			System.out.println("; ensuring " + opr + " use " + r.toTiny());
			return new Statement(null, r);
		}else{
			
			r = allocate(opr, liveness).return_reg;
			if(Micro.TINYGEN_VERBOSE) System.out.println("; ensuring " + opr + " gets " + r.toTiny());

			//generate load 
			String load_cmd = String.format("%s %s %s \n",
					ISA.move.getName(),
					opr, 
					r.toTiny());
			return new Statement(load_cmd, r);
		}
	}
	
	private String free(Register r, HashSet<String> liveness){
		String variable = r.opr;
		String gen_cmd = "";
		System.out.println("; evicting " + r.toTiny() + " for opr " + r.opr);
		Boolean isAlive = varAlive(variable, liveness);
		System.out.println("; free: " + r.free + ", dirty:" + r.dirty + ", live: " + isAlive);
		if(r.dirty && isAlive){
			//generate store
			System.out.println("; spilling " + r.toTiny());
			gen_cmd = String.format("%s %s %s ; spill\n", ISA.move.getName(),
					r.toTiny(),
					r.opr);
		}
		r.free = true;
		r.opr = "nothing";
		r.dirty = false;
		return gen_cmd;
	}
	
	private Statement allocate(String opr, HashSet<String> liveness){
		System.out.println("; Attempting to allocate " + opr);
		//waiting for response on piazza 
		Register r = in_register(opr);
//		if(r!=null) return new Statement("", r);
		
		r = getFreeReg();
		if(r == null){
			//there is no free r
			//choose r by most distant use
			System.out.println("; No free register!");
			r = mostDistantUsedReg();
			
			free(r, liveness);
		}
		
		System.out.println("; allocating " + opr + " to " + r.toTiny());
		
		r.opr = opr;
		r.free = false;
		r.updateTimestamp();
		
		return new Statement(null, r);
	}
	
	
	private Register mostDistantUsedReg(){
		Register min = RegisterFile[0];
		System.out.print("; Most distant used {");
		for(int r = 0 ; r < RegisterFile.length; r++){
			System.out.print("r" + r + "=" + RegisterFile[r].getTimestamp() + ",");
			if(RegisterFile[r].getTimestamp() < min.getTimestamp()){
				min = RegisterFile[r];
			}
		}
		System.out.print("}\n");
		System.out.println("; Chose " + min.toTiny());
		return min;
	}
	
	private Register getFreeReg(){
		for(int i =0; i<RegisterFile.length; i++){
			if(RegisterFile[i].free){
				return RegisterFile[i];
			}
		}
		return null; //no free Register
	}
	
	private String save_regs(){
		return save_restore_register(SAVE);
	}
	
	private String restore_regs(){
		return save_restore_register(RESTORE);
	}
	
	class Statement{
		public String generated_asm;
		public Register return_reg;
		public Statement(String asm, Register reg){
			if(asm == null){
				generated_asm = "";
			}else{
				generated_asm = asm;
			}
			return_reg =reg;
			
		}
	}
	
		
	
}

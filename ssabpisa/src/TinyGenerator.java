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
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Hashtable;
public class TinyGenerator {
	IRList IR;
	HashMap<Instruction, Instruction[]> map_ISA;
	Hashtable<String, Register> reg_map_ir_tiny;
	LinkedHashSet<Id> usedSymbols; //contains all LVALUES
	LinkedHashMap<String, SymbolTable> SymbolTable_Map;
	
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

	private String generate_asm(IRNode irn){	
		Instruction irx = irn.getInstruction();
		Instruction [] possible_instructions = map_ISA.get(irx);
		
		String ircode = irn.toString();
		
		if(possible_instructions == null){
			System.err.println(";No Instruction Mapping is defined for " + irx.getName());
			return null;
		}		
		
		Instruction tiny = possible_instructions[0]; //first 
		if(tiny == ISA.__skip) return null;
		
		if(irn.getFormat() == IRNode.FORMAT_D){
			
			usedSymbols.add(irn.getIdOperand(4));
			return tiny.getName() + " " + irn.getIdOperand(4).getTiny();
		}else if(irn.getFormat() == IRNode.FORMAT_DD){
			
			//Note we cannot do move id1, id2 , we have to do
			//move id1, r1
			//move r1, id2

			usedSymbols.add(irn.getIdOperand(3));
			Register dest = TempRegisterFactory.allocate_tiny();

			String asms = ISA.move.getName() + " " + irn.getIdOperand(1).getTiny() +  " " + dest.toTiny() + "\n";
			asms += tiny.getName() + " " + dest.toTiny() + " " + irn.getIdOperand(3).getTiny();
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_IR || irn.getFormat() == IRNode.FORMAT_FR){
			
			Register dest = TempRegisterFactory.allocate_tiny();
			reg_map_ir_tiny.put(getField(ircode,2), dest);
			
			return tiny.getName() + " " + getField(ircode, 1) + " " + dest.toTiny();
			
		}else if(irn.getFormat() == IRNode.FORMAT_RD){
			usedSymbols.add(irn.getIdOperand(3));

			return tiny.getName() + " " + TempRegisterFactory.previous().toTiny() + " " + irn.getIdOperand(3).getTiny();
			
		}else if(irn.getFormat() == IRNode.FORMAT_DDR){

			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.allocate_tiny();
			reg_map_ir_tiny.put(getField(ircode, 3), reg);
			
			Id d2 = irn.getIdOperand(2);
			Id d1 = irn.getIdOperand(1);
			
			usedSymbols.add(d2);
			usedSymbols.add(d1);
			
			String asms = move_op + " " + d1.getTiny() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " +  d2.getTiny() + " " + reg.toTiny();
			
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_RRR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.allocate_tiny();
			reg_map_ir_tiny.put(getField(ircode, 3), reg);
			
			String asms = move_op + " " + reg_map_ir_tiny.get(getField(ircode, 1)).toTiny() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " +  reg_map_ir_tiny.get(getField(ircode, 2)).toTiny() + " " + reg.toTiny();
			
			return asms;
		}else if(irn.getFormat() == IRNode.FORMAT_RDR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.allocate_tiny();
			reg_map_ir_tiny.put(getField(ircode, 3), reg);
			
			Id d = irn.getIdOperand(2);
			usedSymbols.add(d);
			String asms = move_op + " " + reg_map_ir_tiny.get(getField(ircode, 1)).toTiny() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " + d.getTiny()  + " " + reg.toTiny();
			
			return asms;
		}else if(irn.getFormat() == IRNode.FORMAT_DRR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.allocate_tiny();
			reg_map_ir_tiny.put(getField(ircode, 3), reg);
			usedSymbols.add(irn.getIdOperand(1));
			String asms = move_op + " " + irn.getIdOperand(1).getTiny() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " + reg_map_ir_tiny.get(getField(ircode, 2)).toTiny() + " " + reg.toTiny();
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_O){
			//handle link separately 
			if(irn.getInstruction().equals(ISA.LINK)){
				return doLink(tiny, irn);
			}
			String str = "";
			for(int i = 0; i < possible_instructions.length ; i++){
				if(i > 0) str += "\n";
				str += possible_instructions[i].getName();
			}
			return str;
			
		}else if(irn.getFormat() == IRNode.FORMAT_S){
			
			return tiny.getName() + " " + getField(ircode, 1);
		}else if(irn.getFormat() == IRNode.FORMAT_DRT){
			
			String asms =  possible_instructions[0].getName() + " " + irn.getIdOperand(1).getTiny() + " " + reg_map_ir_tiny.get(getField(ircode,2)).toTiny() + "\n";
			asms += possible_instructions[1].getName() + " " + getField(ircode, 3);
			return asms;	
		}else if(irn.getFormat() == IRNode.FORMAT_DDT){
			
			Register dest = TempRegisterFactory.allocate_tiny();
			
			String asms = ISA.move.getName() + " " + irn.getIdOperand(2).getTiny() + " " + dest.toTiny()  + "\n";
			
		    asms += possible_instructions[0].getName() + " " +  irn.getIdOperand(1).getTiny() + " " + dest.toTiny() + "\n";
			asms += possible_instructions[1].getName() + " " + getField(ircode, 3);
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_T){
			String output = "";
			output += tiny.getName() +  " " + getField(ircode, 1) + "\n";
			
			if(irn.getInstruction().equals(ISA.JSR)){
				output = save_regs() + "\n" + output; 
				output += restore_regs();
			}
			return output;
			
		}else if(irn.getFormat() == IRNode.FORMAT_DR){
			
			Register dest = TempRegisterFactory.allocate_tiny();
			reg_map_ir_tiny.put(getField(ircode, 2), dest);

			return tiny.getName() + " " + irn.getIdOperand(1).getTiny() + " " + dest.toTiny();
		}else if(irn.getFormat() == IRNode.FORMAT_R){
			Register dest = null;
		//	
			if(reg_map_ir_tiny.containsKey(getField(ircode, 1))){
				dest = reg_map_ir_tiny.get(getField(ircode, 1));
			}else{
				dest = TempRegisterFactory.allocate_tiny();
				reg_map_ir_tiny.put(getField(ircode, 1), dest);
			}
			
			return tiny.getName() +  " " + dest.toTiny();
		}else if(irn.getFormat() == IRNode.FORMAT_RS){
			//`S` is not really a register
			String generate = tiny.getName() + " " + reg_map_ir_tiny.get(getField(ircode, 1)).toTiny() + " $" + TinyActivationRecord.getReturnStackAddress();
			return generate;
		}else{
			return ";<unknown format> code: " + irn.getFormat();
		}
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
		int i = 0;
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
	
	private String save_regs(){
		return save_restore_register(SAVE);
	}
	
	private String restore_regs(){
		return save_restore_register(RESTORE);
	}
	
}

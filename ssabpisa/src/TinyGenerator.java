import java.util.LinkedHashSet;
import java.util.Hashtable;
public class TinyGenerator {
	IRCollection IR;
	Hashtable<Instruction, Instruction> map_ISA;
	Hashtable<String, Register> relations_TinyIRDests;
	LinkedHashSet<Id> usedSymbols; //contains all LVALUES
	
	public TinyGenerator(IRCollection _irb) {
		IR = _irb;
		//generate IR -> asm map
		map_ISA = new Hashtable<Instruction, Instruction>(); 
		relations_TinyIRDests = new Hashtable<String, Register>();
		usedSymbols = new LinkedHashSet<Id>();
		
		map_ISA.put(ISA.ADDI, ISA.addi);
		map_ISA.put(ISA.ADDF, ISA.addr);
		map_ISA.put(ISA.SUBI, ISA.subi);
		map_ISA.put(ISA.SUBF, ISA.subr);
		map_ISA.put(ISA.WRITEI, ISA.sys_writei);
		map_ISA.put(ISA.WRITEF, ISA.sys_writer);
		map_ISA.put(ISA.WRITES, ISA.sys_writes);

		map_ISA.put(ISA.READI, ISA.sys_readi);
		map_ISA.put(ISA.READF, ISA.sys_readr);

		map_ISA.put(ISA.STOREI, ISA.move);
		map_ISA.put(ISA.STOREF, ISA.move);
		map_ISA.put(ISA.MULTI, ISA.muli);
		map_ISA.put(ISA.MULTF, ISA.mulr);
		map_ISA.put(ISA.DIVI, ISA.divi);
		map_ISA.put(ISA.DIVF, ISA.divr);

		//mapping.put(ISA.LINK, ISA.link);
		//mapping.put(ISA.LABEL, ISA.label);

	}

	public void printAll() {
		StringBuffer code = new StringBuffer();
		
		
		for(IRNode n : IR){
			String S = generate_asm(n);
			if(S == null) continue;
			code.append(S + "\n");
		}
		int vardec_offsets = 0;
		for(Id symbol: usedSymbols){
			String s =  String.format("var %s\n", symbol.name);
			if(symbol.type == "STRING"){
				s = String.format("str %s %s\n", symbol.name, symbol.value);
			}
			code.insert(vardec_offsets, s);
			vardec_offsets += s.length();
		}
		code.append(ISA.sys_halt.getName() + "\n");
		code.insert(0, ";tiny code\n");
		System.out.print(code.toString());
	}

	private String getField(String st, int i){
		String [] s = st.split(" ");
		return s[i];
	}

	private String generate_asm(IRNode irn){	
		Instruction irx = irn.getInstruction();
		Instruction tiny = map_ISA.get(irx);
		String ircode = irn.toString();
		
		if(tiny == null){
			return null;
		}		
		
		
		if(irn.getFormat() == IRNode.FORMAT_D){
			
			usedSymbols.add(irn.getIdOperand(4));
			return tiny.getName() + " " + irn.getIdOperand(4).name;
		}else if(irn.getFormat() == IRNode.FORMAT_DD){
			
			usedSymbols.add(irn.getIdOperand(3));
			return tiny.getName() + " " + irn.getIdOperand(1).name + " " + irn.getIdOperand(3).name;
			
		}else if(irn.getFormat() == IRNode.FORMAT_IR || irn.getFormat() == IRNode.FORMAT_FR){
			
			Register dest = TempRegisterFactory.createTiny();
			relations_TinyIRDests.put(getField(ircode,2), dest);
			
			return tiny.getName() + " " + getField(ircode, 1) + " " + dest.toTiny();
			
		}else if(irn.getFormat() == IRNode.FORMAT_RD){
			usedSymbols.add(irn.getIdOperand(3));

			return tiny.getName() + " " + TempRegisterFactory.previous().toTiny() + " " + irn.getIdOperand(3).name;
			
		}else if(irn.getFormat() == IRNode.FORMAT_DDR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			relations_TinyIRDests.put(getField(ircode, 3), reg);
			
			Id d2 = irn.getIdOperand(2);
			Id d1 = irn.getIdOperand(1);
			
			usedSymbols.add(d2);
			usedSymbols.add(d1);
			
			String asms = move_op + " " + d1.name + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " +  d2.name + " " + reg.toTiny();
			
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_RRR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			relations_TinyIRDests.put(getField(ircode, 3), reg);
			
			String asms = move_op + " " + relations_TinyIRDests.get(getField(ircode, 1)).toTiny() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " +  relations_TinyIRDests.get(getField(ircode, 2)).toTiny() + " " + reg.toTiny();
			
			return asms;
		}else if(irn.getFormat() == IRNode.FORMAT_RDR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			relations_TinyIRDests.put(getField(ircode, 3), reg);
			
			Id d = irn.getIdOperand(2);
			usedSymbols.add(d);
			String asms = move_op + " " + relations_TinyIRDests.get(getField(ircode, 1)).toTiny() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " + d.name  + " " + reg.toTiny();
			
			return asms;
		}else if(irn.getFormat() == IRNode.FORMAT_DRR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			relations_TinyIRDests.put(getField(ircode, 3), reg);
			usedSymbols.add(irn.getIdOperand(1));
			String asms = move_op + " " + irn.getIdOperand(1).name + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " + relations_TinyIRDests.get(getField(ircode, 2)).toTiny() + " " + reg.toTiny();
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_O){
			
			return tiny.getName();
			
		}else if(irn.getFormat() == IRNode.FORMAT_S){
			
			return tiny.getName() + " " + getField(ircode, 1);
			
		}else{
			return "<unknown format>" + irn.getFormat();
		}
	}
	
	
	
}

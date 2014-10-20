import java.util.Hashtable;
public class TinyGenerator {
	IRBase IR;
	Hashtable<Instruction, Instruction> mapping;
	Hashtable<String, Register> irdest_tinydest;
	
	public TinyGenerator(IRBase _irb) {
		IR = _irb;
		//generate IR -> asm map
		mapping = new Hashtable<Instruction, Instruction>(); 
		irdest_tinydest = new Hashtable<String, Register>();
		mapping.put(ISA.ADDI, ISA.addi);
		mapping.put(ISA.ADDF, ISA.addr);
		mapping.put(ISA.SUBI, ISA.subi);
		mapping.put(ISA.SUBF, ISA.subr);
		mapping.put(ISA.WRITEI, ISA.sys_writei);
		mapping.put(ISA.WRITEF, ISA.sys_writer);
		mapping.put(ISA.WRITES, ISA.sys_writer);

		mapping.put(ISA.READI, ISA.sys_readi);
		mapping.put(ISA.READF, ISA.sys_readr);

		mapping.put(ISA.STOREI, ISA.move);
		mapping.put(ISA.STOREF, ISA.move);
		mapping.put(ISA.MULTI, ISA.muli);
		mapping.put(ISA.MULTF, ISA.mulr);
		mapping.put(ISA.DIVI, ISA.divi);
		mapping.put(ISA.DIVF, ISA.divr);

		mapping.put(ISA.LINK, ISA.link);
		mapping.put(ISA.LABEL, ISA.label);

	}

	public void printAll() {
		System.out.println(";tiny code");
		for(IRNode n : IR){
			System.out.println(generate_asm(n));
		}
	}

	private String getField(String st, int i){
		String [] s = st.split(" ");
		return s[i];
	}

	private String generate_asm(IRNode irn){	
		Instruction irx = irn.getInstruction();
		Instruction tiny = mapping.get(irx);
		String ircode = irn.toString();
		
		if(tiny == null){
			return "<what the duck>" + ircode;
		}		
		
		if(irn.getFormat() == IRNode.FORMAT_D){
			
			return tiny.getName() + " " + getField(ircode, 1);
			
		}else if(irn.getFormat() == IRNode.FORMAT_IR || irn.getFormat() == IRNode.FORMAT_FR){
			
			Register dest = TempRegisterFactory.createTiny();
			irdest_tinydest.put(getField(ircode,2), dest);
			
			return tiny.getName() + " " + getField(ircode, 1) + " " + dest.toTiny();
			
		}else if(irn.getFormat() == IRNode.FORMAT_RD){
			
			return tiny.getName() + " " + TempRegisterFactory.previous().toTiny() + " " + getField(ircode, 2);
			
		}else if(irn.getFormat() == IRNode.FORMAT_DDR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			irdest_tinydest.put(getField(ircode, 3), reg);
			
			String d2 = irn.getIdOperand(2).name;
			String d1 = irn.getIdOperand(1).name;

			String asms = move_op + " " + d1 + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " +  d2 + " " + reg.toTiny();
			
			return asms;
			
		}else if(irn.getFormat() == IRNode.FORMAT_RRR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			irdest_tinydest.put(getField(ircode, 3), reg);
			
			String asms = move_op + " " + irdest_tinydest.get(getField(ircode, 1)).toTiny() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " +  irdest_tinydest.get(getField(ircode, 2)).toTiny() + " " + reg.toTiny();
			
			return asms;
		}else if(irn.getFormat() == IRNode.FORMAT_RDR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			irdest_tinydest.put(getField(ircode, 3), reg);
			
			String d = irn.getIdOperand(2).name;
			String asms = move_op + " " + irdest_tinydest.get(getField(ircode, 1)).toTiny() + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " + d  + " " + reg.toTiny();
			
			return asms;
		}else if(irn.getFormat() == IRNode.FORMAT_DRR){
			
			String move_op = ISA.move.getName();
			Register reg = TempRegisterFactory.createTiny();
			irdest_tinydest.put(getField(ircode, 3), reg);
	
			String asms = move_op + " " + irn.getIdOperand(1).name + " " + reg.toTiny() + "\n";
			asms += tiny.getName() + " " + irdest_tinydest.get(getField(ircode, 2)).toTiny() + " " + reg.toTiny();
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

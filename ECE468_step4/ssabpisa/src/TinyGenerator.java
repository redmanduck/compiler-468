public class TinyGenerator {
	IRBase IR;
	public TinyGenerator(IRBase _irb) {
		IR = _irb;
	}

	public void printAll() {
		System.out.println(";tiny code");
		for(IRNode n : IR){
			System.out.println(generate_asm(n));
		}
	}

	private String generate_asm(IRNode nd){
		Instruction x = nd.getInstruction();
		if(x.equals(ISA.ADDI)){
			return ISA.addi.getName() + " opmrl reg";
		}else if(x.equals(ISA.ADDF)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.SUBI)){
			return ISA.subi.getName() + " opmrl reg";
		}else if(x.equals(ISA.SUBF)){
			return ISA.subr.getName() + " opmrl reg";
		}else if(x.equals(ISA.MULTI)){
			return ISA.muli.getName() + " opmrl reg";
		}else if(x.equals(ISA.MULTF)){
			return ISA.mulr.getName() + " opmrl reg";
		}else if(x.equals(ISA.DIVI)){
			return ISA.divi.getName() + " opmrl reg";
		}else if(x.equals(ISA.DIVF)){
			return ISA.divr.getName() + " opmrl reg";
		}else if(x.equals(ISA.STOREI)){
			return ISA.move.getName() + " opmrl reg";
		}else if(x.equals(ISA.STOREF)){
			return ISA.move.getName() + " opmrl reg";
		}else if(x.equals(ISA.GT)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.GE)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.NE)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.EQ)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.LT)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.JUMP)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.LABEL)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.READI)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.READF)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.WRITEI)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.WRITEF)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.WRITES)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.RET)){
			return ISA.addr.getName() + " opmrl reg";
		}else if(x.equals(ISA.LINK)){
			return ISA.addr.getName() + " opmrl reg";
		}

		return "BAD1BAD1";
	}
	
	
	
}

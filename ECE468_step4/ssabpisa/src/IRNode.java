public class IRNode {
     private Instruction OPCODE;
     private Register r_dest, r_src1, r_src2;
     private int i_src1;
     private Id id_dest, id_src1, id_src2;
     private float f_src1;
     private Id id_readwrite;
     private String label;
     
     private int format;
     public static final int FORMAT_IR = 0; // int reg
     public static final int FORMAT_FR = 1; // float reg
     public static final int FORMAT_RD = 2; // reg identifier
     public static final int FORMAT_DDR = 3; //id id reg
     public static final int FORMAT_RDR = 4; 
     public static final int FORMAT_RRR = 5; 
     public static final int FORMAT_D = 6; 
     public static final int FORMAT_DRR = 7; 
     public static final int FORMAT_S = 8; 
     public static final int FORMAT_O = 9; 

     public Instruction getInstruction(){
    	 return OPCODE;
     }
     
     public int getFormat(){
    	 return format;
     }
     
     public IRNode(String label){
    	 this.OPCODE = ISA.LABEL;
    	 this.label = label;
    	 format = FORMAT_S;
     }
     
	public IRNode(Instruction OPCODE, int src1, Register dest){
		this.OPCODE = OPCODE;
		this.i_src1 = src1;
		this.r_dest = dest;
		format = FORMAT_IR;
	}
	
	public IRNode(Instruction OPCODE){
		this.OPCODE = OPCODE;
		format = FORMAT_O;
	}
	
	public IRNode(Instruction OPCODE, float s, Register dest){
		this.OPCODE = OPCODE;
		this.f_src1 = s;
		this.r_dest = dest;
		format = FORMAT_FR;
	}
	
	public IRNode(Instruction OPCODE, Register r_src, Id id_dest){
		//Like STOREI $T1 a
		this.OPCODE = OPCODE;
		this.r_src1 = r_src;
		this.id_dest = id_dest;
		format = FORMAT_RD;
	}	
	
	public IRNode(Instruction OPCODE, Id id_src1, Id id_src2, Register dest){
		this.OPCODE = OPCODE;
		this.id_src1 = id_src1;
		this.id_src2 = id_src2;
		this.r_dest = dest;
		format = FORMAT_DDR;
	}
	
	public IRNode(Instruction OPCODE, Register r_src1, Id id_src2, Register dest) {
		this.OPCODE = OPCODE;
		this.id_src2 = id_src2;
		this.r_src1 = r_src1;
		this.r_dest = dest;
		format = FORMAT_RDR;
	}
	
	public IRNode(Instruction OPCODE, Register r_src1, Register r_src2, Register dest) {
		this.OPCODE = OPCODE;
		this.r_src1 = r_src1;
		this.r_src2 = r_src2;
		this.r_dest = dest;
		format = FORMAT_RRR;
	}
	
	public IRNode(Instruction OPCODE, Id id_readwrite){
		this.OPCODE = OPCODE;
		this.id_readwrite = id_readwrite;
		format = FORMAT_D;
	}
	
	public IRNode(Instruction OPCODE, Id _id, Register _reg, Register dest) {
		this.OPCODE = OPCODE;
		this.id_src1 = _id;
		this.r_src2 = _reg;
		this.r_dest = dest;
		format = FORMAT_DRR;
	}

	@Override 
	public String toString(){
		if(this.label != null)
			return String.format(";LABEL " + this.label);
		
		String prefix = ";" + this.OPCODE.getName();
		switch(format){
		case FORMAT_IR:
			return prefix + " " + String.format("%s %s", this.i_src1, this.r_dest.toString());
		case FORMAT_FR: 
			return prefix + " " + String.format("%s %s", this.f_src1, this.r_dest.toString());
		case FORMAT_RD: 
			return prefix + " " + String.format("%s %s", this.r_src1.toString(), this.id_dest.name);
		case FORMAT_DDR: 
			return prefix + " " + String.format("%s %s %s", this.id_src1.name, this.id_src2.name, this.r_dest.toString());
		case FORMAT_RDR: 
			return prefix + " " + String.format("%s %s %s", this.r_src1.toString(), this.id_src2.name, this.r_dest.toString());
		case FORMAT_RRR:
			return prefix + " " + String.format("%s %s %s", this.r_src1.toString(), this.r_src2.toString(), this.r_dest.toString());
		case FORMAT_D:
			return prefix + " " + this.id_readwrite.name;
		case FORMAT_DRR:
			return prefix + " " +  String.format("%s %s %s", this.id_src1.name, this.r_src2.toString(), this.r_dest.toString());
		case FORMAT_O:
			return ";" + this.OPCODE.getName();
		}
		/*
		
		//old stuff
		if(this.label != null)
			return String.format(";LABEL " + this.label);
		
		if(OPCODE == ISA.ADDI && r_src1 != null && r_src2 != null && r_dest != null)
			return String.format(";%s %s %s %s", OPCODE.getName(), r_src1.toString() ,r_src2.toString(), r_dest.toString()); 
		
		if(OPCODE == ISA.ADDI && r_src1 != null && id_src2 != null)
			return String.format(";%s %s %s %s", OPCODE.getName(), r_src1.toString() ,id_src2.name, r_dest.toString()); 
		
		
		if(OPCODE == ISA.STOREI && r_dest != null)
			return  String.format(";%s %d %s", OPCODE.getName(), i_src1 ,r_dest.toString()); 
		
		if(OPCODE == ISA.STOREI && r_src1 != null)
			return  String.format(";%s %s %s", OPCODE.getName(), r_src1.toString(), id_dest.name);
		
		if(OPCODE == ISA.READI || OPCODE == ISA.READF)
			return String.format(";%s %s", OPCODE.getName(), id_readwrite.name);
		
		if(this.OPCODE == ISA.MULTI || this.OPCODE == ISA.DIVI)
			if(this.id_src1 != null && this.id_src2 != null){
				return String.format(";%s %s %s %s", OPCODE.getName(), id_src1.name, id_src2.name, r_dest.toString());
			}else if(this.r_src1 != null && this.id_src2 != null){
				return String.format(";%s %s %s %s", OPCODE.getName(), r_src1.toString(), id_src2.name, r_dest.toString());
			}else if(this.id_src1 != null && this.r_src2 != null){
				return String.format(";%s %s %s %s", OPCODE.getName(), id_src1.name, r_src2.toString(), r_dest.toString());
			}else if(this.r_src1 != null && this.r_src2 != null){
				return String.format(";%s %s %s %s", OPCODE.getName(), r_src1.toString(), r_src2.toString(), r_dest.toString());
			}else{
				return "MULT <toString>";
			}
		if(this.OPCODE == ISA.WRITEI || this.OPCODE == ISA.WRITES || this.OPCODE == ISA.WRITES)
			return String.format(";%s %s", OPCODE.getName(), id_readwrite.name);
		*/
		return ";" + this.OPCODE.getName();
	}

}
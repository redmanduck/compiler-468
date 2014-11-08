public class IRNode {
     private Instruction OPCODE;
     private Register r_dest, r_src1, r_src2;
     private int i_src1;
     private Id id_dest, id_src1, id_src2;
     private float f_src1;
     private Id id_readwrite;
     private String label,jtarget;
     
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
     public static final int FORMAT_DD = 10;
     public static final int FORMAT_RRT = 11;
     public static final int FORMAT_RDT = 12;
     public static final int FORMAT_DRT = 13; 
     public static final int FORMAT_DDT = 14;
     public static final int FORMAT_T = 15;

     public Id getIdOperand(int which){
    	 switch(which){
    	 case 1:
    		 return id_src1;
    	 case 2:
    		 return id_src2;
    	 case 3:
    		 return id_dest;
    	 case 4:
    		 return this.id_readwrite;
    	 default:
    		 return null;
    	 }
     }
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
	
	public IRNode(Instruction OPCODE, Id _id1, Id _id2) {
		this.OPCODE = OPCODE;
		this.id_src1 = _id1;
		this.id_dest = _id2;
		format = FORMAT_DD;
	}

	public IRNode(Instruction conditional_control, Register left, Register right, String target) {
		this.OPCODE = conditional_control;
		this.r_src1 = left;
		this.r_src2 = right;
		this.jtarget = target;
		format = FORMAT_RRT;
	}
	
	public IRNode(Instruction conditional_control, Register left, Id right, String string) {
		format = FORMAT_RDT;
		this.OPCODE = conditional_control;
		this.r_src1 = left;
		this.id_src2 = right;
		this.jtarget = string;
	}

	public IRNode(Instruction conditional_control, Id left, Register right, String string) {
		format = FORMAT_DRT;
		this.OPCODE = conditional_control;
		this.id_src1 = left;
		this.r_src2 = right;
		this.jtarget = string;
	}
	
	public IRNode(Instruction conditional_control, Id left, Id right, String string) {
		format = FORMAT_DDT;
		this.OPCODE = conditional_control;
		this.id_src1 = left;
		this.id_src2 = right;
		this.jtarget = string;
	}
	
	public IRNode(Instruction j, String jtarget) {
		format = FORMAT_T;
		this.OPCODE = j;
		this.jtarget = jtarget;
	}
	@Override 
	public String toString(){
		if(this.label != null)
			return String.format(";LABEL " + this.label);
		
		String prefix = ";" + this.OPCODE.getName();
		switch(format){
		case FORMAT_DD:
			return prefix + " " + String.format("%s %s", this.id_src1.name, this.id_dest.name);
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
		case FORMAT_RRT:
			return prefix + " " + String.format("%s %s %s", this.r_src1.toString(), this.r_src2.toString(), this.jtarget.toString());
		case FORMAT_RDT:
			return prefix + " " + String.format("%s %s %s", this.r_src1.toString(), this.id_src2.toString(), this.jtarget.toString());
		case FORMAT_DRT:
			return prefix + " " + String.format("%s %s %s", this.id_src1.toString(), this.r_src2.toString(), this.jtarget.toString());
		case FORMAT_DDT:
			return prefix + " " + String.format("%s %s %s", this.id_src1.toString(), this.id_src2.toString(), this.jtarget.toString());
		case FORMAT_D:
			return prefix + " " + this.id_readwrite.name;
		case FORMAT_DRR:
			return prefix + " " +  String.format("%s %s %s", this.id_src1.name, this.r_src2.toString(), this.r_dest.toString());
		case FORMAT_O:
			return ";" + this.OPCODE.getName();
		case FORMAT_T:
			return ";" + this.OPCODE.getName() + " " + this.jtarget;
		}

		return ";" + this.OPCODE.getName();
	}

}
import ssabpisa.ece468.isa.ISA;
import ssabpisa.ece468.isa.Instruction;

public class IRNode {
     private Instruction OPCODE;
     private Register r_dest;
     private int i_src, i_op1, i_op2;
     private Register r_src, r_src2;
     private Id id_dest, id_src1, id_src2;
     private float f_src;
     private Id id_readwrite;
     private String label;
     
     public IRNode(String label){
    	 this.label = label;
     }
     
	public IRNode(Instruction OPCODE, int src1, Register dest){
		this.OPCODE = OPCODE;
		this.i_src = src1;
		this.r_dest = dest;
	}
	
	public IRNode(Instruction OPCODE){
		this.OPCODE = OPCODE;
	}
	
	public IRNode(Instruction OPCODE, float s, Register dest){
		this.OPCODE = OPCODE;
		this.f_src = s;
		this.r_dest = dest;
	}
	
	public IRNode(Instruction OPCODE, Register r_src, Id id_dest){
		//Like STOREI $T1 a
		this.OPCODE = OPCODE;
		this.r_src = r_src;
		this.id_dest = id_dest;
	}	
	
	public IRNode(Instruction OPCODE, Id id_src1, Id id_src2, Register dest){
		this.OPCODE = OPCODE;
		this.id_src1 = id_src1;
		this.id_src2 = id_src2;
		this.r_dest = dest;
	}
	
	public IRNode(Instruction OPCODE, Register r_src1, Register r_src2, Register dest){
		this.OPCODE = OPCODE;
		this.r_src = r_src1;
		this.r_src2 = r_src2;
		this.r_dest = dest;
	}
	
	public IRNode(Instruction OPCODE, Id id_readwrite){
		this.OPCODE = OPCODE;
		this.id_readwrite = id_readwrite;
	}
	
	@Override 
	public String toString(){
		if(this.label != null)
			return String.format(";LABEL " + this.label);
		if(OPCODE == ISA.ADDI && r_src != null && r_src2 != null && r_dest != null)
			return String.format(";%s %s %s %s", OPCODE.getName(), r_src.toString() ,r_src2.toString(), r_dest.toString()); 
		if(OPCODE == ISA.STOREI && r_dest != null)
			return  String.format(";%s %d %s", OPCODE.getName(), i_src ,r_dest.toString()); 
		if(OPCODE == ISA.STOREI && r_src != null)
			return  String.format(";%s %s %s", OPCODE.getName(), r_src.toString(), id_dest.name);
		if(OPCODE == ISA.READI || OPCODE == ISA.READF)
			return String.format(";%s %s", OPCODE.getName(), id_readwrite.name);
		if(this.OPCODE == ISA.MULTI || this.OPCODE == ISA.DIVI)
			return String.format(";%s %s %s %s", OPCODE.getName(), id_src1.name, id_src2.name, r_dest.toString());
		if(this.OPCODE == ISA.WRITEI || this.OPCODE == ISA.WRITES || this.OPCODE == ISA.WRITES)
			return String.format(";%s %s", OPCODE.getName(), id_readwrite.name);
		return ";" + this.OPCODE.getName();
	}

}
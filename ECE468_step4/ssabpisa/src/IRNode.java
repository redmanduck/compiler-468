import ssabpisa.ece468.isa.Instruction;

public class IRNode {
     private Instruction OPCODE;
     private Register r_dest;
     private int i_src, i_op1, i_op2;
     private Register r_src;
     private Id id_dest;
     private float f_src;
     
	public IRNode(Instruction OPCODE, int src1, Register dest){
		this.OPCODE = OPCODE;
		this.i_src = src1;
		this.r_dest = dest;
	}
	
	public IRNode(Instruction OPCODE, float s, Register dest){
		this.OPCODE = OPCODE;
		this.f_src = s;
		this.r_dest = dest;
	}
	
	public IRNode(Instruction OPCODE, Register r_src, Id id_dest){
		this.OPCODE = OPCODE;
		this.r_src = r_src;
		this.id_dest = id_dest;
	}	
	@Override 
	public String toString(){
		if(r_dest != null)
			return  String.format(";%s %d %s", OPCODE.getName(), i_src ,r_dest.toString()); 
		if(id_dest != null)
			return  String.format(";%s %s %s", OPCODE.getName(), r_src.toString(), id_dest.name);
		
		return null;
	}

}
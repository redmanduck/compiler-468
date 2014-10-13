public class IRNode {
     private String OPCODE;
     private int OP1;
     private int OP2;
     public int Result;
     
     
	public IRNode(String OPCODE, int OP1, int OP2, int res){
		this.OPCODE = OPCODE;
		this.OP1 = OP1;
		this.OP2 = OP2;
		this.Result = res;
	}

	public int getOperand(int which){
		if(which == 1) return OP1;
		return OP2;
	}

}
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
import java.util.HashSet;
public class IRNode {

     private Instruction OPCODE;
     private Register r_dest, r_src1, r_src2;
     private int i_src1;
     private Id id_dest, id_src1, id_src2;
     private float f_src1;
     private Id id_readwrite;
     private String label,jtarget;
	 public boolean discovered;
     public String fn_key; //dedicated function name field

	 public HashSet<IRNode>  predecessors, successors;

	 public HashSet<String> GEN, KILL;
	 public HashSet<String> LIVE_IN, LIVE_OUT;

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
     public static final int FORMAT_RS = 16;
     public static final int FORMAT_DR = 17;
     public static final int FORMAT_R = 18;
     
     
     public static final int OP_ID_SRC1 = 1;
     public static final int OP_ID_SRC2 = 2;
     public static final int OP_ID_DEST = 3;
     public static final int OP_ID_READWRITE = 4;

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

	public String getJumpTarget(){
		return jtarget;
	}
	public String getLabel(){
		return label;
	}

	/*
	 * initialize control flow related properties that was added later
	 */
	private void init_cflow(){
		predecessors = new HashSet<IRNode>();
		successors = new HashSet<IRNode>();

		GEN = new HashSet<String>();
		KILL = new HashSet<String>();
		LIVE_IN = new HashSet<String>();
		LIVE_OUT = new HashSet<String>();

		discovered = false;
	}

     public IRNode(String label){
		 init_cflow();
    	 this.OPCODE = ISA.LABEL;
    	 this.label = label;
    	 format = FORMAT_S;
     }
     
	public IRNode(Instruction OPCODE, int src1, Register dest){
		init_cflow();
		this.OPCODE = OPCODE;
		this.i_src1 = src1;
		this.r_dest = dest;
		format = FORMAT_IR;

		if(!dest.toString().contains("$R"))  KILL.add(dest.toString());
	}
	
	public IRNode(Instruction OPCODE){
		init_cflow();
		this.OPCODE = OPCODE;
		format = FORMAT_O;
	}
	
	public IRNode(Instruction OPCODE, float s, Register dest){
		init_cflow();
		this.OPCODE = OPCODE;
		this.f_src1 = s;
		this.r_dest = dest;
		format = FORMAT_FR;
		if(!dest.toString().contains("$R")) KILL.add(dest.toString());
	}
	
	public IRNode(Instruction OPCODE, Register r_dest){
		init_cflow();
		this.OPCODE = OPCODE;
		this.r_dest = r_dest;
		format = FORMAT_R;
		if(!r_dest.toString().contains("$R")) KILL.add(r_dest.toString());
	}
	
	public IRNode(Instruction OPCODE, Register r_src, Id idd){
		init_cflow();
		//Like STOREI $T1 a
		this.OPCODE = OPCODE;
		this.r_src1 = r_src;
		this.id_dest = idd;
		format = FORMAT_RD;
		if(!idd.toString().contains("$R")) 
			KILL.add(idd.getTiny());
		GEN.add(r_src.toString());
	}	
	
	public IRNode(Instruction OPCODE, Id id_src1, Id id_src2, Register dest){
		init_cflow();
		this.OPCODE = OPCODE;
		this.id_src1 = id_src1;
		this.id_src2 = id_src2;
		this.r_dest = dest;
		format = FORMAT_DDR;
		if(!dest.toString().contains("$R")) 
			KILL.add(dest.toString());
		GEN.add(id_src1.getTiny());
		GEN.add(id_src2.getTiny());
	}
	
	public IRNode(Instruction OPCODE, Register r_src1, Id id_src2, Register dest) {
		init_cflow();
		this.OPCODE = OPCODE;
		this.id_src2 = id_src2;
		this.r_src1 = r_src1;
		this.r_dest = dest;
		format = FORMAT_RDR;
		if(!dest.toString().contains("$R")) KILL.add(dest.toString());
		GEN.add(r_src1.toString());
		GEN.add(id_src2.getTiny());
	}
	
	
	public IRNode(Instruction OPCODE, Register r_src1, Register r_src2, Register dest) {
		init_cflow();
		this.OPCODE = OPCODE;
		this.r_src1 = r_src1;
		this.r_src2 = r_src2;
		this.r_dest = dest;
		format = FORMAT_RRR;
		if(!dest.toString().contains("$R")) 
			KILL.add(dest.toString());
		GEN.add(r_src1.toString());
		GEN.add(r_src2.toString());
	}
	
	public IRNode(Instruction OPCODE, Id id_readwrite){
		init_cflow();
		this.OPCODE = OPCODE;
		this.id_readwrite = id_readwrite;
		format = FORMAT_D;
		if(ISA.InstructionSpecies(OPCODE, ISA._READ)){
			GEN.add(id_readwrite.getTiny());
		}else if(ISA.InstructionSpecies(OPCODE, ISA._WRITE)){
			if(!id_readwrite.toString().contains("$R")) 
				KILL.add(id_readwrite.getTiny());
		}
	}
	
	public IRNode(Instruction OPCODE, Id _id, Register _reg, Register dest) {
		init_cflow();
		this.OPCODE = OPCODE;
		this.id_src1 = _id;
		this.r_src2 = _reg;
		this.r_dest = dest;
		format = FORMAT_DRR;
		if(!dest.toString().contains("$R")) 
			KILL.add(dest.toString());
		GEN.add(_id.getTiny());
		GEN.add(_reg.toString());
	}
	
	public IRNode(Instruction OPCODE, Id _id1, Id _id2) {
		init_cflow();
		this.OPCODE = OPCODE;
		this.id_src1 = _id1;
		this.id_dest = _id2;
		format = FORMAT_DD;
		if(!_id2.toString().contains("$R")) 
			KILL.add(_id2.getTiny());
		GEN.add(id_src1.getTiny());
	}
	
	public IRNode(Instruction OPCODE, Register r1, Register r2) {
		init_cflow();
		this.OPCODE = OPCODE;
		this.r_src1 = r1;
		this.r_dest = r2;
		format = FORMAT_RS;
		if(!r2.toString().contains("$R")) 
			KILL.add(r2.toString());
		GEN.add(r1.toString());
	}
	
	public IRNode(Instruction OPCODE, Id _id1, Register rdest) {
		init_cflow();
		this.OPCODE = OPCODE;
		this.id_src1 = _id1;
		this.r_dest = rdest;
		format = FORMAT_DR;
		if(!rdest.toString().contains("$R")) 
			KILL.add(rdest.toString());
		GEN.add(_id1.getTiny());
	}

	public IRNode(Instruction conditional_control, Register left, Register right, String target) {
		init_cflow();
		this.OPCODE = conditional_control;
		this.r_src1 = left;
		this.r_src2 = right;
		this.jtarget = target;
		format = FORMAT_RRT;
		GEN.add(left.toString());
		GEN.add(right.toString());

	}
	
	public IRNode(Instruction conditional_control, Register left, Id right, String string) {
		init_cflow();
		format = FORMAT_RDT;
		this.OPCODE = conditional_control;
		this.r_src1 = left;
		this.id_src2 = right;
		this.jtarget = string;
		GEN.add(left.toString());
		GEN.add(right.getTiny());
	}

	public IRNode(Instruction conditional_control, Id left, Register right, String string) {
		init_cflow();
		format = FORMAT_DRT;
		this.OPCODE = conditional_control;
		this.id_src1 = left;
		this.r_src2 = right;
		this.jtarget = string;
		GEN.add(left.getTiny());
		GEN.add(right.toString());
	}
	
	public IRNode(Instruction conditional_control, Id left, Id right, String string) {
		init_cflow();
		format = FORMAT_DDT;
		this.OPCODE = conditional_control;
		this.id_src1 = left;
		this.id_src2 = right;
		this.jtarget = string;
		GEN.add(left.getTiny());
		GEN.add(right.toString());
	}
	
	public IRNode(Instruction j, String jtarget) {
		init_cflow();
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
			return prefix + " " + String.format("%s %s", this.id_src1.toString(), this.id_dest.toString());
		case FORMAT_IR:
			return prefix + " " + String.format("%s %s", this.i_src1, this.r_dest.toString());
		case FORMAT_FR: 
			return prefix + " " + String.format("%s %s", this.f_src1, this.r_dest.toString());
		case FORMAT_RD: 
			return prefix + " " + String.format("%s %s", this.r_src1.toString(), this.id_dest.toString());
		case FORMAT_DDR: 
			return prefix + " " + String.format("%s %s %s", this.id_src1.toString(), this.id_src2.toString(), this.r_dest.toString());
		case FORMAT_RDR: 
			return prefix + " " + String.format("%s %s %s", this.r_src1.toString(), this.id_src2.toString(), this.r_dest.toString());
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
			return prefix + " " + this.id_readwrite.toString();
		case FORMAT_DRR:
			return prefix + " " +  String.format("%s %s %s", this.id_src1.toString(), this.r_src2.toString(), this.r_dest.toString());
		case FORMAT_O:
			return ";" + this.OPCODE.getName();
		case FORMAT_T:
			return ";" + this.OPCODE.getName() + " " + this.jtarget;
		case FORMAT_RS:
			return ";" + this.OPCODE.getName() + " " + this.r_src1 + " " + this.r_dest;
		case FORMAT_DR:
			return ";" + this.OPCODE.getName() + " " + this.id_src1 + " " + this.r_dest;
		case FORMAT_R:
			return ";" + this.OPCODE.getName() + " " + this.r_dest;
		}

		return ";" + this.OPCODE.getName();
	}

}
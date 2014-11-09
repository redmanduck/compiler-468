public class ISA{
	
	public static Instruction __skip = new Instruction("__skip", 0);
	public static Instruction ADDI = new Instruction("ADDI", 3, "INT");
	public static Instruction ADDF = new Instruction("ADDF", 3, "FLOAT");
	
	public static Instruction SUBI = new Instruction("SUBI", 3, "INT");
	public static Instruction SUBF = new Instruction("SUBF", 3, "FLOAT");
	
	public static Instruction MULTI = new Instruction("MULTI", 3, "INT");
	public static Instruction MULTF = new Instruction("MULTF", 3, "FLOAT");
	
	public static Instruction DIVI = new Instruction("DIVI", 3, "INT");
	public static Instruction DIVF = new Instruction("DIVF", 3, "FLOAT");
	
	public static Instruction STOREI = new Instruction("STOREI", 2, "INT");
	public static Instruction STOREF = new Instruction("STOREF", 2, "FLOAT");
	
	public static Instruction GT = new Instruction("GT", 3, "INT");
	public static Instruction GE = new Instruction("GE", 3, "INT");
	public static Instruction LT = new Instruction("LT", 3, "INT");
	public static Instruction NE = new Instruction("NE",3, "INT");
	public static Instruction EQ = new Instruction("EQ", 3, "INT");
	
	public static Instruction GTI = new Instruction("GTI", 3, "INT"); //?
	public static Instruction GEI = new Instruction("GEI", 3, "INT"); //? the example is inconsistent with the manual
	public static Instruction LTI = new Instruction("LTI", 3, "INT"); //
	public static Instruction NEI = new Instruction("NEI",3, "INT"); //
	public static Instruction EQI = new Instruction("EQI", 3, "INT"); //
	
	public static Instruction LEF = new Instruction("LEF", 3, "FLOAT"); //
	public static Instruction LEI = new Instruction("LEI", 3, "INT"); //

	public static Instruction GTF = new Instruction("GTF", 3, "FLOAT"); //?
	public static Instruction GEF = new Instruction("GEF", 3, "FLOAT"); //? the example is inconsistent with the manual
	public static Instruction LTF = new Instruction("LTF", 3, "FLOAT"); //
	public static Instruction NEF = new Instruction("NEF",3, "FLOAT"); //
	public static Instruction EQF = new Instruction("EQF", 3, "FLOAT"); //

	public static Instruction JUMP = new Instruction("JUMP", 1, "ADDR");
	public static Instruction LABEL = new Instruction("LABEL", 1, "ADDR");
	
	public static Instruction READI = new Instruction("READI", 1, "INT");
	public static Instruction READF = new Instruction("READF", 1, "FLOAT");
	public static Instruction READS = new Instruction("READS", 1, "STRING");

	public static Instruction WRITEI = new Instruction("WRITEI", 1, "INT");
	public static Instruction WRITEF = new Instruction("WRITEF", 1, "FLOAT");
	public static Instruction WRITES = new Instruction("WRITES",1, "STRING");
	
	public static Instruction RET = new Instruction("RET");
	public static Instruction LINK = new Instruction("LINK");
	
	
	public static OperationGenreCollection _ADD = new OperationGenreCollection(ADDI, ADDF);
	public static OperationGenreCollection _SUB = new OperationGenreCollection(SUBI, SUBF);
	public static OperationGenreCollection _MULT = new OperationGenreCollection(MULTI, MULTF);
	public static OperationGenreCollection _DIV = new OperationGenreCollection(DIVI, DIVF);
	public static OperationGenreCollection _STORE = new OperationGenreCollection(STOREI, STOREF);
	public static OperationGenreCollection _READ = new OperationGenreCollection(READF, READI);			
	public static OperationGenreCollection _WRITE = new OperationGenreCollection(WRITES, WRITEI, WRITEF);

	/* end of IR instructions */
	
	public static Instruction move = new Instruction("move", 2);
	public static Instruction addi = new Instruction("addi", 2);
	public static Instruction addr = new Instruction("addr", 2);
	public static Instruction subi = new Instruction("subi", 2);
	public static Instruction subr = new Instruction("subr", 2);
	public static Instruction muli = new Instruction("muli", 2);
	public static Instruction mulr = new Instruction("mulr", 2);
	public static Instruction divi = new Instruction("divi", 2);
	public static Instruction divr = new Instruction("divr", 2);
	public static Instruction inci = new Instruction("inci", 1);
	public static Instruction deci = new Instruction("deci", 1);
	public static Instruction cmpi = new Instruction("cmpi", 2);
	public static Instruction push = new Instruction("push", 1);
	public static Instruction pop = new Instruction("pop", 1);
	public static Instruction jsr = new Instruction("jsr", 1);
	public static Instruction ret = new Instruction("ret", 0);
	public static Instruction link = new Instruction("link", 1);
	public static Instruction unlnk = new Instruction("unlnk", 0);
	public static Instruction label = new Instruction("label", 1);

	public static Instruction cmpr = new Instruction("cmpr", 2);
	public static Instruction jmp = new Instruction("jmp", 1);
	public static Instruction jgt = new Instruction("jgt", 1);
	public static Instruction jlt = new Instruction("jlt", 1);
	public static Instruction jge = new Instruction("jge", 1);
	public static Instruction jeq = new Instruction("jeq", 1);
	public static Instruction jle = new Instruction("jle", 1);
	public static Instruction sys_readi = new Instruction("sys readi", 1);
	public static Instruction sys_readr = new Instruction("sys readr", 1);
	public static Instruction sys_writei = new Instruction("sys writei", 1);
	public static Instruction sys_writer = new Instruction("sys writer", 1);
	public static Instruction sys_writes = new Instruction("sys writes", 1);
	public static Instruction sys_halt = new Instruction("sys halt");
	public static Instruction end = new Instruction("end");
	
	public ISA(){}

	
	public static boolean InstructionSpecies(Instruction op, OperationGenreCollection g){
		return g.contains(op);
	}
	
	public static Instruction transform_type(Instruction op, String type, String type2) {
		boolean has_float = false;
		boolean has_string = false;
		
		if(type != null && type.equals("FLOAT") || type2 != null && type2.equals("FLOAT")){
			has_float = true;
		}
		
		if(type != null && type.equals("STRING") || type2 != null && type2.equals("STRING")){
			has_string = true;
		}
		
		if(InstructionSpecies(op, _ADD)){
			if(has_float){
				return ADDF;
			}else{
				return ADDI;
			}
		}else if(InstructionSpecies(op, _SUB)){
			if(has_float){
				return SUBF;
			}else{
				return SUBI;
			}
		}else if(InstructionSpecies(op, _DIV)){
			if(has_float){
				return DIVF;
			}else{
				return DIVI;
			}
		}else if(InstructionSpecies(op, _STORE)){
			if(has_float){
				return STOREF;
			}else{
				return STOREI;
			}
		}else if(InstructionSpecies(op, _READ)){
			if(has_float){
				return READF;
			}else{
				return READI;
			}
		}else if(InstructionSpecies(op, _WRITE)){
			if(has_float){
				return WRITEF;
			}else if(has_string){
				return WRITES;
			}else{
				return WRITEI;
			}
		}else if(InstructionSpecies(op, _MULT)){
			if(has_float){
				return MULTF;
			}else{
				return MULTI;
			}
		}
		
		System.err.print("Something gone wrong in ISA.java isa transform");
		return null;
	}
}


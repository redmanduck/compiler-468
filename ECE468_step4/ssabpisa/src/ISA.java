public class ISA{
	
	public static Instruction ADDI = new Instruction("ADDI", 3);
	public static Instruction ADDF = new Instruction("ADDF", 3);
	public static Instruction SUBI = new Instruction("SUBI", 3);
	public static Instruction SUBF = new Instruction("SUBF", 3);
	public static Instruction MULTI = new Instruction("MULTI", 3);
	public static Instruction MULTF = new Instruction("MULTF", 3);
	public static Instruction DIVI = new Instruction("DIVI", 3);
	public static Instruction DIVF = new Instruction("DIVF", 3);
	public static Instruction STOREI = new Instruction("STOREI", 2);
	public static Instruction STOREF = new Instruction("STOREF", 2);
	public static Instruction GT = new Instruction("GT", 3);
	public static Instruction GE = new Instruction("GE", 3);
	public static Instruction LT = new Instruction("LT", 3);
	public static Instruction NE = new Instruction("NE",3 );
	public static Instruction EQ = new Instruction("EQ", 3);
	public static Instruction JUMP = new Instruction("JUMP", 1);
	public static Instruction LABEL = new Instruction("LABEL", 1);
	public static Instruction READI = new Instruction("READI", 1);
	public static Instruction READF = new Instruction("READF", 1);
	public static Instruction WRITEI = new Instruction("WRITEI", 1);
	public static Instruction WRITEF = new Instruction("WRITEF", 1);
	public static Instruction WRITES = new Instruction("WRITES",1 );
	public static Instruction RET = new Instruction("RET");
	public static Instruction LINK = new Instruction("LINK");
	
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
	public static Instruction jle = new Instruction("jle", 1);
	public static Instruction sys_readi = new Instruction("sys readi", 1);
	public static Instruction sys_readr = new Instruction("sys readr", 1);
	public static Instruction sys_writei = new Instruction("sys writei", 1);
	public static Instruction sys_writer = new Instruction("sys writer", 1);
	public static Instruction sys_writes = new Instruction("sys writes", 1);
	public static Instruction sys_halt = new Instruction("sys halt");
	public static Instruction end = new Instruction("end");
	
	public ISA(){}
}


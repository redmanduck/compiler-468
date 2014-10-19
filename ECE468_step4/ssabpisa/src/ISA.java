public class ISA{
	
	public static Instruction ADDI = new Instruction("ADDI");
	public static Instruction ADDF = new Instruction("ADDF");
	public static Instruction SUBI = new Instruction("SUBI");
	public static Instruction SUBF = new Instruction("SUBF");
	public static Instruction MULTI = new Instruction("MULTI");
	public static Instruction MULTF = new Instruction("MULTF");
	public static Instruction DIVI = new Instruction("DIVI");
	public static Instruction DIVF = new Instruction("DIVF");
	public static Instruction STOREI = new Instruction("STOREI");
	public static Instruction STOREF = new Instruction("STOREF");
	public static Instruction GT = new Instruction("GT");
	public static Instruction GE = new Instruction("GE");
	public static Instruction LT = new Instruction("LT");
	public static Instruction NE = new Instruction("NE");
	public static Instruction EQ = new Instruction("EQ");
	public static Instruction JUMP = new Instruction("JUMP");
	public static Instruction LABEL = new Instruction("LABEL");
	public static Instruction READI = new Instruction("READI");
	public static Instruction READF = new Instruction("READF");
	public static Instruction WRITEI = new Instruction("WRITEI");
	public static Instruction WRITEF = new Instruction("WRITEF");
	public static Instruction WRITES = new Instruction("WRITES");
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
	public static Instruction sys_readi = new Instruction("sys readi", 0);
	public static Instruction sys_readr = new Instruction("sys readr", 0);
	public static Instruction sys_writei = new Instruction("sys writei", 0);
	public static Instruction sys_writer = new Instruction("sys writer", 0);
	public static Instruction sys_writes = new Instruction("sys writes", 0);
	public static Instruction sys_halt = new Instruction("sys halt");
	public static Instruction end = new Instruction("end");
	
	public ISA(){}
}


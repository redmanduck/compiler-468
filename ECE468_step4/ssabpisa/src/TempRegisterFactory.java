import java.util.Hashtable;

public class TempRegisterFactory {
	private static int tempcount = 1;
	private static Hashtable<Integer, Register> regs = new Hashtable<Integer, Register>();
	public static Register create(){
		int v = tempcount++;
		Register r = new Register('T', v);
		regs.put(v, r);
		return r;
	}
	
	public static Register getPrevious(){
		return regs.get(tempcount);
	}
}

import java.util.Hashtable;

public class TempRegisterFactory {
	private static int tempcount = 1;
	private static int tiny_tempcount = 0;
	private static Hashtable<Integer, Register> regs = new Hashtable<Integer, Register>();
	private static Register previous;
	public static Register create(String type){
		int v = tempcount++;
		Register r = new Register('T', v, type);
		regs.put(v, r);
		previous = r;
		return r;
	}
	
	public static Register createTiny(){
		int v = tiny_tempcount++;
		Register r = new Register('r', v);
		regs.put(v, r);
		previous = r;
		return r;
	}

	public static Register previous() {
		return previous;
	}
}

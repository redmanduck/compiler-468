import java.util.Hashtable;

public class RegisterFactory {
	private static int tempcount = 0;
	private static Hashtable<Integer, Register> regs = new Hashtable<Integer, Register>();
	public static Register createRegister(){
		return new Register();
	}
	
	public static Register getPreviousRegister(){
		return regs.get(tempcount);
	}
}

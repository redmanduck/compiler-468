
public class AutoLabelFactory {
	private static int tempcount = 1;
	
	public static String create(){
		int v = tempcount++;
		return "label" + v;
	}
	
}

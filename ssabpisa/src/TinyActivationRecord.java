import java.util.Hashtable;


public class TinyActivationRecord {
	private static int stack_growth_count = 1; 
	private static int local_var_count = -1;
	public static Hashtable<String, Integer> ParameterStackMap = new Hashtable<String, Integer>(); 
	public static Hashtable<String, Integer> LocalStackMap = new Hashtable<String, Integer>(); //local variable to stack
	
	public static void assignLocalVariable(String name) {
		LocalStackMap.put(name, local_var_count);
		local_var_count--;
	}
	
	public static void assignParameter(String name){
		ParameterStackMap.put(name, ++stack_growth_count);
	}
	
	public static Integer getParameter(String name){
		if(!ParameterStackMap.containsKey(name)){
			assignParameter(name);
		}
		return ParameterStackMap.get(name);
	}
	
	public static Integer getLocalVariable(String name){
		if(!LocalStackMap.containsKey(name)){
			   //if its not already in the Stack Map
			   //we put it there 
			   assignLocalVariable(name);
		}
		return LocalStackMap.get(name);
	}
	public static void saveRegisters(int increm){
		stack_growth_count += increm;
	}
	
	public static int getReturnStackAddress(){
		return stack_growth_count + 1;
	}
	
	public static void reset(){
		LocalStackMap.clear();
		ParameterStackMap.clear();
		stack_growth_count = 1;
		local_var_count = -1;
	}
}

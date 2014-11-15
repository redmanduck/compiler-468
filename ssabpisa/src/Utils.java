public class Utils {
	public static void printSymbolTable(SymbolTable s){
		// Post order walk of Symbol Table
		if(s == null){
			return;
		}
		if(s.error){
			 return;
		}
		System.out.format("Symbol table %s\n", s.scopename);
		for(String k : s.getKeys()){
			if(s.get(k).getStrValue() != null){
				 System.out.format("name %s type %s value %s\n", s.get(k).toString(), s.get(k).getType(), s.get(k).getStrValue());
			}else{
				 System.out.format("name %s type %s\n", s.get(k).toString(), s.get(k).getType());
			}
		}
		for(SymbolTable j : s.children){
			System.out.format("\n");
			printSymbolTable(j);
		}
	}
	
	public static void printIR(IRCollection irl){
		for(IRNode a : irl){
			System.out.println(a.toString());
		}
	}
	
}

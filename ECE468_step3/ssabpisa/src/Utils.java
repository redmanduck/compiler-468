public class Utils {
	public static void walk(SymbolTable s){
		if(s == null){
			return;
		}
		if(s.error){
			 return;
		}
		System.out.format("Symbol table %s\n", s.scopename);
		for(String k : s.getKeys()){
			if(s.get(k).value != null){
				 System.out.format("name %s type %s value %s\n", s.get(k).name, s.get(k).type, s.get(k).value);
			}else{
				 System.out.format("name %s type %s\n", s.get(k).name, s.get(k).type);
			}
		}
		for(SymbolTable j : s.children){
			System.out.format("\n");
			walk(j);
		}
	}
}

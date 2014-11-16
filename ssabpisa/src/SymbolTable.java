import java.util.*;

public class SymbolTable implements Iterable<Id>{
  private LinkedHashMap<String, Id> table;
  protected SymbolTable parent;
  protected ArrayList<SymbolTable> children;
  public String scopename;
  public boolean error;
  private static int local_var_count; 
  private static int parameter_count;

  public SymbolTable(SymbolTable par, String scopename) {
	// System.out.println("\nSymbol table " + scopename);
     this.parent = par;
     this.scopename  = scopename;
     this.children = new ArrayList<SymbolTable>();
     this.table = new LinkedHashMap<String, Id>();
     this.error =false;
     local_var_count = 1;
     parameter_count = 1;
  }
  
  public int count_local(){
	  int i = 0;
	  for(String key : this.table.keySet()){
		  Id id = this.table.get(key);
		  if(id.isLocalVariable()){
			  i++;
		  }
	  }
	  for(SymbolTable ch: this.children){
		  i += ch.count_local();
	  }
	  return i;
  }
  
  
  
  public Id search(String symbol_name){
	  Id t = null;
	  SymbolTable scope = this;
	  while(t == null && scope != null){
		  t = scope.get(symbol_name);
		  scope = scope.parent;
	  }
	  return t;
  }

  public boolean AddSymbolToTable(String type, String token){
	  if(this.table.containsKey(token)){
		  return false;
	  }
	  Id sym = new Id(token, type);
	  if(!this.scopename.equals("GLOBAL")){
		  sym.non_global_code = local_var_count;
		  local_var_count++;
	  }
	  //System.out.format("name %s type %s\n", token, type);
      this.table.put(token, sym);
      return true;
  }
  
  public boolean AddParameterToTable(String type, String token){
	  if(this.table.containsKey(token)){
		  return false;
	  }
	  
	  Id sym = new Id(token, type);
	  sym.parameter_code = parameter_count;
	  parameter_count++;
	  this.table.put(token, sym);
      return true;
  }

  public boolean AddSymbolToTable(String type, String token, String value){
	  if(this.table.containsKey(token)){
		  return false;
	  }
	 // System.out.format("name %s type %s value %s\n", token, type, value);
	  this.table.put(token, new Id(token, type, value));
	  return true;
  }
  
  public void AddChild(SymbolTable s){
    this.children.add(s);
  }

  public void RemoveChild(SymbolTable s){
    this.children.remove(s);
  }
  
  public Id get(String token){
	  return this.table.get(token);
  }
  
  public Set<String> getKeys(){
	  return this.table.keySet();
  }

@Override
public Iterator<Id> iterator() {
	Iterator<Id> inode = this.table.values().iterator();
    return inode; 
}

}

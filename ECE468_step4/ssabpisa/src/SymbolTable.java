import java.util.*;

public class SymbolTable{
  private LinkedHashMap<String, Id> table;
  protected SymbolTable parent;
  protected ArrayList<SymbolTable> children;
  public String scopename;
  public boolean error;

  public SymbolTable(SymbolTable par, String scopename){
	// System.out.println("\nSymbol table " + scopename);
     this.parent = par;
     this.scopename  = scopename;
     this.children = new ArrayList<SymbolTable>();
     this.table = new LinkedHashMap<String, Id>();
     this.error =false;
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
	  //System.out.format("name %s type %s\n", token, type);
      this.table.put(token, new Id(token, type));
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

}

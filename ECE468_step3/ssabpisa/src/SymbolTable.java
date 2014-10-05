import java.util.*;

public class SymbolTable{
  private Hashtable<String, Id> table;
  protected SymbolTable parent;
  protected Set<SymbolTable> children;
  public String scopename;

  public SymbolTable(SymbolTable par, String scopename){
	 System.out.println("\nSymbol table " + scopename);
     this.parent = par;
     this.scopename  = scopename;
     this.children = new HashSet<SymbolTable>();
     this.table = new Hashtable<String, Id>();
  }

  public boolean AddSymbolToTable(String type, String token){
	  if(this.table.containsKey(token)){
		  return false;
	  }
	  System.out.format("name %s type %s\n", token, type);
      this.table.put(token, new Id(type, token));
      return true;
  }

  public boolean AddSymbolToTable(String type, String token, String value){
	  if(this.table.containsKey(token)){
		  return false;
	  }
	  System.out.format("name %s type %s value %s\n", token, type, value);
	  this.table.put(token, new Id(type, token, value));
	  return true;
  }
  
  public void AddChild(SymbolTable s){
    this.children.add(s);
  }

  public void RemoveChild(SymbolTable s){
    this.children.remove(s);
  }

}

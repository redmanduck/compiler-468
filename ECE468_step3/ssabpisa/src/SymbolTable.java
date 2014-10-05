import java.util.*;

public class SymbolTable{
  private Hashtable<String, Id> table;
  protected SymbolTable parent;
  protected Set<SymbolTable> children;
  public String scopename;

  public SymbolTable(SymbolTable par, String scopename){
     this.parent = par;
     this.scopename  = scopename;
     this.children = new HashSet<SymbolTable>();
  }

  public void AddSymbolToTable(String type, String token){
    this.table.put(token, new Id(type, token));
  }

  public void AddChild(SymbolTable s){
    this.children.add(s);
  }

  public void RemoveChild(SymbolTable s){
    this.children.remove(s);
  }

}

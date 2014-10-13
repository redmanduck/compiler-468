import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

public class ExtractionListener extends MicroBaseListener{
  private MicroParser parser;
  private SymbolTable current_scope;
  private int blockcount;
  private SymbolTable root;
  private IRList irlist;
  private IRGraph irgraph;
 
  public ExtractionListener(MicroParser psr) {
      this.parser = psr;
      current_scope =  new SymbolTable(null, "GLOBAL");
      irlist = new IRList();
      irgraph = new IRGraph();
      root = current_scope;
      this.blockcount = 0;
  }
  
  public SymbolTable getRootSymbolTable(){
	  return root;
  }
  
  public IRList getIRList(){
	  return irlist;
  }

  private void enterScope(String scopename){
    SymbolTable newscope = new SymbolTable(current_scope, scopename);
    current_scope.AddChild(newscope);
    current_scope = newscope;
  }

  private void leaveScope(){
	  if(current_scope != null)
		  current_scope = current_scope.parent;
  }

  public void enterString_decl(@NotNull MicroParser.String_declContext ctx) { 
	 // System.out.print(current_scope.scopename + " -> (STR)");
	  String token_name = ctx.id().getText();
	  String token_value = ctx.str().getText();
	  	  
	  boolean result = current_scope.AddSymbolToTable("STRING", token_name, token_value);
	  if(!result){
    	  System.out.println("DECLARATION ERROR " + token_name);
    	  root.error = true;
    	  System.exit(1);
      }
  }
  
  public void enterVar_decl(@NotNull MicroParser.Var_declContext ctx) { 
	  
	  ParseTree decl_list = ctx.id_list();
	  
	  String[] names = decl_list.getText().split(","); //TODO: ask Milind or TA , this is cheating
	  for(String name : names){
		  if(!current_scope.AddSymbolToTable(ctx.var_type().getText(),name)){
	    	  System.out.println("DECLARATION ERROR " + name);
	    	  root.error = true;
	    	  System.exit(1);

		  }
	  }
  }

  public void enterParam_decl(MicroParser.Param_declContext ctx){	  
	  boolean result = current_scope.AddSymbolToTable(ctx.var_type().getText(), ctx.id().getText()); //TODO: VAR could be INT or FLOAT
      if(!result){
    	  root.error = true;
    	  System.out.println("DECLARATION ERROR " + ctx.id().getText());
    	  System.exit(1);
      }
  }
  
  public void exitPgm_body(MicroParser.Pgm_bodyContext ctx) {
    leaveScope();
  }

  public void exitFunc_body(MicroParser.Func_bodyContext ctx){
    leaveScope();
  }

  public void enterIf_stmt(MicroParser.If_stmtContext ctx){
	  enterScope("BLOCK " + ++blockcount);
  }
  
  public void enterElse_part(@NotNull MicroParser.Else_partContext ctx) { 
	  if(ctx.decl() == null){
		  return;
	  }
	  leaveScope(); //leave the if scope
	  enterScope("BLOCK " + ++blockcount);
  }
  public void exitElse_part(@NotNull MicroParser.Else_partContext ctx) { 
	  leaveScope();
  }
  
  public void exitIf_stmt(MicroParser.Func_bodyContext ctx){
	leaveScope();
  }
  
  public void enterFunc_declarations(@NotNull MicroParser.Func_declarationsContext ctx) { 
	  if(ctx.func_decl() == null){
		  return;
	  }
	  enterScope(ctx.func_decl().id().getText());
  }
  
  public void exitFunc_declarations(@NotNull MicroParser.Func_declarationsContext ctx) { 
	leaveScope();
  }
  
  public void enterWhile_stmt(@NotNull MicroParser.While_stmtContext ctx) { 
	 enterScope("BLOCK " + ++blockcount);
  }
  
  public void exitWhile_stmt(@NotNull MicroParser.While_stmtContext ctx) {
	leaveScope();  
  }

  
  @Override public void exitAssign_expr(MicroParser.Assign_exprContext ctx) { 
	  irlist.attach_Assignment(current_scope, ctx);
  }

  
}
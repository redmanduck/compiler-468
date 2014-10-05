import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;



public class ExtractionListener extends MicroBaseListener{
  MicroParser parser;
  SymbolTable current_scope;

  public ExtractionListener(MicroParser psr) {
      this.parser = psr;
      current_scope =  new SymbolTable(null, "GLOBAL");
  }

  private void enterScope(String scopename){
    SymbolTable newscope = new SymbolTable(current_scope, scopename);
    current_scope.AddChild(newscope);
    //newscope.parent = current_scope; the constructor does this
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
      }
  }
  
  public void enterVar_decl(@NotNull MicroParser.Var_declContext ctx) { 
	  
	  ParseTree decl_list = ctx.id_list();
	  
	  String[] names = decl_list.getText().split(","); //TODO: ask Milind or TA , this is cheating
	  for(String name : names){
		  if(!current_scope.AddSymbolToTable(ctx.var_type().getText(),name)){
	    	  System.out.println("DECLARATION ERROR " + name);
		  }
	  }
  }

  public void enterParam_decl(MicroParser.Param_declContext ctx){	  
	  boolean result = current_scope.AddSymbolToTable("VAR", ctx.id().getText()); //TODO: VAR could be INT or FLOAT
      if(!result){
    	  System.out.println("DECLARATION ERROR " + ctx.id().getText());
      }
  }
  public void exitPgm_body(MicroParser.Pgm_bodyContext ctx) {
    leaveScope();
  }

  public void exitFunc_body(MicroParser.Func_bodyContext ctx){
    leaveScope();
  }

  public void enterIf_stmt(MicroParser.If_stmtContext ctx){
    enterScope("BLOCK_IF");
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
  
  
}

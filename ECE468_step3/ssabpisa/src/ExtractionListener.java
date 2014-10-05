import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;


public class ExtractionListener extends MicroBaseListener{
  MicroParser parser;
  SymbolTable current_scope;

  public ExtractionListener(MicroParser psr) {
      this.parser = psr;
  }

  private void enterScope(String scopename){
    SymbolTable newscope = new SymbolTable(current_scope, scopename);
    current_scope.AddChild(newscope);
    newscope.parent = current_scope;
    current_scope.AddChild(newscope);
    current_scope = newscope;
  }

  private void leaveScope(){
    current_scope = current_scope.parent;
  }

  public void visitTerminal(TerminalNode node){
    System.out.print(node.getSymbol().getText());
  }

  public void enterDecl(MicroParser.String_declContext ctx){
    System.out.println(ctx.Identifier());
  }
  public void enterPgm_body(MicroParser.Pgm_bodyContext ctx) {
    System.out.println("GLOBAL");
    current_scope =  new SymbolTable(null, "GLOBAL");
  }

  public void enterFunc_body(MicroParser.Func_bodyContext ctx){
    enterScope("BLOCK_FUNC");
  }

  public void exitFunc_body(MicroParser.Func_bodyContext ctx){
    leaveScope();
  }

  public void enterIf_stmt(MicroParser.If_stmtContext ctx){
    enterScope("BLOCK_IF");
  }
}

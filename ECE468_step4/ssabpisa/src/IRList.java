import java.util.LinkedList;

public class IRList {
	private LinkedList<IRNode> ll;

	public IRList(){
		ll = new LinkedList<IRNode>();
	}
	
	public IRNode attach_Assignment(SymbolTable scope, MicroParser.Assign_exprContext ctx){
		Utils.printSymbolTable(scope);
		System.out.printf("LVALUE %s\n", ctx.id().getText()); //this is the store to register
		
		System.out.printf("RVALUE %s\n", ctx.expr().getText()); //iterate over this
		return null;
		
	}
	
	public IRNode attach_Read(SymbolTable scope, MicroParser.Assign_exprContext ctx){
		return null;
		
	}
	
	public IRNode attach_Write(SymbolTable scope, MicroParser.Assign_exprContext ctx){
		return null;
		
	}
}

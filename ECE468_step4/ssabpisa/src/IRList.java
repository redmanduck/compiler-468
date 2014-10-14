import java.util.LinkedList;

import ssabpisa.ece468.isa.ISA;

public class IRList {
	private LinkedList<IRNode> _List;

	public IRList(){
		_List = new LinkedList<IRNode>();
	}
	
	/*
	 *  Generate assignment IR nodes and add them to IR list
	 *  @param scope : the current scope
	 *  @param ctx: the current parse subtree context
	 *  @return last node in the list
	 */
	public IRNode attach_Assignment(SymbolTable scope, MicroParser.Assign_exprContext ctx){
		Register i_dest = TempRegisterFactory.create();
		Id dest_token = scope.search(ctx.id().getText());
				
		if(dest_token == null) {
			System.out.println("Symbol not found: " + ctx.id().getText());
			System.exit(1);
		}
		
		IRNode nodeA = null;
		attach_Expressions(scope, ctx.expr());
		
		if(ctx.expr().expr_prefix().getChildCount() == 0){
			if(dest_token.type.equals("INT")){
				nodeA =  new IRNode(ISA.STOREI, Integer.parseInt(ctx.expr().getText()), i_dest);
			}else if(dest_token.type.equals("FLOAT")){
				nodeA =  new IRNode(ISA.STOREF, Float.parseFloat(ctx.expr().getText()), i_dest);
			}
			_List.add(nodeA);
			System.out.println(nodeA.toString());

		}else{
			
		}
		
		IRNode nodeB = new IRNode(ISA.STOREI, i_dest, dest_token);
		_List.add(nodeB);
		
		System.out.println(nodeB.toString());

		return nodeB;
	}

	/*
	 *  Generate expression IR nodes and add them to IR list
	 *  @param scope : the current scope
	 *  @param ctx: the expression parse subtree context
	 *  @return last node in the list
	 */
	public void attach_Expressions(SymbolTable scope, MicroParser.ExprContext expr){
		attach_Factor(scope, expr.factor());
		attach_ExprPrefix(scope, expr.expr_prefix());
	}

	private void attach_Factor(SymbolTable scope, MicroParser.FactorContext factor) {
		if(factor == null) return;
		attach_FactorPrefix(scope, factor.factor_prefix());
		attach_PostfixExpr(scope, factor.postfix_expr());
	}
	
	private void attach_PostfixExpr(SymbolTable scope, MicroParser.Postfix_exprContext postfix_expr) {
		if(postfix_expr == null) return;
		System.out.println(postfix_expr.primary().getText());
		System.out.println(postfix_expr.call_expr().getText());
	}

	private void attach_FactorPrefix(SymbolTable scope, MicroParser.Factor_prefixContext factor_prefix) {
		if(factor_prefix == null) return;
		attach_FactorPrefix(scope, factor_prefix.factor_prefix());
		attach_PostfixExpr(scope, factor_prefix.postfix_expr());
	}

	public void attach_ExprPrefix(SymbolTable scope, MicroParser.Expr_prefixContext expr_prefix){
		if(expr_prefix == null) return;
		attach_ExprPrefix(scope, expr_prefix.expr_prefix());
		attach_Factor(scope, expr_prefix.factor());
		attach_addop(scope, expr_prefix.addop());
	}
	
	private void attach_addop(SymbolTable scope, MicroParser.AddopContext addop) {
		if(addop == null) return;
		System.out.println(addop.getText());
	}

	public IRNode attach_Read(SymbolTable scope, MicroParser.Assign_exprContext ctx){
		return null;
		
	}
	
	public IRNode attach_Write(SymbolTable scope, MicroParser.Assign_exprContext ctx){
		return null;
		
	}
}

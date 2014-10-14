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
		Id dest_token = scope.search(ctx.id().getText());
				
		if(dest_token == null) {
			System.out.println("Symbol not found: " + ctx.id().getText());
			System.exit(1);
		}
		
		IRNode nodeA = null;
		IROperand v = attach_Expressions(scope, ctx.expr());
		Register i_dest = null;
		if(ctx.expr().expr_prefix().factor() == null){
			//Direct assignment
			i_dest = TempRegisterFactory.create();
			if(dest_token.type.equals("INT")){
				nodeA =  new IRNode(ISA.STOREI, Integer.parseInt(ctx.expr().getText()), i_dest);
			}else if(dest_token.type.equals("FLOAT")){
				nodeA =  new IRNode(ISA.STOREF, Float.parseFloat(ctx.expr().getText()), i_dest);
			}
			_List.add(nodeA);
			System.out.println(nodeA.toString());
		}else{
			//Expression assignment
			if(v == null) return null; //TODO: and exit?
			i_dest = v._reg;
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
	public IROperand attach_Expressions(SymbolTable scope, MicroParser.ExprContext expr){
		
		//System.out.format("Processing %s\n", expr.expr_prefix().getText());
		IROperand dleft = attach_ExprPrefix(scope, expr.expr_prefix());
		IROperand dright = attach_Factor(scope, expr.factor());
		
		if(dleft == null || dright == null){
			return null;
		}
		
		Register dest = TempRegisterFactory.create();
		IRNode N = new IRNode(ISA.ADDI, dleft._reg, dright._reg, dest);
		System.out.println(N.toString());
		_List.add(N);
		return new IROperand(dest);
	}

	private IROperand attach_Factor(SymbolTable scope, MicroParser.FactorContext factor) {
		if(factor == null) return null;
		//check left subtree (this will also populate LL)
		//System.out.println("Processing " + factor.getText());
		if(factor.factor_prefix().getText().length() == 0) return null;  //TODO : ugly
		IROperand left = FactorPrefix(scope, factor.factor_prefix()); 
		Id right = scope.search(factor.postfix_expr().primary().getText());
		//join two subtrees into one IR node
		IRNode K = null;
		Register dest = TempRegisterFactory.create();
		if(left._id != null){
			K = new IRNode(ISA.MULTI, left._id, right, dest);
		}else{
			System.out.println("Error: not implemented yet");
		}
		
		//final add to LL
		System.out.println(K.toString());
		_List.add(K);
		return new IROperand(dest);
	}
		
	private void attach_CallExpr(SymbolTable scope, MicroParser.Call_exprContext call_expr) {
		if(call_expr == null) return;	
	}


	private IROperand FactorPrefix(SymbolTable scope, MicroParser.Factor_prefixContext factor_prefix) {
		if(factor_prefix.getText().length() == 0) return null;
		
		if(factor_prefix.factor_prefix().getText().length() == 0){
			//has no more left recursion
			String token_str = factor_prefix.postfix_expr().getText();
			return new IROperand(scope.search(token_str));
		}

		//generate IRNode
		//TODO: support MULTF
		Id fact = FactorPrefix(scope, factor_prefix.factor_prefix())._id;
		Id postfix = scope.search(factor_prefix.postfix_expr().getText());
		Register dest = TempRegisterFactory.create();
		IRNode irn = new IRNode(ISA.MULTI, fact, postfix, dest);
		System.out.println(irn.toString());
		_List.add(irn);
		
		return new IROperand(dest);
	}

	/*
	 * Left hand side of ADD 
	 */
	public IROperand attach_ExprPrefix(SymbolTable scope, MicroParser.Expr_prefixContext expr_prefix){
		if(expr_prefix.getText().length() == 0) return null;
		if(expr_prefix.expr_prefix().getText().length() == 0){
			//has no more left recursion, so we go right
			return attach_Factor(scope, expr_prefix.factor());
		}
		
		IROperand left = attach_ExprPrefix(scope, expr_prefix.expr_prefix());
		IROperand right = attach_Factor(scope, expr_prefix.factor());
		Register dest = TempRegisterFactory.create();
		//TODO: handle case where right of expression is lambda
		IRNode irn = new IRNode(ISA.ADDI, left._id, right._id, dest);
		System.out.println(irn.toString());	
		return new IROperand(dest);
	}
	
	/* 
	 * Grammar : ( READ BROPEN id_list BRCLOSE SEMI );
	 */
	public void attach_Read(SymbolTable scope, MicroParser.Read_stmtContext rstmt){
		String [] ids = rstmt.id_list().getText().split(",");
		for(int i = 0;i < ids.length; i++){
			String token_name = ids[i];
			IRNode n = new IRNode(ISA.READI, scope.search(token_name));
			_List.add(n);
			System.out.println(n.toString());
		}
		
	}
	
	public IRNode attach_Write(SymbolTable scope, MicroParser.Assign_exprContext ctx){
		return null;
		
	}
	
	class IROperand{
		public Register _reg;
		public Id _id;
		public IROperand(Register r){
			_reg = r;
		}
		public IROperand(Id id){
			_id = id;
		}
	}
}

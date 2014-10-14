import java.util.LinkedList;

import ssabpisa.ece468.isa.ISA;
import ssabpisa.ece468.isa.Instruction;

public class IRList {
	private LinkedList<IRNode> _List;

	public IRList(){
		_List = new LinkedList<IRNode>();
	}
	
	public void LABEL(String L){
		_List.add(new IRNode(L));
		_List.add(new IRNode(ISA.LINK)); //TODO: what is this 
	}
	
	public void Trace(String L){
		_List.add(new IRNode(L));
	}
	
	public void RET(){
		_List.add(new IRNode(ISA.RET));
	}
	
	public IRNode head(){
		return _List.getFirst();
	}
	
	public IRNode get(int i){
		return _List.get(i);
	}
	
	public int size(){
		return _List.size();
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
			System.err.println("Symbol not found: " + ctx.id().getText());
			System.exit(1);
		}
		
		IRNode nodeA = null;
		IRDest v = attach_Expressions(scope, ctx.expr());
		Register i_dest = null;
		
		
		/*if(){ //no addition 
			//Direct assignment
			i_dest = TempRegisterFactory.create();
			if(dest_token.type.equals("INT")){
				nodeA =  new IRNode(ISA.STOREI, Integer.parseInt(ctx.expr().getText()), i_dest);
			}else if(dest_token.type.equals("FLOAT")){
				nodeA =  new IRNode(ISA.STOREF, Float.parseFloat(ctx.expr().getText()), i_dest);
			}
			_List.add(nodeA);
		}else{
			//Expression assignment
			if(v == null) return null; //TODO: and exit?
			i_dest = v._reg;
		}
		*/
		
		if(v == null){
			System.err.print("Expression returned null\n");//TODO: and exit?
			return null;
		}
		i_dest = v._reg;
		IRNode nodeB = new IRNode(ISA.STOREI, i_dest, dest_token);
		_List.add(nodeB);
		
		return null;
	}

	/*
	 *  Generate expression IR nodes and add them to IR list
	 *  @param scope : the current scope
	 *  @param ctx: the expression parse subtree context
	 *  @return last node in the list
	 */
	public IRDest attach_Expressions(SymbolTable scope, MicroParser.ExprContext expr){
		
		System.out.format("Processing %s\n", expr.expr_prefix().getText());
		IRDest dleft = attach_ExprPrefix(scope, expr.expr_prefix());
		IRDest dright = attach_Factor(scope, expr.factor());
		
		if(dleft == null && dright == null){
			return null;
		}
		if(dleft == null){
			//no expr_prefix,  like a*b or 1
			return dright;
		}
		
		if(dright == null){
			return dleft;
		}
		
		Register dest = TempRegisterFactory.create();
		IRNode N = new IRNode(ISA.ADDI, dleft._reg, dright._reg, dest);
		_List.add(N);
		return new IRDest(dest);
	}
	
	/*
	 * 
	 * returns whatever the destination register becomes for the post-fix subtree (Right child of factor)
	 * @return IROperand
	 */
	private IRDest attach_PostfixExpr(SymbolTable scope, MicroParser.Postfix_exprContext postfix){
		 if(postfix.primary().expr() != null){
				//Not implemented
				System.out.println(postfix.primary().expr().getText());

		} if(postfix.primary().FLOATLITERAL() != null){
			//we detect a float literal, must be loaded to Temp Register
			Register temp = TempRegisterFactory.create();
			_List.add(new IRNode(ISA.STOREF, Integer.parseInt(postfix.primary().FLOATLITERAL().getText()) , temp));
			return new IRDest(temp);
		}else if(postfix.primary().INTLITERAL() != null){
			//we detect an int literal, must be loaded to Temp Register
			Register temp = TempRegisterFactory.create();
			_List.add(new IRNode(ISA.STOREI, Integer.parseInt(postfix.primary().INTLITERAL().getText()) , temp));
			return new IRDest(temp);
		}else if(postfix.primary().id() != null){
			//we detect an id, DO NOT LOAD to register
			return new IRDest(scope.search(postfix.primary().id().getText()));
		}
		return null;
	}
	
	private IRDest attach_Factor(SymbolTable scope, MicroParser.FactorContext factor) {
	
		if(factor == null) return null;
		//if left subtree is empty
		
		if(factor.factor_prefix().getText().length() == 0){
			//search right subtree
			return attach_PostfixExpr(scope, factor.postfix_expr());
		}
		//left subtree isn't empty, search it for
		IRDest fp = FactorPrefix(scope, factor.factor_prefix()); 
		//do the same for the right subtree
		IRDest postfix = attach_PostfixExpr(scope, factor.postfix_expr());
		Id right = postfix._id;//scope.search(factor.postfix_expr().primary().getText());
		//join two subtrees into one IR node
		IRNode K = null;
		Register dest = TempRegisterFactory.create();

		if(fp._id != null){
			//take the left subtree and factor it to the right subtree
			Instruction op = ISA.MULTI;
			if(factor.factor_prefix().mulop().getText().equals("/")){
				op = ISA.DIVI;
			}
			K = new IRNode(op, fp._id, right, dest);
		}else{
			
		}
		
		_List.add(K);
		return new IRDest(dest);
	}
		
	private void attach_CallExpr(SymbolTable scope, MicroParser.Call_exprContext call_expr) {
		//Not implemented yet
		if(call_expr == null) return;	
	}


	private IRDest FactorPrefix(SymbolTable scope, MicroParser.Factor_prefixContext factor_prefix) {
		if(factor_prefix.getText().length() == 0) return null;
		
		if(factor_prefix.factor_prefix().getText().length() == 0){
			//has no more left recursion
			String token_str = factor_prefix.postfix_expr().getText();
			return new IRDest(scope.search(token_str));
		}

		//generate IRNode
		//TODO: support MULTF
		Id fact = FactorPrefix(scope, factor_prefix.factor_prefix())._id;
		Id postfix = scope.search(factor_prefix.postfix_expr().getText());
		Register dest = TempRegisterFactory.create();
		IRNode irn = new IRNode(ISA.MULTI, fact, postfix, dest);
		_List.add(irn);
		
		return new IRDest(dest);
	}

	/*
	 * Left hand side of ADD 
	 */
	public IRDest attach_ExprPrefix(SymbolTable scope, MicroParser.Expr_prefixContext expr_prefix){
		if(expr_prefix.getText().length() == 0) return null;
		if(expr_prefix.expr_prefix().getText().length() == 0){
			//has no more left recursion, so we go right
			return attach_Factor(scope, expr_prefix.factor());
		}
		
		IRDest left = attach_ExprPrefix(scope, expr_prefix.expr_prefix());
		IRDest right = attach_Factor(scope, expr_prefix.factor());
		Register dest = TempRegisterFactory.create();
		//TODO: handle case where right of expression is lambda
		IRNode irn = null;
		if(left._id != null && right._id != null)
			 irn = new IRNode(ISA.ADDI, left._id, right._id, dest);
		if(left._reg != null && right._reg != null)
			irn = new IRNode(ISA.ADDI, left._reg, right._reg, dest);
		
		/*if(left._id != null && right._reg != null)
			irn = new IRNode(ISA.ADDI, left._id, right._reg, dest);
		if(left._reg != null && right._id != null)
			irn = new IRNode(ISA.ADDI, left._reg, right._id, dest);*/

		_List.add(irn);
		return new IRDest(dest);
	}
	
	/* 
	 * Grammar : ( READ BROPEN id_list BRCLOSE SEMI );
	 */
	public void attach_Read(SymbolTable scope, MicroParser.Read_stmtContext rstmt){
		String [] ids = rstmt.id_list().getText().split(","); //TODO: ugly
		for(int i = 0;i < ids.length; i++){
			String token_name = ids[i];
			IRNode n = new IRNode(ISA.READI, scope.search(token_name));
			_List.add(n);
		}
		
	}
	
	public void attach_Write(SymbolTable scope, MicroParser.Write_stmtContext wstmt){
		String [] ids = wstmt.id_list().getText().split(",");
		for(int i = 0;i < ids.length; i++){
			String token_name = ids[i];
			Id token = scope.search(token_name);
			IRNode n = null;
			if(token.type.equals("FLOAT")){
				n = new IRNode(ISA.WRITEF, token);
			}else if(token.type.equals("INT")){
				n = new IRNode(ISA.WRITEI, token);
			}else if(token.type.equals("STRING")){
				n = new IRNode(ISA.WRITES, token);
			}
			_List.add(n);
		}
		
	}
	
	class IRDest{
		public Register _reg;
		public Id _id;
		public IRDest(Register r){
			_reg = r;
		}
		public IRDest(Id id){
			_id = id;
		}
	}
}

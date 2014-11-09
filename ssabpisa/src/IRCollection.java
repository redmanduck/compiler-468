import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class IRCollection implements Iterable<IRNode>{
	private ArrayList<IRNode> _List;

	public IRCollection(){
		_List = new ArrayList<IRNode>();
	}
	
	public void LABEL(String L){
		_List.add(new IRNode(L));
	}
	
	public void LINK(){
		_List.add(new IRNode(ISA.LINK)); 
	}
	
	public void Trace(String L){
		_List.add(new IRNode(L));
	}
	
	public void RET(){
		_List.add(new IRNode(ISA.RET));
	}
	
	public IRNode head(){
		return _List.get(0);
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
				
		IRDest v = attach_Expressions(scope, ctx.expr());
		Register i_dest = null;

		if(v == null){
			System.err.print("Expression returned null\n");//TODO: and exit?
			return null;
		}
		i_dest = v._reg;
		
		Instruction op  = ISA.STOREI;
		if(dest_token.type.equals("FLOAT")){
			op = ISA.STOREF;
		}
		IRNode nodeB = null;
		
		if(i_dest != null){
			 nodeB = new IRNode(op, i_dest, dest_token);
		}else{
			nodeB = new IRNode(op, v._id, dest_token);
		}
		

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
		IRDest dleft = attach_ExprPrefix(scope, expr.expr_prefix());
		IRDest dright = attach_Factor(scope, expr.factor());
				
		if(dleft == null && dright == null){
			return null;
		}
		
		if(dleft == null){
			return dright;
		}
		
		if(dright == null){
			return dleft;
		}
		
		IRNode N = null;
		
		
		
		Instruction op = ISA.transform_type(ISA.ADDI, dleft.getDataTypePrecedence(), dright.getDataTypePrecedence());
		
		if(expr.expr_prefix().addop().getText().equals("-")) 
			op = ISA.transform_type(ISA.SUBI, dleft.getDataTypePrecedence(), dright.getDataTypePrecedence());
		
		Register dest = TempRegisterFactory.create(op.supported_type);
		
		if(dleft._reg != null && dright._reg != null){
			N = new IRNode(op, dleft._reg, dright._reg, dest);
		}else if(dleft._id != null && dright._id != null){
			N = new IRNode(op, dleft._id, dright._id, dest);
		}else if(dleft._id != null && dright._reg != null){
			N = new IRNode(op, dleft._id, dright._reg, dest);
		}else if(dleft._reg != null && dright._id != null){
			N = new IRNode(op, dleft._reg, dright._id, dest);
		}

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
			 return attach_Expressions(scope, postfix.primary().expr()); 
		}else if(postfix.primary().FLOATLITERAL() != null){
			//we detect a float literal, must be loaded to Temp Register
			Register temp = TempRegisterFactory.create("FLOAT");
			_List.add(new IRNode(ISA.STOREF, Float.parseFloat(postfix.primary().FLOATLITERAL().getText()) , temp));
			return new IRDest(temp);
		}else if(postfix.primary().INTLITERAL() != null){
			//we detect an int literal, must be loaded to Temp Register
			Register temp = TempRegisterFactory.create("INT");
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
		
		if(factor.factor_prefix().children == null){
			//search right subtree
			//System.out.println("Searching postfix_expr");
			IRDest d =  attach_PostfixExpr(scope, factor.postfix_expr());
			///System.out.print("PostfixExpr returned: ");
			///System.out.print(d);
			///System.out.println();
			return d;
		}
		
		//left subtree isn't empty, search it for
		IRDest fp = attach_FactorPrefix(scope, factor.factor_prefix()); 
		//do the same for the right subtree
		IRDest postfix = attach_PostfixExpr(scope, factor.postfix_expr());
		
		
		//join two subtrees into one IR node
		IRNode K = null;
		
		
		Instruction op = ISA.transform_type(ISA.MULTI, fp.getDataTypePrecedence(), postfix.getDataTypePrecedence());
		
		if(factor.factor_prefix().mulop().getText().equals("/")){
			op = ISA.transform_type(ISA.DIVI, fp.getDataTypePrecedence(), postfix.getDataTypePrecedence());
		}
		
		Register dest = TempRegisterFactory.create(op.supported_type);
		
		if(fp._id != null && postfix._id != null){
			K = new IRNode(op, fp._id, postfix._id, dest);
		}else if(fp._reg != null && postfix._reg != null){
			
			K = new IRNode(op, fp._reg, postfix._reg, dest);
		}else if(fp._id != null && postfix._reg != null){
			
			K = new IRNode(op, fp._id, postfix._reg, dest);
		}else if(fp._reg != null && postfix._id != null){
			
			K = new IRNode(op, fp._reg, postfix._id, dest);
		}else{
			System.err.println("Something gone wrong");
		}
		
		_List.add(K);
		
		return new IRDest(dest);
	}
		
	private void attach_CallExpr(SymbolTable scope, MicroParser.Call_exprContext call_expr) {
		//Not implemented yet
		if(call_expr == null) return;	
	}


	private IRDest attach_FactorPrefix(SymbolTable scope, MicroParser.Factor_prefixContext self) {
		if(self.children == null) return null;
		
		if(self.factor_prefix().children == null){
			return attach_PostfixExpr(scope, self.postfix_expr());
		}

		//generate IRNode
		//TODO: support MULTF
		
		IRDest fact = attach_FactorPrefix(scope, self.factor_prefix());
		IRDest postfix = attach_PostfixExpr(scope, self.postfix_expr());
		
		Register dest = TempRegisterFactory.create("INT");
		
		IRNode irn = null;
		if(fact._reg != null && postfix._reg != null){
			irn = new IRNode(ISA.MULTI, fact._reg, postfix._reg, dest);
		}else if(fact._reg != null && postfix._id != null){
			irn = new IRNode(ISA.MULTI, fact._reg, postfix._id, dest);
		}else if(fact._id != null && postfix._reg != null){
			irn = new IRNode(ISA.MULTI, fact._id, postfix._reg, dest);
		}else if(fact._id != null && postfix._id != null){
			irn = new IRNode(ISA.MULTI, fact._id, postfix._id, dest);
		}else{
			//TODO: ERROR
			System.err.println("Something went wrong!");
		}
		
		_List.add(irn);
		
		return new IRDest(dest);
	}

	/*
	 * Left hand side of ADD 
	 */
	public IRDest attach_ExprPrefix(SymbolTable scope, MicroParser.Expr_prefixContext expr_prefix){
		if(expr_prefix.children == null) return null;
		
		if(expr_prefix.expr_prefix().children == null){
			//has no more left recursion, so we go right
			return attach_Factor(scope, expr_prefix.factor());
		}
		
		IRDest left = attach_ExprPrefix(scope, expr_prefix.expr_prefix());
		IRDest right = attach_Factor(scope, expr_prefix.factor());
		//TODO: handle case where right of expression is lambda
		
		IRNode irn = null;
		Instruction op = ISA.transform_type(ISA.ADDI, left.getDataTypePrecedence(), right.getDataTypePrecedence());
		Register dest = TempRegisterFactory.create(op.supported_type);
	
		if(expr_prefix.expr_prefix().addop().getText().equals("-")) op = ISA.SUBI;
		
		if(left._id != null && right._id != null){
			irn = new IRNode(op, left._id, right._id, dest);
		}else if(left._reg != null && right._reg != null){
			irn = new IRNode(op, left._reg, right._reg, dest);
		}else if(left._id != null && right._reg != null){
			irn = new IRNode(op, left._id, right._reg, dest);
		}else if(left._reg != null && right._id != null){
			irn = new IRNode(op, left._reg, right._id, dest);
		}else{
			System.err.println("Something went wrong");
		}
		
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
	
	

	@Override
	public Iterator<IRNode> iterator() {
		Iterator<IRNode> inode = _List.iterator();
        return inode; 

	}

	public void attach_LE(IRDest left, IRDest right, String string) {
		Instruction le;

		if(left.isFloat() || right.isFloat()){
			le = ISA.LEF;
		}else{
			le = ISA.LEI;
		}
		IRNode n = null;
		if(left._reg != null && right._reg != null){
			n = new IRNode(le, left._reg, right._reg, string);
		}else if(left._reg != null && right._id != null){
			
			n = new IRNode(le, left._reg, right._id, string);
		}else if(left._id != null && right._reg != null){
			
			n = new IRNode(le, left._id, right._reg, string);
		}else if(left._id != null && right._id != null){
			
			n = new IRNode(le, left._id, right._id, string);
		}
		_List.add(n);
	}
	
	public void attach_GE(IRDest left, IRDest right, String string) {
		Instruction ge;
		if(left.isFloat() || right.isFloat()){
			ge = ISA.GEF;
		}else{
			ge = ISA.GEI;
		}
		IRNode n = null;
		if(left._reg != null && right._reg != null){
			n = new IRNode(ge, left._reg, right._reg, string);
		}else if(left._reg != null && right._id != null){
			
			n = new IRNode(ge, left._reg, right._id, string);
		}else if(left._id != null && right._reg != null){
			
			n = new IRNode(ge, left._id, right._reg, string);
		}else if(left._id != null && right._id != null){
			
			n = new IRNode(ge, left._id, right._id, string);
		}
		_List.add(n);
	}

	public void attach_Jump(String targ) {
		_List.add(new IRNode(ISA.JUMP, targ));
	}

	public void attach_EQI(IRDest left, IRDest right, String string) {
		IRNode n = null;
		if(left._reg != null && right._reg != null){
			n = new IRNode(ISA.EQI, left._reg, right._reg, string);
		}else if(left._reg != null && right._id != null){
			
			n = new IRNode(ISA.EQI, left._reg, right._id, string);
		}else if(left._id != null && right._reg != null){
			
			n = new IRNode(ISA.EQI, left._id, right._reg, string);
		}else if(left._id != null && right._id != null){
			
			n = new IRNode(ISA.EQI, left._id, right._id, string);
		}
		_List.add(n);
	}
}

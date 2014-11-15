import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

public class ExtractionListener extends MicroBaseListener {
	private SymbolTable current_scope;
	private String current_function;
	private IRCollection current_ir;
	
	private int blockcount;
	
	public HashMap<String, IRCollection> IRMap;  //indexed by Function Name -> IR list
	
	private Stack<String> if_else_label_stk;
	private Queue<String> while_label_stk;

		
	public ExtractionListener(MicroParser psr) {
		current_scope = new SymbolTable(null, "GLOBAL");
		this.blockcount = 0;
		this.if_else_label_stk = new Stack<String>();
		this.while_label_stk = new LinkedList<String>();
		this.IRMap = new HashMap<String, IRCollection>();
	}
//
//	public SymbolTable getRootSymbolTable() {
//		return root;
//	}
	
	public HashMap<String, IRCollection> getFullIR() {
		/*
		 * assuming main will be the main IR
		 */
		
		return IRMap; 
	}

	private void enterScope(String scopename) {
		SymbolTable newscope = new SymbolTable(current_scope, scopename);
		current_scope.AddChild(newscope);
		current_scope = newscope;
		// add new label

		//System.out.println("ENTERING SCOPE: " + current_scope.scopename);

	}

	private void leaveScope() {
		//System.out.println("LEAVING SCOPE: " + current_scope.scopename);
		if (current_scope != null)
			current_scope = current_scope.parent;
	}

	public void enterString_decl(@NotNull MicroParser.String_declContext ctx) {
		// System.out.print(current_scope.scopename + " -> (STR)");
		String token_name = ctx.id().getText();
		String token_value = ctx.str().getText();

		boolean result = current_scope.AddSymbolToTable("STRING", token_name,
				token_value);
		if (!result) {
			//System.out.println("DECLARATION ERROR " + token_name);
//			root.error = true;
			System.exit(1);
		}
	}

	public void enterVar_decl(@NotNull MicroParser.Var_declContext ctx) {

		ParseTree decl_list = ctx.id_list();

		String[] names = decl_list.getText().split("(/s+)?(,)(/s+)?"); 
		for (String name : names) {
			if (!current_scope.AddSymbolToTable(ctx.var_type().getText(), name)) {
				//System.out.println("DECLARATION ERROR " + name);
//				root.error = true;
				System.exit(1);

			}
		}
	}

	public void enterParam_decl(MicroParser.Param_declContext ctx) {
		boolean result = current_scope.AddParameterToTable(ctx.var_type()
				.getText(), ctx.id().getText()); 
		
		if (!result) {
//			root.error = true;
			System.out.println("DECLARATION ERROR " + ctx.id().getText());
			System.exit(1);
		}
	}

	public void exitPgm_body(MicroParser.Pgm_bodyContext ctx) {
		
		leaveScope();
	}
	
	public void exitReturn_stmt(@NotNull MicroParser.Return_stmtContext ctx){
		/*
		 * move the symbol it want to return into $R
		 */
		
		IRDest return_exp = current_ir.attach_Expressions(current_scope, ctx.expr());
		current_ir.RET(return_exp);
	}
	
	public void exitFunc_body(MicroParser.Func_bodyContext ctx) {
		leaveScope();
	}

	public void enterIf_stmt(MicroParser.If_stmtContext ctx) {
		IRDest right = current_ir.attach_Expressions(current_scope, ctx.cond().expr(1));
		IRDest left = current_ir.attach_Expressions(current_scope, ctx.cond().expr(0));
		
		String generated_label = AutoLabelFactory.create();
		String compop = ctx.cond().compop().getText();

		//generate condition to jump to else part
		//( '<' | '>' | '=' | '!=' | '<=' | '>=' ); //SUPPROT THESE
		if(compop.equals("<")){
			current_ir.attach_GE(left, right, generated_label);  //GEI,GEF
		}else if(compop.equals(">")){
			current_ir.attach_LE(left, right, generated_label); 
		}else if(compop.equals("=")){
			current_ir.attach_NE(left, right, generated_label);  
		}else if(compop.equals("!=")){
			current_ir.attach_EQ(left, right, generated_label);  
		}else if(compop.equals("<=")){
			current_ir.attach_GT(left, right, generated_label); //GTI ,GTF  
		}else if(compop.equals(">=")){
			current_ir.attach_LT(left, right, generated_label);  
		}else{
			System.err.println("Compop not supported! " + compop);
		}
		
		if_else_label_stk.push(generated_label);
		
		enterScope("BLOCK " + ++blockcount);
	}

	public void enterElse_part(@NotNull MicroParser.Else_partContext ctx) {
		if (ctx.decl() == null) {
			//there is no else part
			return;
		}
		leaveScope(); // leave the if scope

		String generated_label = AutoLabelFactory.create();
		String gelse_part = if_else_label_stk.pop();
		if_else_label_stk.push(generated_label);
		
		current_ir.attach_Jump(generated_label);
		current_ir.LABEL(gelse_part);
		enterScope("BLOCK " + ++blockcount);
	}

	public void enterFunc_declarations(@NotNull MicroParser.Func_declarationsContext ctx) {
		if (ctx.func_decl() == null) {
			return;
		}
		System.out.println(";[log] func enter " + ctx.func_decl().id().getText());
		//Update Current Function 
		this.current_function = ctx.func_decl().id().getText();
		//Create new IR
		IRCollection i = new IRCollection();
		
		if(IRMap.containsKey(current_function)){
			System.err.println("Double function declaration!");
			System.exit(1);
		}
		
		IRMap.put(current_function, i);
		current_ir = i;
		enterScope(ctx.func_decl().id().getText());
		
		current_ir.LABEL(current_scope.scopename);
		current_ir.LINK();
	}

	public void enterWhile_stmt(@NotNull MicroParser.While_stmtContext ctx) {
		
		String recompare = AutoLabelFactory.create(); 
		this.while_label_stk.offer(recompare);
		current_ir.LABEL(recompare);
		
		IRDest left = current_ir.attach_Expressions(current_scope, ctx.cond().expr(0));
		IRDest right = current_ir.attach_Expressions(current_scope, ctx.cond().expr(1));
		
		String newlabel = AutoLabelFactory.create(); 
		String compop = ctx.cond().compop().getText();
		
		if(compop.equals("<")){
			current_ir.attach_GE(left, right, newlabel);  //GEI,GEF
		}else if(compop.equals(">")){
			current_ir.attach_LE(left, right, newlabel); 
		}else if(compop.equals("=")){
			current_ir.attach_NE(left, right, newlabel);  
		}else if(compop.equals("!=")){
			current_ir.attach_EQ(left, right, newlabel);  
		}else if(compop.equals("<=")){
			current_ir.attach_GT(left, right, newlabel); //GTI ,GTF  
		}else if(compop.equals(">=")){
			current_ir.attach_LT(left, right, newlabel);  
		}else{
			System.err.println("Compop not supported! " + compop);
		}
		
		while_label_stk.offer(newlabel);
		
		enterScope("WHILE_BLOCK " + ++blockcount);
	}
	

	@Override
	public void exitIf_stmt(@NotNull MicroParser.If_stmtContext ctx) {
		current_ir.LABEL(this.if_else_label_stk.pop());
		leaveScope();
	}

	@Override
	public void exitWhile_stmt(@NotNull MicroParser.While_stmtContext ctx) {
		current_ir.attach_Jump(this.while_label_stk.remove());
		current_ir.LABEL(this.while_label_stk.remove());
		leaveScope();
	}


	@Override
	public void exitWrite_stmt(@NotNull MicroParser.Write_stmtContext ctx) {
		current_ir.attach_Write(current_scope, ctx);
	}

	@Override
	public void exitAssign_expr(MicroParser.Assign_exprContext ctx) {
		current_ir.attach_Assignment(current_scope, ctx);
	}

	@Override
	public void exitRead_stmt(@NotNull MicroParser.Read_stmtContext ctx) {
		current_ir.attach_Read(current_scope, ctx);
	}

}

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

public class ExtractionListener extends MicroBaseListener {
	private SymbolTable current_scope;
	private int blockcount;
	private SymbolTable root;
	private IRCollection irlist;
	
	private Queue<String> if_else_label_stk, while_label_stk;

	public ExtractionListener(MicroParser psr) {
		current_scope = new SymbolTable(null, "GLOBAL");
		irlist = new IRCollection();
		root = current_scope;
		this.blockcount = 0;
		this.if_else_label_stk = new LinkedList<String>();
		this.while_label_stk = new LinkedList<String>();
	}

	public SymbolTable getRootSymbolTable() {
		return root;
	}

	public IRCollection getIRList() {
		return irlist;
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
			root.error = true;
			System.exit(1);
		}
	}

	public void enterVar_decl(@NotNull MicroParser.Var_declContext ctx) {

		ParseTree decl_list = ctx.id_list();

		String[] names = decl_list.getText().split(","); // TODO: ask Milind or
															// TA , this is
															// cheating
		for (String name : names) {
			if (!current_scope.AddSymbolToTable(ctx.var_type().getText(), name)) {
				//System.out.println("DECLARATION ERROR " + name);
				root.error = true;
				System.exit(1);

			}
		}
	}

	public void enterParam_decl(MicroParser.Param_declContext ctx) {
		boolean result = current_scope.AddSymbolToTable(ctx.var_type()
				.getText(), ctx.id().getText()); // TODO: VAR could be INT or
													// FLOAT
		if (!result) {
			root.error = true;
			//System.out.println("DECLARATION ERROR " + ctx.id().getText());
			System.exit(1);
		}
	}

	public void exitPgm_body(MicroParser.Pgm_bodyContext ctx) {
		irlist.RET();
		leaveScope();
	}

	public void exitFunc_body(MicroParser.Func_bodyContext ctx) {
		leaveScope();
	}

	public void enterIf_stmt(MicroParser.If_stmtContext ctx) {
		IRDest right = irlist.attach_Expressions(current_scope, ctx.cond().expr(1));
		IRDest left = irlist.attach_Expressions(current_scope, ctx.cond().expr(0));
		
		String generated_label = "gelse_" + ctx.hashCode();
		//TODO: gotta handle other comparator 
		irlist.attach_GEI(left, right, generated_label);  //generate condition to jump to else part
		
		if_else_label_stk.offer(generated_label);
		
		enterScope("BLOCK " + ++blockcount);
	}

	public void enterElse_part(@NotNull MicroParser.Else_partContext ctx) {
		if (ctx.decl() == null) {
			//there is no else part
			return;
		}
		leaveScope(); // leave the if scope

		String generated_label = "g_leave" + ctx.hashCode();
		String gelse_part = if_else_label_stk.remove();
		if_else_label_stk.offer(generated_label);
		
		irlist.attach_Jump(generated_label);
		irlist.LABEL(gelse_part);
		enterScope("BLOCK " + ++blockcount);
	}

	public void enterFunc_declarations(@NotNull MicroParser.Func_declarationsContext ctx) {
		if (ctx.func_decl() == null) {
			return;
		}
		
		enterScope(ctx.func_decl().id().getText());
		irlist.LABEL(current_scope.scopename);
		irlist.LINK();
	}

	public void enterWhile_stmt(@NotNull MicroParser.While_stmtContext ctx) {
		
		String recompare = "w_enter" + ctx.hashCode(); 
		this.while_label_stk.offer(recompare);
		irlist.LABEL(recompare);
		
		IRDest left = irlist.attach_Expressions(current_scope, ctx.cond().expr(0));
		IRDest right = irlist.attach_Expressions(current_scope, ctx.cond().expr(1));
		
		String newlabel = "w_exit" + ctx.hashCode();
		irlist.attach_EQI(left, right, newlabel); //TODO: handle other comparator
		while_label_stk.offer(newlabel);
		
		enterScope("WHILE_BLOCK " + ++blockcount);
	}
	

	@Override
	public void exitIf_stmt(@NotNull MicroParser.If_stmtContext ctx) {
		irlist.LABEL(this.if_else_label_stk.remove());
		leaveScope();
	}

	@Override
	public void exitWhile_stmt(@NotNull MicroParser.While_stmtContext ctx) {
		irlist.attach_Jump(this.while_label_stk.remove());
		irlist.LABEL(this.while_label_stk.remove());
		leaveScope();
	}


	@Override
	public void exitWrite_stmt(@NotNull MicroParser.Write_stmtContext ctx) {
		irlist.attach_Write(current_scope, ctx);
	}

	@Override
	public void exitAssign_expr(MicroParser.Assign_exprContext ctx) {
		irlist.attach_Assignment(current_scope, ctx);
	}

	@Override
	public void exitRead_stmt(@NotNull MicroParser.Read_stmtContext ctx) {
		irlist.attach_Read(current_scope, ctx);
	}
}

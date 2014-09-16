// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MicroParser}.
 */
public interface MicroListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MicroParser#string_decl}.
	 * @param ctx the parse tree
	 */
	void enterString_decl(@NotNull MicroParser.String_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#string_decl}.
	 * @param ctx the parse tree
	 */
	void exitString_decl(@NotNull MicroParser.String_declContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(@NotNull MicroParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(@NotNull MicroParser.IdContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#id_tail}.
	 * @param ctx the parse tree
	 */
	void enterId_tail(@NotNull MicroParser.Id_tailContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#id_tail}.
	 * @param ctx the parse tree
	 */
	void exitId_tail(@NotNull MicroParser.Id_tailContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#str}.
	 * @param ctx the parse tree
	 */
	void enterStr(@NotNull MicroParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#str}.
	 * @param ctx the parse tree
	 */
	void exitStr(@NotNull MicroParser.StrContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#var_type}.
	 * @param ctx the parse tree
	 */
	void enterVar_type(@NotNull MicroParser.Var_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#var_type}.
	 * @param ctx the parse tree
	 */
	void exitVar_type(@NotNull MicroParser.Var_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#any_type}.
	 * @param ctx the parse tree
	 */
	void enterAny_type(@NotNull MicroParser.Any_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#any_type}.
	 * @param ctx the parse tree
	 */
	void exitAny_type(@NotNull MicroParser.Any_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl(@NotNull MicroParser.Var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl(@NotNull MicroParser.Var_declContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(@NotNull MicroParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(@NotNull MicroParser.CommentContext ctx);

	/**
	 * Enter a parse tree produced by {@link MicroParser#id_list}.
	 * @param ctx the parse tree
	 */
	void enterId_list(@NotNull MicroParser.Id_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroParser#id_list}.
	 * @param ctx the parse tree
	 */
	void exitId_list(@NotNull MicroParser.Id_listContext ctx);
}
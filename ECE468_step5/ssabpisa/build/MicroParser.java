// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PROG=1, STRING=2, RETURN=3, COLEQ=4, SEMI=5, STRINGLITERAL=6, FLOATLITERAL=7, 
		INTLITERAL=8, INT=9, FLOAT=10, VOID=11, LESSTHAN=12, MORETHAN=13, NOTEQUAL=14, 
		EQUAL=15, ASKT=16, FORESLASH=17, COMMA=18, MORETHAN_EQ=19, LESSTHAN_EQ=20, 
		END=21, BEGIN=22, MINUS=23, PLUS=24, READ=25, WRITE=26, BROPEN=27, BRCLOSE=28, 
		FUNCTION=29, IF=30, ELSE=31, ENDIF=32, WHILE=33, ENDWHILE=34, IDENTIFIER=35, 
		COMMENT=36, CONTINUE=37, OPERATOR=38, KEYWORD=39, WS=40, BREAK=41;
	public static final String[] tokenNames = {
		"<INVALID>", "'PROGRAM'", "'STRING'", "'RETURN'", "':='", "';'", "STRINGLITERAL", 
		"FLOATLITERAL", "INTLITERAL", "'INT'", "'FLOAT'", "'VOID'", "'<'", "'>'", 
		"'!='", "'='", "'*'", "'/'", "','", "'>='", "'<='", "'END'", "'BEGIN'", 
		"'-'", "'+'", "'READ'", "'WRITE'", "'('", "')'", "'FUNCTION'", "'IF'", 
		"'ELSE'", "'ENDIF'", "'WHILE'", "'ENDWHILE'", "IDENTIFIER", "COMMENT", 
		"'CONTINUE'", "OPERATOR", "KEYWORD", "WS", "'BREAK'"
	};
	public static final int
		RULE_program = 0, RULE_id = 1, RULE_pgm_body = 2, RULE_decl = 3, RULE_comment = 4, 
		RULE_string_decl = 5, RULE_str = 6, RULE_var_decl = 7, RULE_var_type = 8, 
		RULE_any_type = 9, RULE_id_list = 10, RULE_id_tail = 11, RULE_param_decl_list = 12, 
		RULE_param_decl = 13, RULE_param_decl_tail = 14, RULE_func_declarations = 15, 
		RULE_func_decl = 16, RULE_func_body = 17, RULE_stmt_list = 18, RULE_stmt = 19, 
		RULE_base_stmt = 20, RULE_assign_stmt = 21, RULE_assign_expr = 22, RULE_read_stmt = 23, 
		RULE_write_stmt = 24, RULE_return_stmt = 25, RULE_expr = 26, RULE_expr_prefix = 27, 
		RULE_factor = 28, RULE_factor_prefix = 29, RULE_postfix_expr = 30, RULE_call_expr = 31, 
		RULE_expr_list = 32, RULE_expr_list_tail = 33, RULE_primary = 34, RULE_addop = 35, 
		RULE_mulop = 36, RULE_if_stmt = 37, RULE_else_part = 38, RULE_cond = 39, 
		RULE_compop = 40, RULE_while_stmt = 41, RULE_aug_break = 42, RULE_aug_continue = 43;
	public static final String[] ruleNames = {
		"program", "id", "pgm_body", "decl", "comment", "string_decl", "str", 
		"var_decl", "var_type", "any_type", "id_list", "id_tail", "param_decl_list", 
		"param_decl", "param_decl_tail", "func_declarations", "func_decl", "func_body", 
		"stmt_list", "stmt", "base_stmt", "assign_stmt", "assign_expr", "read_stmt", 
		"write_stmt", "return_stmt", "expr", "expr_prefix", "factor", "factor_prefix", 
		"postfix_expr", "call_expr", "expr_list", "expr_list_tail", "primary", 
		"addop", "mulop", "if_stmt", "else_part", "cond", "compop", "while_stmt", 
		"aug_break", "aug_continue"
	};

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public MicroParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode PROG() { return getToken(MicroParser.PROG, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Pgm_bodyContext pgm_body() {
			return getRuleContext(Pgm_bodyContext.class,0);
		}
		public TerminalNode BEGIN() { return getToken(MicroParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(MicroParser.END, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(88); match(PROG);
			setState(89); id();
			setState(90); match(BEGIN);
			setState(91); pgm_body();
			setState(92); match(END);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(MicroParser.IDENTIFIER, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(94); match(IDENTIFIER);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pgm_bodyContext extends ParserRuleContext {
		public Func_declarationsContext func_declarations() {
			return getRuleContext(Func_declarationsContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public Pgm_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pgm_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPgm_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPgm_body(this);
		}
	}

	public final Pgm_bodyContext pgm_body() throws RecognitionException {
		Pgm_bodyContext _localctx = new Pgm_bodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pgm_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(96); decl();
			setState(97); func_declarations();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public String_declContext string_decl() {
			return getRuleContext(String_declContext.class,0);
		}
		public Var_declContext var_decl() {
			return getRuleContext(Var_declContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(99); string_decl();
				setState(100); decl();
				}
				break;
			case INT:
			case FLOAT:
				{
				setState(102); var_decl();
				setState(103); decl();
				}
				break;
			case RETURN:
			case END:
			case READ:
			case WRITE:
			case FUNCTION:
			case IF:
			case ELSE:
			case ENDIF:
			case WHILE:
			case ENDWHILE:
			case IDENTIFIER:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(MicroParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_comment);
		try {
			setState(110);
			switch (_input.LA(1)) {
			case COMMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(108); match(COMMENT);
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_declContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public TerminalNode COLEQ() { return getToken(MicroParser.COLEQ, 0); }
		public TerminalNode SEMI() { return getToken(MicroParser.SEMI, 0); }
		public StrContext str() {
			return getRuleContext(StrContext.class,0);
		}
		public TerminalNode STRING() { return getToken(MicroParser.STRING, 0); }
		public String_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterString_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitString_decl(this);
		}
	}

	public final String_declContext string_decl() throws RecognitionException {
		String_declContext _localctx = new String_declContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_string_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(112); match(STRING);
			setState(113); id();
			}
			setState(115); match(COLEQ);
			setState(116); str();
			setState(117); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrContext extends ParserRuleContext {
		public TerminalNode STRINGLITERAL() { return getToken(MicroParser.STRINGLITERAL, 0); }
		public StrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_str; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStr(this);
		}
	}

	public final StrContext str() throws RecognitionException {
		StrContext _localctx = new StrContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_str);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(119); match(STRINGLITERAL);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(MicroParser.SEMI, 0); }
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterVar_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitVar_decl(this);
		}
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_var_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(121); var_type();
			setState(122); id_list();
			setState(123); match(SEMI);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_typeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(MicroParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(MicroParser.FLOAT, 0); }
		public Var_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterVar_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitVar_type(this);
		}
	}

	public final Var_typeContext var_type() throws RecognitionException {
		Var_typeContext _localctx = new Var_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_var_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Any_typeContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(MicroParser.VOID, 0); }
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
		public Any_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAny_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAny_type(this);
		}
	}

	public final Any_typeContext any_type() throws RecognitionException {
		Any_typeContext _localctx = new Any_typeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_any_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
			switch (_input.LA(1)) {
			case INT:
			case FLOAT:
				{
				setState(127); var_type();
				}
				break;
			case VOID:
				{
				setState(128); match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_listContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Id_tailContext id_tail() {
			return getRuleContext(Id_tailContext.class,0);
		}
		public Id_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterId_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitId_list(this);
		}
	}

	public final Id_listContext id_list() throws RecognitionException {
		Id_listContext _localctx = new Id_listContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_id_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(131); id();
			setState(132); id_tail();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Id_tailContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Id_tailContext id_tail() {
			return getRuleContext(Id_tailContext.class,0);
		}
		public Id_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterId_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitId_tail(this);
		}
	}

	public final Id_tailContext id_tail() throws RecognitionException {
		Id_tailContext _localctx = new Id_tailContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_id_tail);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			switch (_input.LA(1)) {
			case COMMA:
				{
				setState(134); match(COMMA);
				setState(135); id();
				setState(136); id_tail();
				}
				break;
			case SEMI:
			case BRCLOSE:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_decl_listContext extends ParserRuleContext {
		public Param_decl_tailContext param_decl_tail() {
			return getRuleContext(Param_decl_tailContext.class,0);
		}
		public Param_declContext param_decl() {
			return getRuleContext(Param_declContext.class,0);
		}
		public Param_decl_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_decl_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParam_decl_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParam_decl_list(this);
		}
	}

	public final Param_decl_listContext param_decl_list() throws RecognitionException {
		Param_decl_listContext _localctx = new Param_decl_listContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_param_decl_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			switch (_input.LA(1)) {
			case INT:
			case FLOAT:
				{
				setState(141); param_decl();
				setState(142); param_decl_tail();
				}
				break;
			case BRCLOSE:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_declContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
		public Param_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParam_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParam_decl(this);
		}
	}

	public final Param_declContext param_decl() throws RecognitionException {
		Param_declContext _localctx = new Param_declContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_param_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(147); var_type();
			setState(148); id();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_decl_tailContext extends ParserRuleContext {
		public Param_decl_tailContext param_decl_tail() {
			return getRuleContext(Param_decl_tailContext.class,0);
		}
		public Param_declContext param_decl() {
			return getRuleContext(Param_declContext.class,0);
		}
		public Param_decl_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_decl_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterParam_decl_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitParam_decl_tail(this);
		}
	}

	public final Param_decl_tailContext param_decl_tail() throws RecognitionException {
		Param_decl_tailContext _localctx = new Param_decl_tailContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_param_decl_tail);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			switch (_input.LA(1)) {
			case COMMA:
				{
				setState(150); match(COMMA);
				setState(151); param_decl();
				setState(152); param_decl_tail();
				}
				break;
			case BRCLOSE:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_declarationsContext extends ParserRuleContext {
		public Func_declarationsContext func_declarations() {
			return getRuleContext(Func_declarationsContext.class,0);
		}
		public Func_declContext func_decl() {
			return getRuleContext(Func_declContext.class,0);
		}
		public Func_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunc_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunc_declarations(this);
		}
	}

	public final Func_declarationsContext func_declarations() throws RecognitionException {
		Func_declarationsContext _localctx = new Func_declarationsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_func_declarations);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			switch (_input.LA(1)) {
			case FUNCTION:
				{
				setState(157); func_decl();
				setState(158); func_declarations();
				}
				break;
			case END:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_declContext extends ParserRuleContext {
		public Func_bodyContext func_body() {
			return getRuleContext(Func_bodyContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Param_decl_listContext param_decl_list() {
			return getRuleContext(Param_decl_listContext.class,0);
		}
		public Any_typeContext any_type() {
			return getRuleContext(Any_typeContext.class,0);
		}
		public TerminalNode BROPEN() { return getToken(MicroParser.BROPEN, 0); }
		public TerminalNode FUNCTION() { return getToken(MicroParser.FUNCTION, 0); }
		public TerminalNode BRCLOSE() { return getToken(MicroParser.BRCLOSE, 0); }
		public TerminalNode BEGIN() { return getToken(MicroParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(MicroParser.END, 0); }
		public Func_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunc_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunc_decl(this);
		}
	}

	public final Func_declContext func_decl() throws RecognitionException {
		Func_declContext _localctx = new Func_declContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_func_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(163); match(FUNCTION);
			setState(164); any_type();
			setState(165); id();
			setState(166); match(BROPEN);
			setState(167); param_decl_list();
			setState(168); match(BRCLOSE);
			setState(169); match(BEGIN);
			setState(170); func_body();
			setState(171); match(END);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_bodyContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public Func_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFunc_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFunc_body(this);
		}
	}

	public final Func_bodyContext func_body() throws RecognitionException {
		Func_bodyContext _localctx = new Func_bodyContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_func_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(173); decl();
			setState(174); stmt_list();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Stmt_listContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public Stmt_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStmt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStmt_list(this);
		}
	}

	public final Stmt_listContext stmt_list() throws RecognitionException {
		Stmt_listContext _localctx = new Stmt_listContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_stmt_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			switch (_input.LA(1)) {
			case RETURN:
			case READ:
			case WRITE:
			case IF:
			case WHILE:
			case IDENTIFIER:
				{
				setState(176); stmt();
				setState(177); stmt_list();
				}
				break;
			case END:
			case ELSE:
			case ENDIF:
			case ENDWHILE:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public Base_stmtContext base_stmt() {
			return getRuleContext(Base_stmtContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public While_stmtContext while_stmt() {
			return getRuleContext(While_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			switch (_input.LA(1)) {
			case RETURN:
			case READ:
			case WRITE:
			case IDENTIFIER:
				{
				setState(182); base_stmt();
				}
				break;
			case IF:
				{
				setState(183); if_stmt();
				}
				break;
			case WHILE:
				{
				setState(184); while_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_stmtContext extends ParserRuleContext {
		public Write_stmtContext write_stmt() {
			return getRuleContext(Write_stmtContext.class,0);
		}
		public Read_stmtContext read_stmt() {
			return getRuleContext(Read_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public Assign_stmtContext assign_stmt() {
			return getRuleContext(Assign_stmtContext.class,0);
		}
		public Base_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterBase_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitBase_stmt(this);
		}
	}

	public final Base_stmtContext base_stmt() throws RecognitionException {
		Base_stmtContext _localctx = new Base_stmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_base_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(187); assign_stmt();
				}
				break;
			case READ:
				{
				setState(188); read_stmt();
				}
				break;
			case WRITE:
				{
				setState(189); write_stmt();
				}
				break;
			case RETURN:
				{
				setState(190); return_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_stmtContext extends ParserRuleContext {
		public TerminalNode SEMI() { return getToken(MicroParser.SEMI, 0); }
		public Assign_exprContext assign_expr() {
			return getRuleContext(Assign_exprContext.class,0);
		}
		public Assign_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAssign_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAssign_stmt(this);
		}
	}

	public final Assign_stmtContext assign_stmt() throws RecognitionException {
		Assign_stmtContext _localctx = new Assign_stmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_assign_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(193); assign_expr();
			setState(194); match(SEMI);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_exprContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode COLEQ() { return getToken(MicroParser.COLEQ, 0); }
		public Assign_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAssign_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAssign_expr(this);
		}
	}

	public final Assign_exprContext assign_expr() throws RecognitionException {
		Assign_exprContext _localctx = new Assign_exprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_assign_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(196); id();
			}
			setState(197); match(COLEQ);
			setState(198); expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Read_stmtContext extends ParserRuleContext {
		public TerminalNode BROPEN() { return getToken(MicroParser.BROPEN, 0); }
		public TerminalNode SEMI() { return getToken(MicroParser.SEMI, 0); }
		public TerminalNode READ() { return getToken(MicroParser.READ, 0); }
		public TerminalNode BRCLOSE() { return getToken(MicroParser.BRCLOSE, 0); }
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Read_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_read_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterRead_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitRead_stmt(this);
		}
	}

	public final Read_stmtContext read_stmt() throws RecognitionException {
		Read_stmtContext _localctx = new Read_stmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_read_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(200); match(READ);
			setState(201); match(BROPEN);
			setState(202); id_list();
			setState(203); match(BRCLOSE);
			setState(204); match(SEMI);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Write_stmtContext extends ParserRuleContext {
		public TerminalNode BROPEN() { return getToken(MicroParser.BROPEN, 0); }
		public TerminalNode SEMI() { return getToken(MicroParser.SEMI, 0); }
		public TerminalNode WRITE() { return getToken(MicroParser.WRITE, 0); }
		public TerminalNode BRCLOSE() { return getToken(MicroParser.BRCLOSE, 0); }
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Write_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_write_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterWrite_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitWrite_stmt(this);
		}
	}

	public final Write_stmtContext write_stmt() throws RecognitionException {
		Write_stmtContext _localctx = new Write_stmtContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_write_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(206); match(WRITE);
			setState(207); match(BROPEN);
			setState(208); id_list();
			setState(209); match(BRCLOSE);
			setState(210); match(SEMI);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MicroParser.SEMI, 0); }
		public TerminalNode RETURN() { return getToken(MicroParser.RETURN, 0); }
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitReturn_stmt(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_return_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(212); match(RETURN);
			setState(213); expr();
			setState(214); match(SEMI);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Expr_prefixContext expr_prefix() {
			return getRuleContext(Expr_prefixContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(216); expr_prefix(0);
			setState(217); factor();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_prefixContext extends ParserRuleContext {
		public int _p;
		public Expr_prefixContext expr_prefix() {
			return getRuleContext(Expr_prefixContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public AddopContext addop() {
			return getRuleContext(AddopContext.class,0);
		}
		public Expr_prefixContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Expr_prefixContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr_prefix(this);
		}
	}

	public final Expr_prefixContext expr_prefix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Expr_prefixContext _localctx = new Expr_prefixContext(_ctx, _parentState, _p);
		Expr_prefixContext _prevctx = _localctx;
		int _startState = 54;
		enterRecursionRule(_localctx, RULE_expr_prefix);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(226);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Expr_prefixContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_expr_prefix);
					setState(220);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(221); factor();
					setState(222); addop();
					}
					} 
				}
				setState(228);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public Postfix_exprContext postfix_expr() {
			return getRuleContext(Postfix_exprContext.class,0);
		}
		public Factor_prefixContext factor_prefix() {
			return getRuleContext(Factor_prefixContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(229); factor_prefix(0);
			setState(230); postfix_expr();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Factor_prefixContext extends ParserRuleContext {
		public int _p;
		public MulopContext mulop() {
			return getRuleContext(MulopContext.class,0);
		}
		public Postfix_exprContext postfix_expr() {
			return getRuleContext(Postfix_exprContext.class,0);
		}
		public Factor_prefixContext factor_prefix() {
			return getRuleContext(Factor_prefixContext.class,0);
		}
		public Factor_prefixContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Factor_prefixContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_factor_prefix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterFactor_prefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitFactor_prefix(this);
		}
	}

	public final Factor_prefixContext factor_prefix(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Factor_prefixContext _localctx = new Factor_prefixContext(_ctx, _parentState, _p);
		Factor_prefixContext _prevctx = _localctx;
		int _startState = 58;
		enterRecursionRule(_localctx, RULE_factor_prefix);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(239);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Factor_prefixContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_factor_prefix);
					setState(233);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(234); postfix_expr();
					setState(235); mulop();
					}
					} 
				}
				setState(241);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Postfix_exprContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public Call_exprContext call_expr() {
			return getRuleContext(Call_exprContext.class,0);
		}
		public Postfix_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfix_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPostfix_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPostfix_expr(this);
		}
	}

	public final Postfix_exprContext postfix_expr() throws RecognitionException {
		Postfix_exprContext _localctx = new Postfix_exprContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_postfix_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(242); primary();
				}
				break;

			case 2:
				{
				setState(243); call_expr();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Call_exprContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Expr_listContext expr_list() {
			return getRuleContext(Expr_listContext.class,0);
		}
		public TerminalNode BROPEN() { return getToken(MicroParser.BROPEN, 0); }
		public TerminalNode BRCLOSE() { return getToken(MicroParser.BRCLOSE, 0); }
		public Call_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterCall_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitCall_expr(this);
		}
	}

	public final Call_exprContext call_expr() throws RecognitionException {
		Call_exprContext _localctx = new Call_exprContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_call_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(246); id();
			setState(247); match(BROPEN);
			setState(248); expr_list();
			setState(249); match(BRCLOSE);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_listContext extends ParserRuleContext {
		public Expr_list_tailContext expr_list_tail() {
			return getRuleContext(Expr_list_tailContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr_list(this);
		}
	}

	public final Expr_listContext expr_list() throws RecognitionException {
		Expr_listContext _localctx = new Expr_listContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_expr_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(251); expr();
				setState(252); expr_list_tail();
				}
				break;

			case 2:
				{
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_list_tailContext extends ParserRuleContext {
		public Expr_list_tailContext expr_list_tail() {
			return getRuleContext(Expr_list_tailContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_list_tailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_list_tail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterExpr_list_tail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitExpr_list_tail(this);
		}
	}

	public final Expr_list_tailContext expr_list_tail() throws RecognitionException {
		Expr_list_tailContext _localctx = new Expr_list_tailContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_expr_list_tail);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			switch (_input.LA(1)) {
			case COMMA:
				{
				setState(257); match(COMMA);
				setState(258); expr();
				setState(259); expr_list_tail();
				}
				break;
			case BRCLOSE:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode FLOATLITERAL() { return getToken(MicroParser.FLOATLITERAL, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode INTLITERAL() { return getToken(MicroParser.INTLITERAL, 0); }
		public TerminalNode BROPEN() { return getToken(MicroParser.BROPEN, 0); }
		public TerminalNode BRCLOSE() { return getToken(MicroParser.BRCLOSE, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitPrimary(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_primary);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			switch (_input.LA(1)) {
			case BROPEN:
				{
				setState(264); match(BROPEN);
				setState(265); expr();
				setState(266); match(BRCLOSE);
				}
				break;
			case IDENTIFIER:
				{
				setState(268); id();
				}
				break;
			case INTLITERAL:
				{
				setState(269); match(INTLITERAL);
				}
				break;
			case FLOATLITERAL:
				{
				setState(270); match(FLOATLITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddopContext extends ParserRuleContext {
		public AddopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAddop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAddop(this);
		}
	}

	public final AddopContext addop() throws RecognitionException {
		AddopContext _localctx = new AddopContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_addop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			_la = _input.LA(1);
			if ( !(_la==MINUS || _la==PLUS) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulopContext extends ParserRuleContext {
		public MulopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterMulop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitMulop(this);
		}
	}

	public final MulopContext mulop() throws RecognitionException {
		MulopContext _localctx = new MulopContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_mulop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			_la = _input.LA(1);
			if ( !(_la==ASKT || _la==FORESLASH) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MicroParser.IF, 0); }
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public TerminalNode BROPEN() { return getToken(MicroParser.BROPEN, 0); }
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public TerminalNode ENDIF() { return getToken(MicroParser.ENDIF, 0); }
		public TerminalNode BRCLOSE() { return getToken(MicroParser.BRCLOSE, 0); }
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public Else_partContext else_part() {
			return getRuleContext(Else_partContext.class,0);
		}
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitIf_stmt(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_if_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(277); match(IF);
			setState(278); match(BROPEN);
			setState(279); cond();
			setState(280); match(BRCLOSE);
			setState(281); decl();
			setState(282); stmt_list();
			setState(283); else_part();
			setState(284); match(ENDIF);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Else_partContext extends ParserRuleContext {
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(MicroParser.ELSE, 0); }
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public Else_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterElse_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitElse_part(this);
		}
	}

	public final Else_partContext else_part() throws RecognitionException {
		Else_partContext _localctx = new Else_partContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_else_part);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			switch (_input.LA(1)) {
			case ELSE:
				{
				setState(286); match(ELSE);
				setState(287); decl();
				setState(288); stmt_list();
				}
				break;
			case ENDIF:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondContext extends ParserRuleContext {
		public CompopContext compop() {
			return getRuleContext(CompopContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitCond(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		CondContext _localctx = new CondContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(293); expr();
			setState(294); compop();
			setState(295); expr();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompopContext extends ParserRuleContext {
		public CompopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterCompop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitCompop(this);
		}
	}

	public final CompopContext compop() throws RecognitionException {
		CompopContext _localctx = new CompopContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_compop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LESSTHAN) | (1L << MORETHAN) | (1L << NOTEQUAL) | (1L << EQUAL) | (1L << MORETHAN_EQ) | (1L << LESSTHAN_EQ))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_stmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MicroParser.WHILE, 0); }
		public Stmt_listContext stmt_list() {
			return getRuleContext(Stmt_listContext.class,0);
		}
		public TerminalNode BROPEN() { return getToken(MicroParser.BROPEN, 0); }
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public TerminalNode ENDWHILE() { return getToken(MicroParser.ENDWHILE, 0); }
		public TerminalNode BRCLOSE() { return getToken(MicroParser.BRCLOSE, 0); }
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public While_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterWhile_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitWhile_stmt(this);
		}
	}

	public final While_stmtContext while_stmt() throws RecognitionException {
		While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_while_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(299); match(WHILE);
			setState(300); match(BROPEN);
			setState(301); cond();
			setState(302); match(BRCLOSE);
			setState(303); decl();
			setState(304); stmt_list();
			setState(305); match(ENDWHILE);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aug_breakContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(MicroParser.BREAK, 0); }
		public Aug_breakContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aug_break; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAug_break(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAug_break(this);
		}
	}

	public final Aug_breakContext aug_break() throws RecognitionException {
		Aug_breakContext _localctx = new Aug_breakContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_aug_break);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307); match(BREAK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aug_continueContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(MicroParser.CONTINUE, 0); }
		public Aug_continueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aug_continue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).enterAug_continue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MicroListener ) ((MicroListener)listener).exitAug_continue(this);
		}
	}

	public final Aug_continueContext aug_continue() throws RecognitionException {
		Aug_continueContext _localctx = new Aug_continueContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_aug_continue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309); match(CONTINUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 27: return expr_prefix_sempred((Expr_prefixContext)_localctx, predIndex);

		case 29: return factor_prefix_sempred((Factor_prefixContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean factor_prefix_sempred(Factor_prefixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean expr_prefix_sempred(Expr_prefixContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3+\u013a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\5m\n\5\3\6\3\6\5\6q\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\5\13\u0084\n\13\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u008e\n\r\3\16\3\16\3\16\3\16\5\16\u0094\n\16\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\5\20\u009e\n\20\3\21\3\21\3\21\3\21"+
		"\5\21\u00a4\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\5\24\u00b7\n\24\3\25\3\25\3\25\5\25\u00bc"+
		"\n\25\3\26\3\26\3\26\3\26\5\26\u00c2\n\26\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\7\35\u00e3\n\35"+
		"\f\35\16\35\u00e6\13\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\7\37\u00f0"+
		"\n\37\f\37\16\37\u00f3\13\37\3 \3 \5 \u00f7\n \3!\3!\3!\3!\3!\3\"\3\""+
		"\3\"\3\"\5\"\u0102\n\"\3#\3#\3#\3#\3#\5#\u0109\n#\3$\3$\3$\3$\3$\3$\3"+
		"$\5$\u0112\n$\3%\3%\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3"+
		"(\3(\3(\5(\u0126\n(\3)\3)\3)\3)\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3"+
		"-\3-\3-\2.\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<>@BDFHJLNPRTVX\2\6\3\2\13\f\3\2\31\32\3\2\22\23\4\2\16\21\25\26\u0124"+
		"\2Z\3\2\2\2\4`\3\2\2\2\6b\3\2\2\2\bl\3\2\2\2\np\3\2\2\2\fr\3\2\2\2\16"+
		"y\3\2\2\2\20{\3\2\2\2\22\177\3\2\2\2\24\u0083\3\2\2\2\26\u0085\3\2\2\2"+
		"\30\u008d\3\2\2\2\32\u0093\3\2\2\2\34\u0095\3\2\2\2\36\u009d\3\2\2\2 "+
		"\u00a3\3\2\2\2\"\u00a5\3\2\2\2$\u00af\3\2\2\2&\u00b6\3\2\2\2(\u00bb\3"+
		"\2\2\2*\u00c1\3\2\2\2,\u00c3\3\2\2\2.\u00c6\3\2\2\2\60\u00ca\3\2\2\2\62"+
		"\u00d0\3\2\2\2\64\u00d6\3\2\2\2\66\u00da\3\2\2\28\u00dd\3\2\2\2:\u00e7"+
		"\3\2\2\2<\u00ea\3\2\2\2>\u00f6\3\2\2\2@\u00f8\3\2\2\2B\u0101\3\2\2\2D"+
		"\u0108\3\2\2\2F\u0111\3\2\2\2H\u0113\3\2\2\2J\u0115\3\2\2\2L\u0117\3\2"+
		"\2\2N\u0125\3\2\2\2P\u0127\3\2\2\2R\u012b\3\2\2\2T\u012d\3\2\2\2V\u0135"+
		"\3\2\2\2X\u0137\3\2\2\2Z[\7\3\2\2[\\\5\4\3\2\\]\7\30\2\2]^\5\6\4\2^_\7"+
		"\27\2\2_\3\3\2\2\2`a\7%\2\2a\5\3\2\2\2bc\5\b\5\2cd\5 \21\2d\7\3\2\2\2"+
		"ef\5\f\7\2fg\5\b\5\2gm\3\2\2\2hi\5\20\t\2ij\5\b\5\2jm\3\2\2\2km\3\2\2"+
		"\2le\3\2\2\2lh\3\2\2\2lk\3\2\2\2m\t\3\2\2\2nq\7&\2\2oq\3\2\2\2pn\3\2\2"+
		"\2po\3\2\2\2q\13\3\2\2\2rs\7\4\2\2st\5\4\3\2tu\3\2\2\2uv\7\6\2\2vw\5\16"+
		"\b\2wx\7\7\2\2x\r\3\2\2\2yz\7\b\2\2z\17\3\2\2\2{|\5\22\n\2|}\5\26\f\2"+
		"}~\7\7\2\2~\21\3\2\2\2\177\u0080\t\2\2\2\u0080\23\3\2\2\2\u0081\u0084"+
		"\5\22\n\2\u0082\u0084\7\r\2\2\u0083\u0081\3\2\2\2\u0083\u0082\3\2\2\2"+
		"\u0084\25\3\2\2\2\u0085\u0086\5\4\3\2\u0086\u0087\5\30\r\2\u0087\27\3"+
		"\2\2\2\u0088\u0089\7\24\2\2\u0089\u008a\5\4\3\2\u008a\u008b\5\30\r\2\u008b"+
		"\u008e\3\2\2\2\u008c\u008e\3\2\2\2\u008d\u0088\3\2\2\2\u008d\u008c\3\2"+
		"\2\2\u008e\31\3\2\2\2\u008f\u0090\5\34\17\2\u0090\u0091\5\36\20\2\u0091"+
		"\u0094\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u008f\3\2\2\2\u0093\u0092\3\2"+
		"\2\2\u0094\33\3\2\2\2\u0095\u0096\5\22\n\2\u0096\u0097\5\4\3\2\u0097\35"+
		"\3\2\2\2\u0098\u0099\7\24\2\2\u0099\u009a\5\34\17\2\u009a\u009b\5\36\20"+
		"\2\u009b\u009e\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u0098\3\2\2\2\u009d\u009c"+
		"\3\2\2\2\u009e\37\3\2\2\2\u009f\u00a0\5\"\22\2\u00a0\u00a1\5 \21\2\u00a1"+
		"\u00a4\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3\u009f\3\2\2\2\u00a3\u00a2\3\2"+
		"\2\2\u00a4!\3\2\2\2\u00a5\u00a6\7\37\2\2\u00a6\u00a7\5\24\13\2\u00a7\u00a8"+
		"\5\4\3\2\u00a8\u00a9\7\35\2\2\u00a9\u00aa\5\32\16\2\u00aa\u00ab\7\36\2"+
		"\2\u00ab\u00ac\7\30\2\2\u00ac\u00ad\5$\23\2\u00ad\u00ae\7\27\2\2\u00ae"+
		"#\3\2\2\2\u00af\u00b0\5\b\5\2\u00b0\u00b1\5&\24\2\u00b1%\3\2\2\2\u00b2"+
		"\u00b3\5(\25\2\u00b3\u00b4\5&\24\2\u00b4\u00b7\3\2\2\2\u00b5\u00b7\3\2"+
		"\2\2\u00b6\u00b2\3\2\2\2\u00b6\u00b5\3\2\2\2\u00b7\'\3\2\2\2\u00b8\u00bc"+
		"\5*\26\2\u00b9\u00bc\5L\'\2\u00ba\u00bc\5T+\2\u00bb\u00b8\3\2\2\2\u00bb"+
		"\u00b9\3\2\2\2\u00bb\u00ba\3\2\2\2\u00bc)\3\2\2\2\u00bd\u00c2\5,\27\2"+
		"\u00be\u00c2\5\60\31\2\u00bf\u00c2\5\62\32\2\u00c0\u00c2\5\64\33\2\u00c1"+
		"\u00bd\3\2\2\2\u00c1\u00be\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c0\3\2"+
		"\2\2\u00c2+\3\2\2\2\u00c3\u00c4\5.\30\2\u00c4\u00c5\7\7\2\2\u00c5-\3\2"+
		"\2\2\u00c6\u00c7\5\4\3\2\u00c7\u00c8\7\6\2\2\u00c8\u00c9\5\66\34\2\u00c9"+
		"/\3\2\2\2\u00ca\u00cb\7\33\2\2\u00cb\u00cc\7\35\2\2\u00cc\u00cd\5\26\f"+
		"\2\u00cd\u00ce\7\36\2\2\u00ce\u00cf\7\7\2\2\u00cf\61\3\2\2\2\u00d0\u00d1"+
		"\7\34\2\2\u00d1\u00d2\7\35\2\2\u00d2\u00d3\5\26\f\2\u00d3\u00d4\7\36\2"+
		"\2\u00d4\u00d5\7\7\2\2\u00d5\63\3\2\2\2\u00d6\u00d7\7\5\2\2\u00d7\u00d8"+
		"\5\66\34\2\u00d8\u00d9\7\7\2\2\u00d9\65\3\2\2\2\u00da\u00db\58\35\2\u00db"+
		"\u00dc\5:\36\2\u00dc\67\3\2\2\2\u00dd\u00e4\b\35\1\2\u00de\u00df\6\35"+
		"\2\3\u00df\u00e0\5:\36\2\u00e0\u00e1\5H%\2\u00e1\u00e3\3\2\2\2\u00e2\u00de"+
		"\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"9\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e8\5<\37\2\u00e8\u00e9\5> \2\u00e9"+
		";\3\2\2\2\u00ea\u00f1\b\37\1\2\u00eb\u00ec\6\37\3\3\u00ec\u00ed\5> \2"+
		"\u00ed\u00ee\5J&\2\u00ee\u00f0\3\2\2\2\u00ef\u00eb\3\2\2\2\u00f0\u00f3"+
		"\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2=\3\2\2\2\u00f3"+
		"\u00f1\3\2\2\2\u00f4\u00f7\5F$\2\u00f5\u00f7\5@!\2\u00f6\u00f4\3\2\2\2"+
		"\u00f6\u00f5\3\2\2\2\u00f7?\3\2\2\2\u00f8\u00f9\5\4\3\2\u00f9\u00fa\7"+
		"\35\2\2\u00fa\u00fb\5B\"\2\u00fb\u00fc\7\36\2\2\u00fcA\3\2\2\2\u00fd\u00fe"+
		"\5\66\34\2\u00fe\u00ff\5D#\2\u00ff\u0102\3\2\2\2\u0100\u0102\3\2\2\2\u0101"+
		"\u00fd\3\2\2\2\u0101\u0100\3\2\2\2\u0102C\3\2\2\2\u0103\u0104\7\24\2\2"+
		"\u0104\u0105\5\66\34\2\u0105\u0106\5D#\2\u0106\u0109\3\2\2\2\u0107\u0109"+
		"\3\2\2\2\u0108\u0103\3\2\2\2\u0108\u0107\3\2\2\2\u0109E\3\2\2\2\u010a"+
		"\u010b\7\35\2\2\u010b\u010c\5\66\34\2\u010c\u010d\7\36\2\2\u010d\u0112"+
		"\3\2\2\2\u010e\u0112\5\4\3\2\u010f\u0112\7\n\2\2\u0110\u0112\7\t\2\2\u0111"+
		"\u010a\3\2\2\2\u0111\u010e\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0110\3\2"+
		"\2\2\u0112G\3\2\2\2\u0113\u0114\t\3\2\2\u0114I\3\2\2\2\u0115\u0116\t\4"+
		"\2\2\u0116K\3\2\2\2\u0117\u0118\7 \2\2\u0118\u0119\7\35\2\2\u0119\u011a"+
		"\5P)\2\u011a\u011b\7\36\2\2\u011b\u011c\5\b\5\2\u011c\u011d\5&\24\2\u011d"+
		"\u011e\5N(\2\u011e\u011f\7\"\2\2\u011fM\3\2\2\2\u0120\u0121\7!\2\2\u0121"+
		"\u0122\5\b\5\2\u0122\u0123\5&\24\2\u0123\u0126\3\2\2\2\u0124\u0126\3\2"+
		"\2\2\u0125\u0120\3\2\2\2\u0125\u0124\3\2\2\2\u0126O\3\2\2\2\u0127\u0128"+
		"\5\66\34\2\u0128\u0129\5R*\2\u0129\u012a\5\66\34\2\u012aQ\3\2\2\2\u012b"+
		"\u012c\t\5\2\2\u012cS\3\2\2\2\u012d\u012e\7#\2\2\u012e\u012f\7\35\2\2"+
		"\u012f\u0130\5P)\2\u0130\u0131\7\36\2\2\u0131\u0132\5\b\5\2\u0132\u0133"+
		"\5&\24\2\u0133\u0134\7$\2\2\u0134U\3\2\2\2\u0135\u0136\7+\2\2\u0136W\3"+
		"\2\2\2\u0137\u0138\7\'\2\2\u0138Y\3\2\2\2\23lp\u0083\u008d\u0093\u009d"+
		"\u00a3\u00b6\u00bb\u00c1\u00e4\u00f1\u00f6\u0101\u0108\u0111\u0125";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
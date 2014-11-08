// Generated from Micro.g4 by ANTLR 4.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'PROGRAM'", "'STRING'", "'RETURN'", "':='", "';'", "STRINGLITERAL", "FLOATLITERAL", 
		"INTLITERAL", "'INT'", "'FLOAT'", "'VOID'", "'<'", "'>'", "'!='", "'='", 
		"'*'", "'/'", "','", "'>='", "'<='", "'END'", "'BEGIN'", "'-'", "'+'", 
		"'READ'", "'WRITE'", "'('", "')'", "'FUNCTION'", "'IF'", "'ELSE'", "'ENDIF'", 
		"'WHILE'", "'ENDWHILE'", "IDENTIFIER", "COMMENT", "'CONTINUE'", "OPERATOR", 
		"KEYWORD", "WS", "'BREAK'"
	};
	public static final String[] ruleNames = {
		"PROG", "STRING", "RETURN", "COLEQ", "SEMI", "STRINGLITERAL", "FLOATLITERAL", 
		"INTLITERAL", "INT", "FLOAT", "VOID", "LESSTHAN", "MORETHAN", "NOTEQUAL", 
		"EQUAL", "ASKT", "FORESLASH", "COMMA", "MORETHAN_EQ", "LESSTHAN_EQ", "END", 
		"BEGIN", "MINUS", "PLUS", "READ", "WRITE", "BROPEN", "BRCLOSE", "FUNCTION", 
		"IF", "ELSE", "ENDIF", "WHILE", "ENDWHILE", "IDENTIFIER", "COMMENT", "CONTINUE", 
		"OPERATOR", "KEYWORD", "WS", "BREAK"
	};


	public MicroLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 35: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 39: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2+\u0141\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\7\7s\n\7\f\7\16\7v\13\7\3\7\3\7"+
		"\3\b\5\b{\n\b\3\b\3\b\6\b\177\n\b\r\b\16\b\u0080\3\t\6\t\u0084\n\t\r\t"+
		"\16\t\u0085\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\7$\u00f1\n$\f"+
		"$\16$\u00f4\13$\3$\5$\u00f7\n$\3%\3%\3%\3%\7%\u00fd\n%\f%\16%\u0100\13"+
		"%\3%\5%\u0103\n%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u0122\n\'\3(\3"+
		"(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\5(\u0136\n(\3)\3)\3"+
		")\3)\3*\3*\3*\3*\3*\3*\2+\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1"+
		"\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23"+
		"\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34\1\67\35"+
		"\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\2K\'\1M(\1O)\1Q*\3S+\1\3\2\b"+
		"\3\2$$\3\2\62;\4\2C\\c|\5\2\62;C\\c|\4\2\f\f\17\17\5\2\13\f\17\17\"\""+
		"\u0168\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2"+
		"G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3"+
		"\2\2\2\3U\3\2\2\2\5]\3\2\2\2\7d\3\2\2\2\tk\3\2\2\2\13n\3\2\2\2\rp\3\2"+
		"\2\2\17z\3\2\2\2\21\u0083\3\2\2\2\23\u0087\3\2\2\2\25\u008b\3\2\2\2\27"+
		"\u0091\3\2\2\2\31\u0096\3\2\2\2\33\u0098\3\2\2\2\35\u009a\3\2\2\2\37\u009d"+
		"\3\2\2\2!\u009f\3\2\2\2#\u00a1\3\2\2\2%\u00a3\3\2\2\2\'\u00a5\3\2\2\2"+
		")\u00a8\3\2\2\2+\u00ab\3\2\2\2-\u00af\3\2\2\2/\u00b5\3\2\2\2\61\u00b7"+
		"\3\2\2\2\63\u00b9\3\2\2\2\65\u00be\3\2\2\2\67\u00c4\3\2\2\29\u00c6\3\2"+
		"\2\2;\u00c8\3\2\2\2=\u00d1\3\2\2\2?\u00d4\3\2\2\2A\u00d9\3\2\2\2C\u00df"+
		"\3\2\2\2E\u00e5\3\2\2\2G\u00f6\3\2\2\2I\u00f8\3\2\2\2K\u0108\3\2\2\2M"+
		"\u0121\3\2\2\2O\u0135\3\2\2\2Q\u0137\3\2\2\2S\u013b\3\2\2\2UV\7R\2\2V"+
		"W\7T\2\2WX\7Q\2\2XY\7I\2\2YZ\7T\2\2Z[\7C\2\2[\\\7O\2\2\\\4\3\2\2\2]^\7"+
		"U\2\2^_\7V\2\2_`\7T\2\2`a\7K\2\2ab\7P\2\2bc\7I\2\2c\6\3\2\2\2de\7T\2\2"+
		"ef\7G\2\2fg\7V\2\2gh\7W\2\2hi\7T\2\2ij\7P\2\2j\b\3\2\2\2kl\7<\2\2lm\7"+
		"?\2\2m\n\3\2\2\2no\7=\2\2o\f\3\2\2\2pt\7$\2\2qs\n\2\2\2rq\3\2\2\2sv\3"+
		"\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vt\3\2\2\2wx\7$\2\2x\16\3\2\2\2y{"+
		"\t\3\2\2zy\3\2\2\2z{\3\2\2\2{|\3\2\2\2|~\7\60\2\2}\177\t\3\2\2~}\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\20\3\2\2"+
		"\2\u0082\u0084\t\3\2\2\u0083\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\22\3\2\2\2\u0087\u0088\7K\2\2\u0088"+
		"\u0089\7P\2\2\u0089\u008a\7V\2\2\u008a\24\3\2\2\2\u008b\u008c\7H\2\2\u008c"+
		"\u008d\7N\2\2\u008d\u008e\7Q\2\2\u008e\u008f\7C\2\2\u008f\u0090\7V\2\2"+
		"\u0090\26\3\2\2\2\u0091\u0092\7X\2\2\u0092\u0093\7Q\2\2\u0093\u0094\7"+
		"K\2\2\u0094\u0095\7F\2\2\u0095\30\3\2\2\2\u0096\u0097\7>\2\2\u0097\32"+
		"\3\2\2\2\u0098\u0099\7@\2\2\u0099\34\3\2\2\2\u009a\u009b\7#\2\2\u009b"+
		"\u009c\7?\2\2\u009c\36\3\2\2\2\u009d\u009e\7?\2\2\u009e \3\2\2\2\u009f"+
		"\u00a0\7,\2\2\u00a0\"\3\2\2\2\u00a1\u00a2\7\61\2\2\u00a2$\3\2\2\2\u00a3"+
		"\u00a4\7.\2\2\u00a4&\3\2\2\2\u00a5\u00a6\7@\2\2\u00a6\u00a7\7?\2\2\u00a7"+
		"(\3\2\2\2\u00a8\u00a9\7>\2\2\u00a9\u00aa\7?\2\2\u00aa*\3\2\2\2\u00ab\u00ac"+
		"\7G\2\2\u00ac\u00ad\7P\2\2\u00ad\u00ae\7F\2\2\u00ae,\3\2\2\2\u00af\u00b0"+
		"\7D\2\2\u00b0\u00b1\7G\2\2\u00b1\u00b2\7I\2\2\u00b2\u00b3\7K\2\2\u00b3"+
		"\u00b4\7P\2\2\u00b4.\3\2\2\2\u00b5\u00b6\7/\2\2\u00b6\60\3\2\2\2\u00b7"+
		"\u00b8\7-\2\2\u00b8\62\3\2\2\2\u00b9\u00ba\7T\2\2\u00ba\u00bb\7G\2\2\u00bb"+
		"\u00bc\7C\2\2\u00bc\u00bd\7F\2\2\u00bd\64\3\2\2\2\u00be\u00bf\7Y\2\2\u00bf"+
		"\u00c0\7T\2\2\u00c0\u00c1\7K\2\2\u00c1\u00c2\7V\2\2\u00c2\u00c3\7G\2\2"+
		"\u00c3\66\3\2\2\2\u00c4\u00c5\7*\2\2\u00c58\3\2\2\2\u00c6\u00c7\7+\2\2"+
		"\u00c7:\3\2\2\2\u00c8\u00c9\7H\2\2\u00c9\u00ca\7W\2\2\u00ca\u00cb\7P\2"+
		"\2\u00cb\u00cc\7E\2\2\u00cc\u00cd\7V\2\2\u00cd\u00ce\7K\2\2\u00ce\u00cf"+
		"\7Q\2\2\u00cf\u00d0\7P\2\2\u00d0<\3\2\2\2\u00d1\u00d2\7K\2\2\u00d2\u00d3"+
		"\7H\2\2\u00d3>\3\2\2\2\u00d4\u00d5\7G\2\2\u00d5\u00d6\7N\2\2\u00d6\u00d7"+
		"\7U\2\2\u00d7\u00d8\7G\2\2\u00d8@\3\2\2\2\u00d9\u00da\7G\2\2\u00da\u00db"+
		"\7P\2\2\u00db\u00dc\7F\2\2\u00dc\u00dd\7K\2\2\u00dd\u00de\7H\2\2\u00de"+
		"B\3\2\2\2\u00df\u00e0\7Y\2\2\u00e0\u00e1\7J\2\2\u00e1\u00e2\7K\2\2\u00e2"+
		"\u00e3\7N\2\2\u00e3\u00e4\7G\2\2\u00e4D\3\2\2\2\u00e5\u00e6\7G\2\2\u00e6"+
		"\u00e7\7P\2\2\u00e7\u00e8\7F\2\2\u00e8\u00e9\7Y\2\2\u00e9\u00ea\7J\2\2"+
		"\u00ea\u00eb\7K\2\2\u00eb\u00ec\7N\2\2\u00ec\u00ed\7G\2\2\u00edF\3\2\2"+
		"\2\u00ee\u00f2\t\4\2\2\u00ef\u00f1\t\5\2\2\u00f0\u00ef\3\2\2\2\u00f1\u00f4"+
		"\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f7\3\2\2\2\u00f4"+
		"\u00f2\3\2\2\2\u00f5\u00f7\t\4\2\2\u00f6\u00ee\3\2\2\2\u00f6\u00f5\3\2"+
		"\2\2\u00f7H\3\2\2\2\u00f8\u00f9\7/\2\2\u00f9\u00fa\7/\2\2\u00fa\u00fe"+
		"\3\2\2\2\u00fb\u00fd\n\6\2\2\u00fc\u00fb\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe"+
		"\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2"+
		"\2\2\u0101\u0103\7\17\2\2\u0102\u0101\3\2\2\2\u0102\u0103\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104\u0105\7\f\2\2\u0105\u0106\3\2\2\2\u0106\u0107\b%"+
		"\2\2\u0107J\3\2\2\2\u0108\u0109\7E\2\2\u0109\u010a\7Q\2\2\u010a\u010b"+
		"\7P\2\2\u010b\u010c\7V\2\2\u010c\u010d\7K\2\2\u010d\u010e\7P\2\2\u010e"+
		"\u010f\7W\2\2\u010f\u0110\7G\2\2\u0110L\3\2\2\2\u0111\u0122\5\13\6\2\u0112"+
		"\u0122\5\67\34\2\u0113\u0122\59\35\2\u0114\u0122\5\61\31\2\u0115\u0122"+
		"\5/\30\2\u0116\u0122\5\t\5\2\u0117\u0122\5!\21\2\u0118\u0122\5\35\17\2"+
		"\u0119\u0122\5\37\20\2\u011a\u0122\5#\22\2\u011b\u0122\5\31\r\2\u011c"+
		"\u0122\5\33\16\2\u011d\u0122\5\13\6\2\u011e\u0122\5%\23\2\u011f\u0122"+
		"\5)\25\2\u0120\u0122\5\'\24\2\u0121\u0111\3\2\2\2\u0121\u0112\3\2\2\2"+
		"\u0121\u0113\3\2\2\2\u0121\u0114\3\2\2\2\u0121\u0115\3\2\2\2\u0121\u0116"+
		"\3\2\2\2\u0121\u0117\3\2\2\2\u0121\u0118\3\2\2\2\u0121\u0119\3\2\2\2\u0121"+
		"\u011a\3\2\2\2\u0121\u011b\3\2\2\2\u0121\u011c\3\2\2\2\u0121\u011d\3\2"+
		"\2\2\u0121\u011e\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122"+
		"N\3\2\2\2\u0123\u0136\5\3\2\2\u0124\u0136\5-\27\2\u0125\u0136\5+\26\2"+
		"\u0126\u0136\5;\36\2\u0127\u0136\5\63\32\2\u0128\u0136\5\65\33\2\u0129"+
		"\u0136\5=\37\2\u012a\u0136\5? \2\u012b\u0136\5A!\2\u012c\u0136\5C\"\2"+
		"\u012d\u0136\5K&\2\u012e\u0136\5E#\2\u012f\u0136\5S*\2\u0130\u0136\5\7"+
		"\4\2\u0131\u0136\5\23\n\2\u0132\u0136\5\27\f\2\u0133\u0136\5\5\3\2\u0134"+
		"\u0136\5\25\13\2\u0135\u0123\3\2\2\2\u0135\u0124\3\2\2\2\u0135\u0125\3"+
		"\2\2\2\u0135\u0126\3\2\2\2\u0135\u0127\3\2\2\2\u0135\u0128\3\2\2\2\u0135"+
		"\u0129\3\2\2\2\u0135\u012a\3\2\2\2\u0135\u012b\3\2\2\2\u0135\u012c\3\2"+
		"\2\2\u0135\u012d\3\2\2\2\u0135\u012e\3\2\2\2\u0135\u012f\3\2\2\2\u0135"+
		"\u0130\3\2\2\2\u0135\u0131\3\2\2\2\u0135\u0132\3\2\2\2\u0135\u0133\3\2"+
		"\2\2\u0135\u0134\3\2\2\2\u0136P\3\2\2\2\u0137\u0138\t\7\2\2\u0138\u0139"+
		"\3\2\2\2\u0139\u013a\b)\3\2\u013aR\3\2\2\2\u013b\u013c\7D\2\2\u013c\u013d"+
		"\7T\2\2\u013d\u013e\7G\2\2\u013e\u013f\7C\2\2\u013f\u0140\7M\2\2\u0140"+
		"T\3\2\2\2\r\2tz\u0080\u0085\u00f2\u00f6\u00fe\u0102\u0121\u0135";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
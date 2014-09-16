grammar Micro;

id: IDENTIFIER;
comment: COMMENT;
str: (STRINGLITERAL);
string_decl: (STRING id COLEQ str);
//Test
/* Variable Declaration */
var_decl  : var_type id_list;
var_type  : FLOAT | INT;
any_type  : var_type | VOID;
id_list   : id id_tail;
id_tail   : ((',' id id_tail) | /* */);

/*Program declarations */
//program: (PROGRAM id BEGIN pgm_body END);
//pgm_body: (decl func_declarations);
//decl: (string_decl decl | var_decl decl | /* */);
//declar_str : ( STRING id COLEQ SEMI);

/* Function Declarations */
//func_declarations: (/* */);
OPERATOR: SEMI | BROPEN | BRCLOSE | PLUS | MINUS | COLEQ | ASKT | NOTEQUAL | EQUAL | FORESLASH | LESSTHAN | MORETHAN | SEMI | COMMA | LESSTHAN_EQ | MORETHAN_EQ;
KEYWORD: PROGRAM | BEGIN | END | FUNCTION | READ | WRITE | IF | ELSE | ENDIF | WHILE | ENDWHILE | CONTINUE | BREAK | RETURN | INT | VOID| STRING | FLOAT;
STRING : 'STRING';
RETURN: 'RETURN';
SEMI: ';';
COLEQ : ':=';
STRINGLITERAL: ('"' ~(["])* '"');
FLOATLITERAL: ([0-9]? '.' [0-9]+);
INTLITERAL: [0-9]+;
INT: 'INT';
FLOAT: 'FLOAT';
VOID: 'VOID';
LESSTHAN: '<';
MORETHAN: '>';
NOTEQUAL: '!=';
EQUAL : '=';
ASKT : '*';
FORESLASH: '/';
COMMA : ',';
MORETHAN_EQ: '>=';
LESSTHAN_EQ: '<=';

PROGRAM: 'PROGRAM';
END: 'END';
BEGIN: 'BEGIN';
MINUS: '-';
PLUS: '+';
READ: 'READ';
WRITE: 'WRITE';
BROPEN: '(';
BRCLOSE: ')';
FUNCTION: 'FUNCTION';
IF: 'IF';
ELSE: 'ELSE';
ENDIF: 'ENDIF';
WHILE: 'WHILE';
ENDWHILE: 'ENDWHILE';
CONTINUE: 'CONTINUE';
BREAK: 'BREAK';
IDENTIFIER: ([a-zA-Z][a-zA-Z0-9]* | [a-zA-Z]);
COMMENT: ('--' ~['\n']* '\n'+); 

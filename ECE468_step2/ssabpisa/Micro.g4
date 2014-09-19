grammar Micro;

/* Program */
program : ( PROG id BEGIN pgm_body END);
id : ( IDENTIFIER );
pgm_body : ( decl func_declarations);
decl : ( string_decl decl | var_decl decl | /*empty*/ );

/* Global String Declaration */
string_decl : ( STRING id ) COLEQ str SEMI ;
str : ( STRINGLITERAL );

/* Variable Declaration */
var_decl : ( var_type id_list  SEMI );
var_type : ( FLOAT | INT );
any_type : ( var_type | VOID );
id_list : ( id id_tail );
id_tail : ( ',' id id_tail | /* empty */);

/* Function Paramater List */
param_decl_list : ( param_decl param_decl_tail | /* empty */ );
param_decl : ( var_type id );
param_decl_tail : ( ',' param_decl param_decl_tail | /* empty */);

/* Function Declarations */
func_declarations : ( func_decl func_declarations | /* empty */);
func_decl : ( FUNCTION any_type id BROPEN param_decl_list BRCLOSE BEGIN func_body END );
func_body : ( decl stmt_list );

/* Statement List */
stmt_list : ( stmt stmt_list | /* empty */ );
stmt : ( base_stmt | if_stmt | while_stmt );
base_stmt : ( assign_stmt | read_stmt | write_stmt | return_stmt );

/* Basic Statements */
assign_stmt : ( assign_expr  SEMI );
assign_expr : ( id ) COLEQ expr;
read_stmt : ( READ BROPEN id_list BRCLOSE SEMI );
write_stmt : ( WRITE BROPEN id_list BRCLOSE SEMI );
return_stmt : ( RETURN expr  SEMI );

/* Expressions */

//expr : ();
expr : ( expr_prefix factor );
expr_prefix : expr_prefix factor addop | /*empty */;
factor : ( factor_prefix postfix_expr );
factor_prefix : factor_prefix postfix_expr mulop | /* empty */ ;
postfix_expr : ( primary | call_expr );
call_expr : ( id BROPEN expr_list BRCLOSE );
expr_list : ( expr expr_list_tail | );
expr_list_tail : ( ',' expr expr_list_tail | );
primary : ( BROPEN expr BRCLOSE | id | INTLITERAL | FLOATLITERAL );
addop : ( '+' | '-' );
mulop : ( '*' | '/' );

/* Complex Statements and Condition */
if_stmt : ( IF BROPEN cond BRCLOSE decl stmt_list else_part ENDIF );
else_part : ( ELSE decl stmt_list | /* empty */ );
cond : ( expr compop expr );
compop : ( '<' | '>' | '=' | '!=' | '<=' | '>=' );

/* ECE 468 students use this version of while_stmt */
while_stmt : ( WHILE BROPEN cond BRCLOSE decl stmt_list ENDWHILE);

PROG: 'PROGRAM';
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

OPERATOR: SEMI | BROPEN | BRCLOSE | PLUS | MINUS | COLEQ | ASKT | NOTEQUAL | EQUAL | FORESLASH | LESSTHAN | MORETHAN | SEMI | COMMA | LESSTHAN_EQ | MORETHAN_EQ;
KEYWORD: PROG | BEGIN | END | FUNCTION | READ | WRITE | IF | ELSE | ENDIF | WHILE | ENDWHILE | CONTINUE | BREAK | RETURN | INT | VOID| STRING | FLOAT;
WS: [ \t\n\r] -> skip;

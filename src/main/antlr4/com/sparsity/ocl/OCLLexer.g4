lexer grammar OCLLexer;

/** OCL 1.3 Lexer
 *
 * author:	Frederic FONDEMENT	f.fondement@objexion.com
 * Version 1.00 August 2, 2000 -- initial release
 *
 * This grammar is in the PUBLIC DOMAIN
 */

NL
	:	(	'\r' '\n' 
		|	'\n' '\r'  //Improbable
		|	'\r'
		|	'\n'
		)
	;


WS
	:	(	' '
		|	'\t'   
		|	NL
		)
	;

COMMENT
	:	'--'
		(~(	'\r'
		|	'\n'
		))*
		(NL)?
	;

LPAREN
    :
	'('
	;

RPAREN
    :
	')'
	;

LSQUARE
    :
	'['
	;

RSQUARE
    :
	']'
	;

LCURLY
    :
	'{'
	;

RCURLY
    :
	'}'
	;

PIPE
    :
	'|'
	;

POINT
    :
	'.'
	;

POINT_POINT
    :
	'..'
	;

COMA
    :
	','
	;

SEMI
    :
	';'
	;

AROBAPRE
    :
	'@pre'
	;

DIESE
    :
	'#'
	;

POINTS
    :
	':'
	;

FOUR_POINTS
    :
	'::'
	;
	
ARROW
    :
	'->'
	;

STAR
    :
	'*'
	;

DIV
    :
	'/'
	;

PLUS
    :
	'+'
	;

MINUS
    :
	'-'
	;

EQ
    :
	'='
	;

NE
    :
	'<>'
	;

LT
    :
	'<'
	;

LE
    :
	'<='
	;

GT
    :
	'>'
	;


GE
    :
	'>='
	;

DIGIT
	:	'0'..'9'
	;

ALPHA
	:	'a'..'z' 
	|	'A'..'Z'
	|	'_'
	//For Unicode compatibility (from 0000 to 00ff)
	|	'\u00C0' .. '\u00D6'
	|	'\u00D8' .. '\u00F6'
	|	'\u00F8' .. '\u00FF'
	;

INT
    :	(DIGIT)+
	;

FLOAT
    :	(DIGIT)+
		('.' (DIGIT)+ )?
	;

NAME
:	(ALPHA) (ALPHA | DIGIT)*
	;

ESC
	:	'\\'
		(	'n'
		|	'r'
		|	't'
		|	'b'
		|	'f'
		|	'"'
		|	'\''
		|	'\\'
		|	(
				('0'..'3')
				(
					('0'..'7')
					(	
						'0'..'7'
					)?
				)?
			|	('4'..'7')
				(
					('0'..'7')
				)?
			)
		)
	;

STRING
:	'\''
		(	ESC
		|	~('\\'|'\'')
		)*
		'\''
	;

RECOVER
	:	(~(	'0'..'9' | 'A'..'Z' | 'a'..'z' | '_'
		|	'+'|'-'|'*'|'/'
		|	'>'|'<'|'='
		|	'\''|'#'|'@'|':'|'.'|','|';'|'|'
		|	'('|')'|'{'|'}'|'['|']'
		|	' '|'\t'|'\n'|'\r'
		|	'\u00C0' .. '\u00D6'
		|	'\u00D8' .. '\u00F6'
		|	'\u00F8' .. '\u00FF'
		))
		(~(	' '
		|	'\t'
		|	'\n'
		|	'\r'
		))*
	;

CONTEXT
    :
    'context'
    ;

INV
    :
    'inv'
    ;

PRE
    :
    'pre'
    ;

POST
    :
    'post'
    ;

LET
    :
    'let'
    ;

IN
    :
    'in'
    ;

AND
    :
    'and'
    ;

OR
    :
    'or'
    ;

XOR
    :
    'xor'
    ;

NOT
    :
    'not'
    ;

IMPLIES
    :
    'implies'
    ;

IF
    :
    'if'
    ;

THEN
    :
    'then'
    ;

ELSE
    :
    'else'
    ;

ENDIF
    :
    'endif'
    ;

ENUM
    :
    'enum'
    ;

OCLTYPE
    :
    'OclType'
    ;

OCLANY
    :
    'OclAny'
    ;

REAL
    :
    'Real'
    ;

INTEGER
    :
    'Integer'
    ;

STRINGTEXT
    :
    'String'
    ;

BOOLEAN
    :
    'Boolean'
    ;

COLLECTION
    :
    'Collection'
    ;

SET
    :
    'Set'
    ;

BAG
    :
    'Bag'
    ;

SEQUENCE
    :
    'Sequence'
    ;

TRUE
    :
    'true'
    ;

FALSE
    :
    'false'
    ;


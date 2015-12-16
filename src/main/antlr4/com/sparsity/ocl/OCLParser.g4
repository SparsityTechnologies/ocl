parser grammar OCLParser;

/** OCL 1.3 Parser
 *
 * author:	Frederic FONDEMENT	f.fondement@objexion.com
 * Version 1.00 August 2, 2000 -- initial release
 *
 * This grammar is in the PUBLIC DOMAIN
 */


options
{
	language = Java;
	tokenVocab = OCLLexer;
}

oclStatement : contextDeclaration (stereotype (NAME)? POINTS expression)+;

contextDeclaration : CONTEXT (operationContext | classifierContext );

classifierContext
	: (NAME POINTS)? classifierType
	;

operationContext
	: classifierType /*includes the operation name!!!*/
	 LPAREN (formalParameterList)? RPAREN (POINTS (classifierType|oclType))?
	;

formalParameterList
	: formalParameter (COMA formalParameter)*
	;

formalParameter
	: NAME POINTS typeName
	;

stereotype
	: (INV | PRE | POST | BODY)
	;

classifierType
	: pathName
	;

fileExpression
	: expression EOF
	;

expression
	: letExpression
	| logicExpression
	| tupleExpression
	;

letExpression
	: LET NAME (POINTS typeName)? EQ expression IN expression
	;

logicExpression
	:
	  arithmeticExpression
	| NOT logicExpression
	| logicExpression (GT|LT|GE|LE) logicExpression
	| logicExpression (EQ|NE) logicExpression
	| logicExpression AND logicExpression
	| logicExpression OR logicExpression
	| logicExpression XOR logicExpression
	| logicExpression IMPLIES logicExpression
	;

tupleExpression
    :
    TUPLE LCURLY NAME EQ expression (COMA NAME EQ expression)* RCURLY
    ;

arithmeticExpression
	:
	  primaryExpression((POINT|ARROW) featureCall)*
	| MINUS arithmeticExpression
	| arithmeticExpression (STAR|DIV) arithmeticExpression
	| arithmeticExpression (PLUS|MINUS) arithmeticExpression
	;

primaryExpression
	: litteralCollection
	| litteral
	| featureCall
	| LPAREN expression RPAREN
	| ifExpression
	;

litteralCollection
	: oclCollection LCURLY (expressionListOrRange)? RCURLY
	;

expressionListOrRange
	: expression
	 (
	 |	(COMA expression)+
	 |	POINT_POINT expression
	 )
	;

litteral
	: number
	| DIESE NAME
	;

ifExpression
	: IF expression THEN expression ELSE expression ENDIF
	;
	
qualifiers
	: LSQUARE actualParameterList RSQUARE
	{
	}
	;

featureCall
	: pathName
	 ((qualifiers))?
	 (AROBAPRE)?
	 (	qualifiers
	 |	parameters
	 )?
	;
	
parameters
    :
    LPAREN
 	(
	declarator (expression)?
	|	actualParameterList
	)
	RPAREN
	;

declarator
	:
	declaration
	(SEMI declaration)*
	PIPE
	;
	
declaration
	: NAME
	(COMA NAME)*
	(POINTS typeName)?
	(EQ expression)?
	;

actualParameterList
	: (expression (COMA expression)*)?
	;

typeName
	: pathName
	| enumType
	| oclType
	;
	
enumType
	: ENUM LCURLY DIESE NAME (COMA DIESE NAME)* RCURLY
	;

oclType
	: ( OCLTYPE
	| OCLANY
	|   REAL
	|   INTEGER
	|   STRINGTEXT
	|   BOOLEAN
	|   tupleType
	|	oclCollection LPAREN typeName RPAREN
	)
	;

oclCollection
	: (	COLLECTION
	|   SET
	|   SORTEDSET
	|   BAG
	|   SEQUENCE
	)
	;
tupleType
    :
    TUPLETYPE LPAREN (formalParameterList) RPAREN
    ;

pathName
	: NAME
	(FOUR_POINTS NAME)*
	;

number
	: INT
	| FLOAT
	| STRING
	| bool
	| oclType
	;

bool
	: (TRUE | FALSE)
	;

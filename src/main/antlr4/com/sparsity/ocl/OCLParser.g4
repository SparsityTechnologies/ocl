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
    TUPLE LCURLY declaration (COMA declaration)* RCURLY
    ;

arithmeticExpression
	:
	  primaryExpression ((POINT|ARROW) callExp)*
	| MINUS arithmeticExpression
	| arithmeticExpression (STAR|DIV) arithmeticExpression
	| arithmeticExpression (PLUS|MINUS) arithmeticExpression
	;

primaryExpression
	: literalCollection
	| literal
	| typeExp
	| callExp
	| LPAREN expression RPAREN
	| ifExpression
	;

	typeExp
	: typeName
	;

variable
    : NAME
    ;

classifier
	: pathName
	;

literalCollection
	: oclCollection LCURLY (expressionListOrRange)? RCURLY
	;

expressionListOrRange
	: expression
	 (
	 |	(COMA expression)+
	 |	POINT_POINT expression
	 )
	;

literal
	: number
	| STRING
	| bool
	| DIESE NAME
	;

ifExpression
	: IF expression THEN expression ELSE expression ENDIF
	;
	
qualifiers
	: LSQUARE argumentList RSQUARE
	;

callExp
	: featureCallExp
	| loopExp
	;

loopExp
	: iteratorExp
	;

iteratorExp
    : NAME LPAREN declarator expression RPAREN
    ;

featureCallExp
    : operationCallExp
    | propertyCallExp
    ;

operationCallExp
    : pathName? NAME
	 (AROBAPRE)?
	 LPAREN argumentList RPAREN
    ;

propertyCallExp
    :NAME ((qualifiers))?
    ;

parameters
    :
    LPAREN
 	(
	declarator (expression)?
	| argumentList
	)
	RPAREN
	;


declarator
	:
	declaration
	(COMA declaration)*
	PIPE
	;
	
declaration
	: variable
	(POINTS typeName)?
	(EQ expression)?
	;

argumentList
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
    TUPLE LPAREN (formalParameterList) RPAREN
    ;

pathName
	: NAME
	(FOUR_POINTS NAME)*
	;

number
	: INT
	| FLOAT
	| oclType
	;

bool
	: (TRUE | FALSE)
	;

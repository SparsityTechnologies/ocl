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

/*tokens {
	CONSTR,
	CLASSCONTEXT,
	OPCONTEXT,
	CONSTR_BODY,
	STEREOTYPE,
	FORMAL,
	PARAMLIST,
	LET,
	QUALIFIERS,
	PARAMETERS,
	PARAMETERSDEFININGVARS,
	DECLARATION,
	VALUE,
	EXPRLIST,
	EXPRRANGE,
	OPERATOR,
	TYPE,
	TYPEINIT,
	ENUMERATION,
	COLLECTION,
	BOOLEAN
}*/

constraint
	:	contextDeclaration (constraintBody)+
	;

contextDeclaration
	:   CONTEXT
		(
			operationContext
		|	classifierContext
		)
	;

classifierContext
	:	(NAME POINTS)? classifierType
	;

operationContext
	:	classifierType /*includes the operation name!!!*/
		LPAREN (formalParameterList)? RPAREN (POINTS (classifierType|oclType))?
	;

formalParameterList
	:	formalParameter (SEMI formalParameter)*
	;

formalParameter
	:	NAME POINTS typeName
	;

constraintBody
	:	stereotype (NAME)? POINTS expression
	;

stereotype
	:	(INV | PRE | POST)
	;

classifierType
	:	pathName
	;

fileExpression
	:	expression EOF
	;

expression
	:	letExpression
	|	logicalExpression
	;

letExpression
	:	LET NAME (POINTS typeName)? EQ expression IN expression
	;

logicalExpression
	:	relationalExpression ((AND|OR|XOR|IMPLIES) relationalExpression)*
	;

relationalExpression
	:	additiveExpression ((EQ|GT|LT|GE|LE|NE) additiveExpression)?
	;

additiveExpression
	:	multiplicativeExpression ((PLUS|MINUS) multiplicativeExpression )*
	;

multiplicativeExpression
	:	unaryExpression ((STAR|DIV) unaryExpression)*
	;

unaryExpression
	:	((NOT|MINUS))? postfixExpression
	;

postfixExpression
	:	primaryExpression((POINT|ARROW) featureCall)*
	;

primaryExpression
	:	litteralCollection
	|	litteral
	|	featureCall
	|	LPAREN expression RPAREN
	|	ifExpression
	;

litteralCollection
	:	oclCollection LCURLY (expressionListOrRange)? RCURLY
	;

expressionListOrRange
	:	expression 
		(
		|	(COMA expression)+
		|	POINT_POINT expression
		)
	;

litteral
	:	number
	|	DIESE NAME
	;

ifExpression
	:	IF expression THEN expression ELSE expression ENDIF
	;
	
qualifiers
	:	LSQUARE actualParameterList RSQUARE
	{
	}
	;

featureCall
	:	pathName
		((qualifiers))?
		(AROBAPRE)?
		(	qualifiers
		|	parameters
		)?
	;
	
parameters :	LPAREN
		(
			declarator (expression)?
		|	actualParameterList
		)
		RPAREN
	;

declarator
	:	declaration
		(SEMI declaration)*
		PIPE
	;
	
declaration
	:	NAME 
		(COMA NAME)*
		(POINTS typeName)?
		(EQ expression)?
	;

actualParameterList
	:	(expression (COMA expression)*)?
	;

typeName
	:	pathName
	|	enumType
	|	oclType
	;
	
enumType
	:	ENUM LCURLY DIESE NAME (COMA DIESE NAME)* RCURLY
	;

oclType
	:	(   OCLTYPE
		|   OCLANY
		|   REAL
		|   INTEGER
		|   STRINGTEXT
		|   BOOLEAN
		|	oclCollection LPAREN typeName RPAREN
		)
	;

oclCollection
	:	(	COLLECTION
		|   SET
		|   BAG
		|   SEQUENCE
		)
	;

pathName
	:	NAME
		(FOUR_POINTS NAME)*
	;

number
	:	INT
	|	FLOAT
	|	STRING
	|	bool
	|	oclType
	;

bool
	:	(TRUE | FALSE)
	;

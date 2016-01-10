/* Copyright 2015-2016 Sparsity Technologies
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.*/
package com.sparsity.ocl;

import com.sparsity.ocl.ast.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aprat on 15/12/15.
 */
public class OCLASTGeneratorVisitor extends OCLParserBaseVisitor<OclAstNode> {

    @Override
    public OclAstNode visitOclStatement(OCLParser.OclStatementContext ctx  ) {
        OCLParser.ContextDeclarationContext contextDeclaration = ctx.contextDeclaration();
        OCLParser.OperationContextContext operationCtx = contextDeclaration.operationContext();
        if(operationCtx == null) {
            System.err.println("Only operation delcarations are supported so far!");
            assert false;
        }

        Operation operation = new Operation(operationCtx.classifierType(0).getText());

        OCLParser.FormalParameterListContext formalParameterListCtx = operationCtx.formalParameterList();
        for(OCLParser.FormalParameterContext paramCtx : formalParameterListCtx.formalParameter()) {
            Parameter param = (Parameter)visitFormalParameter(paramCtx);
            operation.addParameter(param);
        }

        int index = 0;
        for( OCLParser.ExpressionContext cContext : ctx.expression() ) {
            OclExpression oclExpression = (OclExpression)visitExpression(cContext);
            ExpressionInOcl expressionInOcl = new ExpressionInOcl(oclExpression);
            Constraint constraint = new Constraint(expressionInOcl);
            String stereotype = ctx.stereotype(index).getText();
            if(stereotype.compareTo("body") == 0) {
                operation.setBody(constraint);
            } else if (stereotype.compareTo("post") == 0) {
                operation.addPost(constraint);
            } else if (stereotype.compareTo("pre") == 0) {
                operation.addPre(constraint);
            }
            ++index;
        }
        return operation;
    }

    @Override
    public OclAstNode visitFormalParameter(OCLParser.FormalParameterContext paramCtx) {
        Type type = (Type)visitTypeName(paramCtx.typeName());
        Parameter param = new Parameter(paramCtx.NAME().getText(), type);
        return param;
    }

    @Override
    public OclAstNode visitTypeName(OCLParser.TypeNameContext typeNameContext) {
        if(typeNameContext.oclType() != null) {
            return new Type(typeNameContext.oclType().getText());
        } else if(typeNameContext.enumType() != null ) {
            return new Type(typeNameContext.enumType().getText());
        }
        return new Type(typeNameContext.pathName().getText());
    }

    @Override
    public OclAstNode visitExpression(OCLParser.ExpressionContext expressionCtx) {
        if(expressionCtx.letExpression() != null) return visitLetExpression(expressionCtx.letExpression());
        if(expressionCtx.logicExpression() != null) return visitLogicExpression(expressionCtx.logicExpression());
        return visitTupleExpression(expressionCtx.tupleExpression());
    }

    @Override
    public OclAstNode visitLetExpression( OCLParser.LetExpressionContext letExpressionCtx ) {
        Variable variable = new Variable(letExpressionCtx.NAME().getText());
        if(letExpressionCtx.typeName() != null) {
            variable.setType((Type)visitTypeName(letExpressionCtx.typeName()));
        }
        variable.setInitExpression((OclExpression)visitExpression(letExpressionCtx.expression(0)));
        OCLParser.ExpressionContext inExpressionContext = letExpressionCtx.expression(1);
        return new LetExpression((OclExpression)visitExpression(inExpressionContext),variable);
    }

    @Override
    public OclAstNode visitTupleExpression( OCLParser.TupleExpressionContext tupleExpressionCtx ) {
        TupleLiteralExp tupleLiteralExp = new TupleLiteralExp();
        List<Variable> parts = new ArrayList<Variable>();
        for(OCLParser.DeclarationContext decl : tupleExpressionCtx.declaration()) {
            Variable var = (Variable)visitDeclaration(decl);
            parts.add(var);
        }
        tupleLiteralExp.setParts(parts);
        return tupleLiteralExp;
    }

    @Override
    public OclAstNode visitLogicExpression( OCLParser.LogicExpressionContext logicExpression ) {
//        System.err.println("Visit logicExpression not implemented ");
        if(logicExpression.arithmeticExpression() != null) {
            return visitArithmeticExpression(logicExpression.arithmeticExpression());
        }

        if(logicExpression.NOT() != null) {
            OperationCallExp operationCallExp = new OperationCallExp();
            operationCallExp.setReferredOperation("NOT");
            operationCallExp.setSource((OclExpression)visitLogicExpression(logicExpression.logicExpression(0)));
            return operationCallExp;
        }

        String operationName = null;
        if(logicExpression.GT() != null) {
            operationName = "GT";
        } else if(logicExpression.LT() != null) {
            operationName = "LT";
        } else if(logicExpression.GE() != null) {
            operationName = "GE";
        } else if(logicExpression.GE() != null) {
            operationName = "LE";
        } else if(logicExpression.EQ() != null) {
            operationName = "EQ";
        } else if(logicExpression.NE() != null) {
            operationName = "NE";
        } else  if(logicExpression.AND() != null) {
            operationName = "AND";
        } else if(logicExpression.OR() != null) {
            operationName = "OR";
        } else if(logicExpression.XOR() != null) {
            operationName = "XOR";
        } else if(logicExpression.IMPLIES() != null) {
            operationName = "IMPLIES";
        }
        assert operationName != null;

        OperationCallExp operationCallExp = new OperationCallExp();
        operationCallExp.setReferredOperation(operationName);
        operationCallExp.setSource((OclExpression)visitLogicExpression(logicExpression.logicExpression(0)));
        List<OclExpression> arguments = new ArrayList<OclExpression>();
        arguments.add((OclExpression)visitLogicExpression(logicExpression.logicExpression(1)));
        operationCallExp.setArguments(arguments);
        return operationCallExp;
    }

    @Override
    public OclAstNode visitArithmeticExpression( OCLParser.ArithmeticExpressionContext arithmeticExpression ) {
        //System.err.println("Visit arithmeticExpression not implemented ");
        if(arithmeticExpression.primaryExpression() != null) {
            OclExpression exp = (OclExpression)visitPrimaryExpression(arithmeticExpression.primaryExpression());
            for(OCLParser.CallExpContext callExpContext : arithmeticExpression.callExp()) {
                CallExp callExp = (CallExp)visitCallExp(callExpContext);
                callExp.setSource(exp);
                exp = callExp;
            }
            return exp;
        }

        if(arithmeticExpression.MINUS() != null && arithmeticExpression.arithmeticExpression().size() == 1) {
            OperationCallExp operationCallExp = new OperationCallExp();
            operationCallExp.setReferredOperation("MINUS");
            operationCallExp.setSource((OclExpression)visitArithmeticExpression(arithmeticExpression.arithmeticExpression(0)));
            return operationCallExp;
        }

        String operationName = null;
        if(arithmeticExpression.STAR() != null) {
            operationName = "STAR";
        } else if(arithmeticExpression.DIV() != null) {
            operationName = "DIC";
        } else if(arithmeticExpression.PLUS() != null) {
            operationName = "PLUS";
        } else if(arithmeticExpression.MINUS() != null) {
            operationName = "MINUS";
        }

        assert operationName != null;

        OperationCallExp operationCallExp = new OperationCallExp();
        operationCallExp.setReferredOperation(operationName);
        operationCallExp.setSource((OclExpression)visitArithmeticExpression(arithmeticExpression.arithmeticExpression(0)));
        List<OclExpression> arguments = new ArrayList<OclExpression>();
        arguments.add((OclExpression)visitArithmeticExpression(arithmeticExpression.arithmeticExpression(1)));
        operationCallExp.setArguments(arguments);
        return operationCallExp;
    }

    @Override
    public OclAstNode visitPrimaryExpression( OCLParser.PrimaryExpressionContext primaryExpressionCtx ) {
        if(primaryExpressionCtx.callExp() != null ) {
            return visitCallExp(primaryExpressionCtx.callExp());
        }

        if(primaryExpressionCtx.typeExp() != null ) {
            return visitTypeExp(primaryExpressionCtx.typeExp());
        }

        if(primaryExpressionCtx.variable() != null ) {
            return visitVariable(primaryExpressionCtx.variable());
        }

        if(primaryExpressionCtx.literal() != null ) {
            return visitLiteral(primaryExpressionCtx.literal());
        }

        if(primaryExpressionCtx.literalCollection() != null ) {
            System.err.println("litteralCollection expression not yet handled");
            assert false;
        }

        if(primaryExpressionCtx.ifExpression() != null ) {
            System.err.println("ifExpression expression not yet handled");
            assert false;
        }

        if(primaryExpressionCtx.LPAREN() != null ) {
            return visitExpression(primaryExpressionCtx.expression());
        }

        System.out.println("Invalid grammar? Possibly unhandled path.");
        assert false;
        return null;
    }

    @Override
    public OclAstNode visitLiteral(OCLParser.LiteralContext literalCtx) {
        if(literalCtx.bool() != null) {
            return new BooleanLiteralExp(Boolean.parseBoolean(literalCtx.bool().getText()));
        }

        if(literalCtx.number().FLOAT() != null) {
            return new RealLiteralExp(Double.parseDouble(literalCtx.number().FLOAT().getText()));
        }

        if(literalCtx.number().INT() != null) {
            return new IntegerLiteralExp(Integer.parseInt(literalCtx.number().INT().getText()));
        }
        System.err.println("Unhandled literal");
        assert false;
        return null;
    }

    @Override
    public OclAstNode visitVariable(OCLParser.VariableContext variableCtx) {
        VariableExp var = new VariableExp(variableCtx.getText());
        return var;
    }

    @Override
    public OclAstNode visitCallExp(OCLParser.CallExpContext callExpCtx) {
        if(callExpCtx.loopExp() != null) return visitLoopExp(callExpCtx.loopExp());
        if(callExpCtx.featureCallExp() != null) return visitFeatureCallExp(callExpCtx.featureCallExp());
        System.out.println("Unhandled path at callExpCtx");
        assert false;
        return null;
    }

    @Override
    public OclAstNode visitFeatureCallExp(OCLParser.FeatureCallExpContext featureCallExpCtx) {
        if(featureCallExpCtx.operationCallExp() != null) return visitOperationCallExp(featureCallExpCtx.operationCallExp());
        if(featureCallExpCtx.propertyCallExp() != null)  return visitPropertyCallExp(featureCallExpCtx.propertyCallExp());
        System.out.println("Unhandled path at featureCallExp");
        assert false;
        return null;
    }

    @Override
    public OclAstNode visitLoopExp(OCLParser.LoopExpContext loopExpContext ) {
        return visitIteratorExp(loopExpContext.iteratorExp());
    }

    @Override
    public OclAstNode visitOperationCallExp(OCLParser.OperationCallExpContext operationCall) {
        OperationCallExp operation = new OperationCallExp();
        operation.setReferredOperation(operationCall.NAME().getText());
        List<OclExpression> arguments = new ArrayList<OclExpression>();
        if(operationCall.argumentList() != null) {
            for(OCLParser.ExpressionContext expression : operationCall.argumentList().expression()) {
                OclExpression argument = (OclExpression)visitExpression(expression);
                arguments.add(argument);
            }
        }
        operation.setArguments(arguments);
        return operation;
    }

    @Override
    public OclAstNode visitDeclarator(OCLParser.DeclaratorContext declaratorContext ) {
        System.err.println("Visit Declarator not implemented");
        assert false;
        return null;
    }

    public OclAstNode visitDeclaration(OCLParser.DeclarationContext declarationContext ) {
        Variable var = new Variable(declarationContext.variable().NAME().getText());
        if(declarationContext.typeName() != null ) var.setType(new Type(declarationContext.getText()));
        if(declarationContext.expression() != null) var.setInitExpression((OclExpression)visitExpression(declarationContext.expression()));
        return var;
    }

    @Override
    public OclAstNode visitPropertyCallExp(OCLParser.PropertyCallExpContext propertyCallCtx) {
        PropertyCallExp propertyCallExp = new PropertyCallExp();
        propertyCallExp.setReferredProperty(propertyCallCtx.NAME().getText());
        if(propertyCallCtx.qualifiers() != null) {
            System.err.println("Qualifiers on property call not yet supported");
            assert false;
        }
        return propertyCallExp;
    }

    @Override
    public OclAstNode visitIteratorExp(OCLParser.IteratorExpContext iteratorExpCtx) {
        IteratorExp iteratorExp = new IteratorExp((OclExpression)visitExpression(iteratorExpCtx.expression()));
        iteratorExp.setName(iteratorExpCtx.NAME().getText());
        List<Variable> vars = new ArrayList<Variable>();
        for(OCLParser.DeclarationContext declarationContext : iteratorExpCtx.declarator().declaration()) {
           Variable var = (Variable)visitDeclaration(declarationContext);
           vars.add(var);
        }
        iteratorExp.setIterator(vars);
        return iteratorExp;
    }

    @Override
    public OclAstNode visitTypeExp( OCLParser.TypeExpContext typeExpCtx) {
        Type type = (Type)visitTypeName(typeExpCtx.typeName());
        TypeExp typeExp = new TypeExp();
        typeExp.setReferredType(type);
        return typeExp;
    }
}

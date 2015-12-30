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
    public OclAstNode visitTupleExpression( OCLParser.TupleExpressionContext tupleExpression ) {
        System.err.println("Visit tupleExpression not implemented ");
        return null;
    }

    @Override
    public OclAstNode visitLogicExpression( OCLParser.LogicExpressionContext logicExpression ) {
        System.err.println("Visit logicExpression not implemented ");
        if(logicExpression.arithmeticExpression() != null) {
            return visitArithmeticExpression(logicExpression.arithmeticExpression());
        }
        return null;
    }

    @Override
    public OclAstNode visitArithmeticExpression( OCLParser.ArithmeticExpressionContext arithmeticExpression ) {
        System.err.println("Visit arithmeticExpression not implemented ");
        if(arithmeticExpression.primaryExpression() != null) {
            OclExpression exp = (OclExpression)visitPrimaryExpression(arithmeticExpression.primaryExpression());
            for(OCLParser.FeatureCallContext featureCallContext : arithmeticExpression.featureCall()) {
                FeatureCallExp fc = (FeatureCallExp)visitFeatureCall(featureCallContext);
                fc.setSource(exp);
                exp = fc;
            }
            return exp;
        }
        return null;
    }

    @Override
    public OclAstNode visitPrimaryExpression( OCLParser.PrimaryExpressionContext primaryExpression ) {
        System.err.println("Visit primaryExpression not implemented ");
        if(primaryExpression.classifier() != null) {
            if(primaryExpression.featureCall().operationCall() != null ) {
                return visitOperationCall(primaryExpression.featureCall().operationCall());
            }
            return visitPropertyCall(primaryExpression.featureCall().propertyCall());
        }

        return null;
    }

    @Override
    public OclAstNode visitOperationCall(OCLParser.OperationCallContext operationCall) {
        System.err.println("Visit operationCall not implemented ");
        OperationCallExp operation = new OperationCallExp();
        operation.setReferredOperation(operationCall.NAME().getText());
        List<OclExpression> arguments = new ArrayList<OclExpression>();
        if(operationCall.parameters().actualParameterList() != null) {
            for(OCLParser.ExpressionContext expression : operationCall.parameters().actualParameterList().expression()) {
                OclExpression argument = (OclExpression)visitExpression(expression);
                arguments.add(argument);
            }
        } else {
            OclExpression declarator = (OclExpression)visitDeclarator(operationCall.parameters().declarator());
            arguments.add(declarator);
        }
        operation.setArguments(arguments);
        return operation;
    }

    @Override
    public OclAstNode visitDeclarator(OCLParser.DeclaratorContext declaratorContext ) {
        System.err.println("Visit Declarator not implemented");

        return null;
    }

    @Override
    public OclAstNode visitPropertyCall(OCLParser.PropertyCallContext propertyCall) {
        System.err.println("Visit propertyCall not implemented ");
        return null;
    }
}

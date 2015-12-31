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
            for(OCLParser.CallExpContext callExpContext : arithmeticExpression.callExp()) {
                CallExp callExp = (CallExp)visitCallExp(callExpContext);
                callExp.setSource(exp);
                exp = callExp;
            }
            return exp;
        }
        return null;
    }

    @Override
    public OclAstNode visitPrimaryExpression( OCLParser.PrimaryExpressionContext primaryExpression ) {
        System.err.println("Visit primaryExpression not implemented ");
        if(primaryExpression.classifier() != null) {
            if(primaryExpression.callExp().featureCallExp() != null ) {
                return visitFeatureCallExp(primaryExpression.callExp().featureCallExp());
            }
            return visitLoopExp(primaryExpression.callExp().loopExp());
        }
        return null;
    }

    @Override
    public OclAstNode visitLoopExp(OCLParser.LoopExpContext loopExpContext ) {
        return visitIteratorExp(loopExpContext.iteratorExp());
    }

    @Override
    public OclAstNode visitOperationCallExp(OCLParser.OperationCallExpContext operationCall) {
        System.err.println("Visit operationCall not implemented ");
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
        return null;
    }

    public List<Variable> visitVariables(OCLParser.DeclarationContext declarationContext ) {
        System.err.println("Visit Declarator not implemented");
        List<Variable> vars = new ArrayList<Variable>();
        OclExpression initExpression = null;
        Type type = null;
        if(declarationContext.typeName() != null ) type = new Type(declarationContext.getText());
        if(declarationContext.expression() != null) initExpression = (OclExpression)visitExpression(declarationContext.expression());
        for(OCLParser.VariableContext varContext : declarationContext.variable()) {
            Variable var = new Variable(varContext.NAME().getText());
            if(initExpression != null) {
                var.setInitExpression(initExpression);
            }
            if(type != null) {
                var.setType(type);
            }
            vars.add(var);
        }
        return vars;
    }

    @Override
    public OclAstNode visitPropertyCallExp(OCLParser.PropertyCallExpContext propertyCall) {
        System.err.println("Visit propertyCall not implemented ");
        return null;
    }

    @Override
    public OclAstNode visitIteratorExp(OCLParser.IteratorExpContext iteratorExpCtx) {
        System.err.println("Visit iteratorExp not implemented ");
        IteratorExp iteratorExp = new IteratorExp((OclExpression)visitExpression(iteratorExpCtx.expression()));
        iteratorExp.setName(iteratorExpCtx.NAME().getText());
        List<Variable> vars = new ArrayList<Variable>();
        for(OCLParser.DeclarationContext declarationContext : iteratorExpCtx.declarator().declaration()) {
           List<Variable> auxVars = visitVariables(declarationContext);
           vars.addAll(auxVars);
        }
        iteratorExp.setIterator(vars);
        return iteratorExp;
    }
}

package com.sparsity.ocl.ast;

/**
 * Created by aprat on 10/01/16.
 */
public interface OclAstVisitor {

    public void visit(BooleanLiteralExp booleanLiteralExp);

    public void visit(CallExp callExp);

    public void visit(ConnectableElement connectableElement);

    public void visit(Constraint constraint);

    public void visit(Expression expression);

    public void visit(ExpressionInOcl expressionInOcl);

    public void visit(FeatureCallExp featureCallExp);

    public void visit(IfExp ifExp);

    public void visit(IntegerLiteralExp integerLiteralExp);

    public void visit(IterateExp iterateExp);

    public void visit(IteratorExp iteratorExp);

    public void visit(LetExpression letExpression);

    public void visit(LiteralExp literalExp);

    public void visit(LoopExp loopExp);

    public void visit(NamedElement namedElement);

    public void visit(NavigationCallExp navigationCallExp);

    public void visit(NumericLiteralExp numericLiteralExp);

    public void visit(OclExpression oclExpression);

    public void visit(Operation operation);

    public void visit(OperationCallExp operationCallExp);

    public void visit(Parameter parameter);

    public void visit(PropertyCallExp propertyCallExp);

    public void visit(RealLiteralExp realLiteralExp);

    public void visit(StateExp stateExp);

    public void visit(StringLiteralExp stringLiteralExp);

    public void visit(TupleLiteralExp tupleLiteralExp);

    public void visit(Type type);

    public void visit(TypedElement typedElement);

    public void visit(TypeExp typedExp);

    public void visit(Variable variable);

    public void visit(VariableExp variableExp);
}

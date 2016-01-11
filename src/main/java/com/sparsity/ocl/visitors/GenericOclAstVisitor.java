package com.sparsity.ocl.visitors;

import com.sparsity.ocl.ast.BooleanLiteralExp;
import com.sparsity.ocl.ast.CallExp;
import com.sparsity.ocl.ast.ConnectableElement;
import com.sparsity.ocl.ast.Constraint;
import com.sparsity.ocl.ast.Expression;
import com.sparsity.ocl.ast.ExpressionInOcl;
import com.sparsity.ocl.ast.FeatureCallExp;
import com.sparsity.ocl.ast.IfExp;
import com.sparsity.ocl.ast.IntegerLiteralExp;
import com.sparsity.ocl.ast.IterateExp;
import com.sparsity.ocl.ast.IteratorExp;
import com.sparsity.ocl.ast.LetExpression;
import com.sparsity.ocl.ast.LiteralExp;
import com.sparsity.ocl.ast.LoopExp;
import com.sparsity.ocl.ast.NamedElement;
import com.sparsity.ocl.ast.NavigationCallExp;
import com.sparsity.ocl.ast.NumericLiteralExp;
import com.sparsity.ocl.ast.Operation;
import com.sparsity.ocl.ast.OperationCallExp;
import com.sparsity.ocl.ast.Parameter;
import com.sparsity.ocl.ast.PropertyCallExp;
import com.sparsity.ocl.ast.RealLiteralExp;
import com.sparsity.ocl.ast.StateExp;
import com.sparsity.ocl.ast.StringLiteralExp;
import com.sparsity.ocl.ast.TupleLiteralExp;
import com.sparsity.ocl.ast.Type;
import com.sparsity.ocl.ast.TypeExp;
import com.sparsity.ocl.ast.TypedElement;
import com.sparsity.ocl.ast.Variable;
import com.sparsity.ocl.ast.VariableExp;

public interface GenericOclAstVisitor<A, T> {

	public A visit(BooleanLiteralExp booleanLiteralExp, T context);

	public A visit(CallExp callExp, T context);

	public A visit(ConnectableElement connectableElement, T context);

	public A visit(Constraint constraint, T context);

	public A visit(ExpressionInOcl expressionInOcl, T context);

	public A visit(FeatureCallExp featureCallExp, T context);

	public A visit(IfExp ifExp, T context);

	public A visit(IntegerLiteralExp integerLiteralExp, T context);

	public A visit(IterateExp iterateExp, T context);

	public A visit(IteratorExp iteratorExp, T context);

	public A visit(LetExpression letExpression, T context);

	public A visit(LiteralExp literalExp, T context);

	public A visit(LoopExp loopExp, T context);

	public A visit(NamedElement namedElement, T context);

	public A visit(NavigationCallExp navigationCallExp, T context);

	public A visit(NumericLiteralExp numericLiteralExp, T context);

	public A visit(Operation operation, T context);

	public A visit(OperationCallExp operationCallExp, T context);

	public A visit(Parameter parameter, T context);

	public A visit(PropertyCallExp propertyCallExp, T context);

	public A visit(RealLiteralExp realLiteralExp, T context);

	public A visit(StateExp stateExp, T context);

	public A visit(StringLiteralExp stringLiteralExp, T context);

	public A visit(TupleLiteralExp tupleLiteralExp, T context);

	public A visit(Type type, T context);

	public A visit(TypedElement typedElement, T context);

	public A visit(TypeExp typedExp, T context);

	public A visit(Variable variable, T context);

	public A visit(VariableExp variableExp, T context);

}

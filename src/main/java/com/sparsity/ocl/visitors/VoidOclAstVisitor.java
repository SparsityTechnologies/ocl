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

/**
 * Created by aprat on 10/01/16.
 */
public interface VoidOclAstVisitor<T> {

    public void visit(BooleanLiteralExp booleanLiteralExp, T context);

    public void visit(CallExp callExp, T context);

    public void visit(ConnectableElement connectableElement, T context);

    public void visit(Constraint constraint, T context);

    public void visit(Expression expression, T context);

    public void visit(ExpressionInOcl expressionInOcl, T context);

    public void visit(FeatureCallExp featureCallExp, T context);

    public void visit(IfExp ifExp, T context);

    public void visit(IntegerLiteralExp integerLiteralExp, T context);

    public void visit(IterateExp iterateExp, T context);

    public void visit(IteratorExp iteratorExp, T context);

    public void visit(LetExpression letExpression, T context);

    public void visit(LiteralExp literalExp, T context);

    public void visit(LoopExp loopExp, T context);

    public void visit(NamedElement namedElement, T context);

    public void visit(NavigationCallExp navigationCallExp, T context);

    public void visit(NumericLiteralExp numericLiteralExp, T context);

    public void visit(Operation operation, T context);

    public void visit(OperationCallExp operationCallExp, T context);

    public void visit(Parameter parameter, T context);

    public void visit(PropertyCallExp propertyCallExp, T context);

    public void visit(RealLiteralExp realLiteralExp, T context);

    public void visit(StateExp stateExp, T context);

    public void visit(StringLiteralExp stringLiteralExp, T context);

    public void visit(TupleLiteralExp tupleLiteralExp, T context);

    public void visit(Type type, T context);

    public void visit(TypedElement typedElement, T context);

    public void visit(TypeExp typedExp, T context);

    public void visit(Variable variable, T context);

    public void visit(VariableExp variableExp, T context);
}

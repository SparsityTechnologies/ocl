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
package com.sparsity.ocl.ast.printer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparsity.ocl.ast.*;
import com.sparsity.ocl.visitors.VoidOclAstVisitor;

/**
 * Created by aprat on 17/12/15.
 */
public class OclAstJsonPrinter<A> implements VoidOclAstVisitor<A> {
	String text = null;

	public OclAstJsonPrinter() {
		text = new String();
	}

	public String getText() {
		return text;
	}

	@Override
	public void visit(BooleanLiteralExp booleanLiteralExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(CallExp callExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ConnectableElement connectableElement, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Constraint constraint, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ExpressionInOcl expressionInOcl, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FeatureCallExp featureCallExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IfExp ifExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IntegerLiteralExp integerLiteralExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IterateExp iterateExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IteratorExp iteratorExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(LetExpression letExpression, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(LiteralExp literalExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(LoopExp loopExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NamedElement namedElement, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NavigationCallExp navigationCallExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NumericLiteralExp numericLiteralExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Operation operation, A context) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			text = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(operation);
		} catch (Exception e) {

		}
	}

	@Override
	public void visit(OperationCallExp operationCallExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Parameter parameter, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(PropertyCallExp propertyCallExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RealLiteralExp realLiteralExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StateExp stateExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(StringLiteralExp stringLiteralExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(TupleLiteralExp tupleLiteralExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Type type, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(TypedElement typedElement, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(TypeExp typedExp, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Variable variable, A context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VariableExp variableExp, A context) {
		// TODO Auto-generated method stub

	}
}

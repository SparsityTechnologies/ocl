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

import com.sparsity.ocl.ast.*;
import com.sparsity.ocl.visitors.VoidOclAstVisitor;

/**
 * Created by aprat on 17/12/15.
 */
public class OclAstTextPrinter<T>  implements VoidOclAstVisitor<T> {

    StringBuffer strBuffer;
    StringBuffer indentBuffer;

    public OclAstTextPrinter() {
        strBuffer = new StringBuffer();
        indentBuffer = new StringBuffer();
    }

    private void print(String str) {
        strBuffer.append(indentBuffer);
        strBuffer.append(str);
    }

    private void println(String str) {
        print(str+"\n");
    }

    private void addIndent() {
        indentBuffer.append('\t');
    }

    private void removeIndent() {
        indentBuffer.deleteCharAt(indentBuffer.length()-1);
    }

    public String getText() {
        return strBuffer.toString();
    }

    public void visit(Operation operation, T context) {
        print("Operation: "+operation.getName()+"\n");
        addIndent();
        print("parameters: \n");
        int numParams = operation.numParameters();
        addIndent();
        for(int i = 0; i < numParams; ++i) {
            Parameter param = operation.getParameter(i);
            param.accept(this, context);
        }
        removeIndent();
        int numPre = operation.numPre();
        for(int i = 0; i < numPre; ++i) {
            Constraint pre = operation.getPre(i);
            println("pre:");
            addIndent();
            pre.accept(this, context);
            removeIndent();
        }
        removeIndent();

        addIndent();
        int numPost = operation.numPost();
        for(int i = 0; i < numPost; ++i) {
            println("post:");
            Constraint post = operation.getPost(i);
            addIndent();
            post.accept(this, context);
            removeIndent();
        }
        removeIndent();

        addIndent();
        println("body:");
        Constraint body = operation.getBody();
        if(body != null) {
            addIndent();
            body.accept(this, context);
            removeIndent();
        }
        removeIndent();

    }

    public void visit(Constraint operation, T context) {
        println("Constraint:");
        addIndent();
        operation.getExpression().accept(this, context);
        removeIndent();
    }

    public void visit(Parameter parameter, T context) {
        println("parameter:");
        addIndent();
        println("Name: "+parameter.getName());
        println("Type: "+parameter.getType().getTypeName());
        removeIndent();
    }

    public void visit(TypedElement typedElement, T context) {

    }

    public void visit(LetExpression letExpression, T context) {
        println("LetExpression:");
        addIndent();
        letExpression.getVariable().accept(this, context);
//        letExpression.getInExpression().accept(this);
        removeIndent();
    }

    public void visit(Variable variable, T context) {
        println("Variable:");
        addIndent();
        if(variable.getName() != null) {
            println("Name: " + variable.getName());
        }
        if(variable.getType() != null) {
            println("Type: " + variable.getType().getTypeName());
        }
        if(variable.getInitExpression() != null) {
            variable.getInitExpression().accept(this, context);
        }
        removeIndent();
    }

    public void visit(Expression expression, T context) {
        expression.accept(this, context);
    }

    public void visit(ExpressionInOcl expressionInOcl, T context) {
        expressionInOcl.getBodyExpression().accept(this, context);
    }

	@Override
	public void visit(BooleanLiteralExp booleanLiteralExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CallExp callExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ConnectableElement connectableElement, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FeatureCallExp featureCallExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IfExp ifExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IntegerLiteralExp integerLiteralExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IterateExp iterateExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IteratorExp iteratorExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LiteralExp literalExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LoopExp loopExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NamedElement namedElement, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NavigationCallExp navigationCallExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NumericLiteralExp numericLiteralExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(OperationCallExp operationCallExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(PropertyCallExp propertyCallExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(RealLiteralExp realLiteralExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StateExp stateExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StringLiteralExp stringLiteralExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TupleLiteralExp tupleLiteralExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Type type, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(TypeExp typedExp, T context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VariableExp variableExp, T context) {
		// TODO Auto-generated method stub
		
	}
}

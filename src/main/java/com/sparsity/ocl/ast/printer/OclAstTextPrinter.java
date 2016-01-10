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

/**
 * Created by aprat on 17/12/15.
 */
public class OclAstTextPrinter  implements OclAstPrinter {

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

    public void visit(Operation operation) {
        print("Operation: "+operation.getName()+"\n");
        addIndent();
        print("parameters: \n");
        int numParams = operation.numParameters();
        addIndent();
        for(int i = 0; i < numParams; ++i) {
            Parameter param = operation.getParameter(i);
            param.accept(this);
        }
        removeIndent();
        int numPre = operation.numPre();
        for(int i = 0; i < numPre; ++i) {
            Constraint pre = operation.getPre(i);
            println("pre:");
            addIndent();
            pre.accept(this);
            removeIndent();
        }
        removeIndent();

        addIndent();
        int numPost = operation.numPost();
        for(int i = 0; i < numPost; ++i) {
            println("post:");
            Constraint post = operation.getPost(i);
            addIndent();
            post.accept(this);
            removeIndent();
        }
        removeIndent();

        addIndent();
        println("body:");
        Constraint body = operation.getBody();
        if(body != null) {
            addIndent();
            body.accept(this);
            removeIndent();
        }
        removeIndent();

    }

    public void visit(Constraint operation) {
        println("Constraint:");
        addIndent();
        operation.getExpression().accept(this);
        removeIndent();
    }

    public void visit(Parameter parameter) {
        println("parameter:");
        addIndent();
        println("Name: "+parameter.getName());
        println("Type: "+parameter.getType().getTypeName());
        removeIndent();
    }

    public void visit(TypedElement typedElement) {

    }

    public void visit(LetExpression letExpression) {
        println("LetExpression:");
        addIndent();
        letExpression.getVariable().accept(this);
//        letExpression.getInExpression().accept(this);
        removeIndent();
    }

    public void visit(Variable variable) {
        println("Variable:");
        addIndent();
        if(variable.getName() != null) {
            println("Name: " + variable.getName());
        }
        if(variable.getType() != null) {
            println("Type: " + variable.getType().getTypeName());
        }
        if(variable.getInitExpression() != null) {
            variable.getInitExpression().accept(this);
        }
        removeIndent();
    }

    public void visit(Expression expression) {
        expression.accept(this);
    }

    public void visit(ExpressionInOcl expressionInOcl) {
        expressionInOcl.getBodyExpression().accept(this);
    }

    public void visit(OclExpression oclExpression) {
        oclExpression.accept(this);
    }
}

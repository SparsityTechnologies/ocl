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

/**
 * Created by aprat on 17/12/15.
 */
public class OclAstJsonPrinter implements OclAstPrinter {
    String text = null;

    public OclAstJsonPrinter() {
        text = new String();
    }

    public String getText() {
        return text;
    }

    public void visit(Operation operation) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            text = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(operation);
        } catch(Exception e ) {

        }
    }

    public void visit(Constraint constraint) {

    }

    public void visit(Parameter parameter) {

    }

    public void visit(TypedElement typedElement) {

    }

    public void visit(LetExpression letExpression) {

    }

    public void visit(Variable variable) {

    }

    public void visit(Expression expression) {

    }

    public void visit(ExpressionInOcl expressionInOcl) {

    }

    public void visit(OclExpression oclExpression) {

    }
}

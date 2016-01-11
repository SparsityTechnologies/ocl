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
package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;
import com.sparsity.ocl.visitors.VoidOclAstVisitor;

/**
 * Created by aprat on 15/12/15.
 */
public class Variable extends TypedElement {

    private OclExpression initExpression = null;

    public Variable(String name ) {
        super(name);
    }

    public void accept(OclAstPrinter printer) {
        printer.visit(this);
    }

    public OclExpression getInitExpression() {
        return initExpression;
    }

    public void setInitExpression(OclExpression initExpression) {
        this.initExpression = initExpression;
    }

    @Override
    public <A> void accept(VoidOclAstVisitor<A> visitor, A arg){
        visitor.visit(this, arg);
    }
}

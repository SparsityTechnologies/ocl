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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aprat on 15/12/15.
 */
public class LoopExp extends CallExp {

    protected List<Variable> iterator = null;
    protected OclExpression body = null;

    public LoopExp(OclExpression body) {
        this.iterator = new ArrayList<Variable>();
        this.body = body;
    }

    public List<Variable> getIterator() {
        return iterator;
    }

    public void setIterator(List<Variable> iterator) {
        this.iterator = iterator;
    }

    public OclExpression getBody() {
        return body;
    }

    public void setBody(OclExpression body) {
        this.body = body;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

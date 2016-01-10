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
 * Created by aprat on 30/12/15.
 */
public class OperationCallExp extends FeatureCallExp {

    protected List<OclExpression> arguments = null;
    protected String referredOperation = null;

    public OperationCallExp() {
        this.arguments = new ArrayList<OclExpression>();
    }

    public String getReferredOperation() {
        return referredOperation;
    }

    public void setReferredOperation(String referredOperation) {
        this.referredOperation = referredOperation;
    }

    public List<OclExpression> getArguments() {
        return arguments;
    }

    public void setArguments(List<OclExpression> arguments) {
        this.arguments = arguments;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

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

import com.sparsity.ocl.visitors.GenericOclAstVisitor;
import com.sparsity.ocl.visitors.VoidOclAstVisitor;

/**
 * Created by aprat on 16/12/15.
 */
public class Type extends OclAstNode {
    private String typeName;

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public <A> void accept(VoidOclAstVisitor<A> visitor, A arg) {
        visitor.visit(this, arg);
    }

	@Override
	public <A, T> T accept(GenericOclAstVisitor<T, A> visitor, A arg) {
		
		return visitor.visit(this, arg);
	}
}

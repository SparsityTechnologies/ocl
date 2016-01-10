package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.TypedElement;

/**
 * Created by aprat on 15/12/15.
 */
public class OclExpression extends TypedElement {

    public OclExpression() {
    }

    public OclExpression(String name) {
        super(name);
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

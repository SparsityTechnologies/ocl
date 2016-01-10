package com.sparsity.ocl.ast;

/**
 * Created by aprat on 5/01/16.
 */
public class NumericLiteralExp extends LiteralExp {

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

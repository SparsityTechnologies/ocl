package com.sparsity.ocl.ast;

/**
 * Created by aprat on 15/12/15.
 */
public class FeatureCallExp extends CallExp {

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

package com.sparsity.ocl.ast;

/**
 * Created by aprat on 31/12/15.
 */
public class NavigationCallExp extends FeatureCallExp {

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

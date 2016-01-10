package com.sparsity.ocl.ast;

/**
 * Created by aprat on 31/12/15.
 */
public class PropertyCallExp extends NavigationCallExp  {
    protected String referredProperty = "";

    public String getReferredProperty() {
        return referredProperty;
    }

    public void setReferredProperty(String referredProperty) {
        this.referredProperty = referredProperty;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

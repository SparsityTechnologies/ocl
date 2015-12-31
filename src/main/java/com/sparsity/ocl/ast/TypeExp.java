package com.sparsity.ocl.ast;

/**
 * Created by aprat on 15/12/15.
 */
public class TypeExp extends OclExpression {
    protected Type referredType = null;

    public Type getReferredType() {
        return referredType;
    }

    public void setReferredType(Type referredType) {
        this.referredType = referredType;
    }
}

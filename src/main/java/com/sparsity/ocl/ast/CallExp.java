package com.sparsity.ocl.ast;

/**
 * Created by aprat on 15/12/15.
 */
public class CallExp extends OclExpression {
    protected OclExpression source = null;

    public OclExpression getSource() {
        return source;
    }

    public void setSource(OclExpression source) {
        this.source = source;
    }
}

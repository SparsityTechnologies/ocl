package com.sparsity.ocl.ast;

/**
 * Created by aprat on 15/12/15.
 */
public class VariableExp extends OclExpression {
    protected String referedVariable;

    public VariableExp( String referedVariable ) {
        this.referedVariable = referedVariable;
    }

    public String getReferedVariable() {
        return referedVariable;
    }

    public void setReferedVariable(String referedVariable) {
        this.referedVariable = referedVariable;
    }
}

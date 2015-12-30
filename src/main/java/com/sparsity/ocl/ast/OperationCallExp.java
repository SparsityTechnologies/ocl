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
}

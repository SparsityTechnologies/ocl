package com.sparsity.ocl.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aprat on 15/12/15.
 */
public class ExpressionInOcl extends Expression {

    private OclExpression bodyExpression = null;

    public ExpressionInOcl( OclExpression bodyExpression) {
        this.bodyExpression = bodyExpression;
    }
}

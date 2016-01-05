package com.sparsity.ocl.ast;

/**
 * Created by aprat on 5/01/16.
 */
public class BooleanLiteralExp extends LiteralExp {

    protected boolean booleanSymbol;

    public BooleanLiteralExp(boolean booleanSymbol) {
        this.booleanSymbol = booleanSymbol;
    }

    public Boolean getBooleanSymbol() {
        return booleanSymbol;
    }
}

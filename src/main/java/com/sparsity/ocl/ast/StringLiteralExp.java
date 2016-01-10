package com.sparsity.ocl.ast;

/**
 * Created by aprat on 5/01/16.
 */
public class StringLiteralExp extends LiteralExp {
    protected String stringSymbol;

    public StringLiteralExp(String stringSymbol) {
        this.stringSymbol = stringSymbol;
    }

    public String getStringSymbol() {
        return stringSymbol;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }

}

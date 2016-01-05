package com.sparsity.ocl.ast;

/**
 * Created by aprat on 5/01/16.
 */
public class IntegerLiteralExp extends NumericLiteralExp {

    protected int integerSymbol;

    public IntegerLiteralExp(int integerSymbol) {
        this.integerSymbol = integerSymbol;
    }

    public int getIntegerSymbol() {
        return integerSymbol;
    }
}

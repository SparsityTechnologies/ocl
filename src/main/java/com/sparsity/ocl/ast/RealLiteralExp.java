package com.sparsity.ocl.ast;

import com.sparsity.ocl.OCLParser;

/**
 * Created by aprat on 5/01/16.
 */
public class RealLiteralExp extends NumericLiteralExp {

    protected double realSymbol;

    public RealLiteralExp(double realSymbol) {
        this.realSymbol = realSymbol;
    }

    public double getRealSymbol() {
        return realSymbol;
    }
}

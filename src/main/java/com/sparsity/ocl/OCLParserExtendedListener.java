package com.sparsity.ocl;

import com.sparsity.ocl.OCLParser;
import com.sparsity.ocl.OCLParserBaseListener;

/**
 * Created by aprat on 14/12/15.
 */
public class OCLParserExtendedListener extends OCLParserBaseListener {
    @Override
    public void enterConstraint(OCLParser.ConstraintContext ctx) {
        System.out.println(ctx.getText());

    }
}

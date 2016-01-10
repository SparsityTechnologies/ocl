package com.sparsity.ocl.ast;

/**
 * Created by aprat on 15/12/15.
 */
public class IteratorExp extends LoopExp {

    public IteratorExp(OclExpression body) {
        super(body);
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

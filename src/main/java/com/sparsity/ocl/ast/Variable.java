package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

/**
 * Created by aprat on 15/12/15.
 */
public class Variable extends TypedElement {

    private OclExpression initExpression = null;

    public Variable(String name ) {
        super(name);
    }

    public void accept(OclAstPrinter printer) {
        printer.visit(this);
    }

    public OclExpression getInitExpression() {
        return initExpression;
    }

    public void setInitExpression(OclExpression initExpression) {
        this.initExpression = initExpression;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

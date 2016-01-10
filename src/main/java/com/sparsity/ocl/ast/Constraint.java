package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

/**
 * Created by aprat on 16/12/15.
 */
public class Constraint extends OclAstNode {

    protected Expression expression = null;

    public Constraint(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(OclAstPrinter printer) {
        printer.visit(this);
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

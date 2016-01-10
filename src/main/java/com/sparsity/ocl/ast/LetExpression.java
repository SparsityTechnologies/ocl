package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

/**
 * Created by aprat on 17/12/15.
 */
public class LetExpression extends OclExpression {

    private Variable variable = null;
    private OclExpression inExpression = null;

    public LetExpression(OclExpression inExpression, Variable variable) {
        this.inExpression = inExpression;
        this.variable = variable;
    }

    public void accept(OclAstPrinter printer) {
        printer.visit(this);
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public OclExpression getInExpression() {
        return inExpression;
    }

    public void setInExpression(OclExpression inExpression) {
        this.inExpression = inExpression;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

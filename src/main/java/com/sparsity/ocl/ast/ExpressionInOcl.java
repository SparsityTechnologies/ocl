package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

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

    public void accept(OclAstPrinter printer) {
        printer.visit(this);
    }

    public OclExpression getBodyExpression() {
        return bodyExpression;
    }

    public void setBodyExpression(OclExpression bodyExpression) {
        this.bodyExpression = bodyExpression;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

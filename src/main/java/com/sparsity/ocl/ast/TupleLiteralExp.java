package com.sparsity.ocl.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aprat on 4/01/16.
 */
public class TupleLiteralExp extends LiteralExp {

    protected List<Variable> parts = null;

    public TupleLiteralExp() {
        parts = new ArrayList<Variable>();
    }

    public List<Variable> getParts() {
        return parts;
    }

    public void setParts(List<Variable> parts) {
        this.parts = parts;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

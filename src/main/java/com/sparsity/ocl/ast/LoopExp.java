package com.sparsity.ocl.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aprat on 15/12/15.
 */
public class LoopExp extends CallExp {

    protected List<Variable> iterator = null;
    protected OclExpression body = null;

    public LoopExp(OclExpression body) {
        this.iterator = new ArrayList<Variable>();
        this.body = body;
    }

    public List<Variable> getIterator() {
        return iterator;
    }

    public void setIterator(List<Variable> iterator) {
        this.iterator = iterator;
    }

    public OclExpression getBody() {
        return body;
    }

    public void setBody(OclExpression body) {
        this.body = body;
    }
}

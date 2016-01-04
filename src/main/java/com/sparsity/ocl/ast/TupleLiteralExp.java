package com.sparsity.ocl.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aprat on 4/01/16.
 */
public class TupleLiteralExp extends LiteralExp {

    protected List<TupleLiteralPart> parts = null;

    public TupleLiteralExp() {
        parts = new ArrayList<TupleLiteralPart>();
    }

    public List<TupleLiteralPart> getParts() {
        return parts;
    }

    public void setParts(List<TupleLiteralPart> parts) {
        this.parts = parts;
    }
}

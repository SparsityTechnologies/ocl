package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

/**
 * Created by aprat on 15/12/15.
 */
public class TypedElement extends NamedElement {

    protected Type type = null;

    public TypedElement() {
    }

    public TypedElement(String name) {
        super(name);
    }

    public void accept(OclAstPrinter printer) {
        printer.visit(this);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

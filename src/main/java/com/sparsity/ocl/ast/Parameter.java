package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

/**
 * Created by aprat on 16/12/15.
 */
public class Parameter extends NamedElement {

    protected Type type;

    public Parameter(String name, Type type)  {
        super(name);
        this.type = type;
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

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

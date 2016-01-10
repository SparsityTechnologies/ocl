package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

/**
 * Created by aprat on 16/12/15.
 */
public class NamedElement extends OclAstNode {


    protected String name = null;

    public NamedElement() {

    }

    public NamedElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void accept(OclAstPrinter visitor) {
        System.err.println("Unimplemented visitor");
        assert false;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }

}

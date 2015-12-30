package com.sparsity.ocl.ast;

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
}

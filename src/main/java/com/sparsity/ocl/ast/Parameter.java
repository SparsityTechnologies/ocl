package com.sparsity.ocl.ast;

/**
 * Created by aprat on 16/12/15.
 */
public class Parameter extends NamedElement {

    protected Type type;

    public Parameter(String name, Type type)  {
        super(name);
        this.type = type;
    }
}

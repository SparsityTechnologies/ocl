package com.sparsity.ocl.ast;

/**
 * Created by aprat on 16/12/15.
 */
public class Type extends OclAstNode {
    private String typeName;

    public Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

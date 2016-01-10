package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

/**
 * Created by aprat on 15/12/15.
 */
public abstract class OclAstNode {

    private String nodeType = null;

    public OclAstNode(){
        String className = this.getClass().getName();
        int index = className.lastIndexOf('.');
        nodeType = className.substring(index+1,className.length());

    }

    public abstract void accept(OclAstPrinter printer);

    public abstract void accept(OclAstVisitor visitor);

    public String getNodeType() {
        return nodeType;
    }
}

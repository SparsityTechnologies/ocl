package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

/**
 * Created by aprat on 15/12/15.
 */
public class OclAstNode {

    private String nodeType = null;

    public OclAstNode(){
        nodeType = this.getClass().getName();
    }

    public void accept(OclAstPrinter printer) {

    }

    public String getNodeType() {
        return nodeType;
    }
}

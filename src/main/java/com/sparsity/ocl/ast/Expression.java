package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

/**
 * Created by aprat on 16/12/15.
 */
public class Expression extends OclAstNode {

    public void accept( OclAstPrinter printer ) {
        printer.visit(this);
    }
}

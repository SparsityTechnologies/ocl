package com.sparsity.ocl.ast.printer;

import com.sparsity.ocl.ast.Operation;

/**
 * Created by aprat on 16/12/15.
 */
public interface OclAstPrinter {
    public void toString(Operation operation);
}

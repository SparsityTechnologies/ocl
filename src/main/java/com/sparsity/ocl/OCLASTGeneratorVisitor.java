package com.sparsity.ocl;

import com.sparsity.ocl.ast.OclAstNode;
import com.sparsity.ocl.ast.OclAst;

/**
 * Created by aprat on 15/12/15.
 */
public class OCLASTGeneratorVisitor extends OCLParserBaseVisitor<OclAstNode> {

    public OclAst ast;

    @Override
    public OclAstNode visitConstraint(OCLParser.ConstraintContext ctx) { return visitChildren(ctx); }

}

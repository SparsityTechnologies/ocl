package com.sparsity.ocl;

import com.sparsity.ocl.ast.*;

/**
 * Created by aprat on 15/12/15.
 */
public class OCLASTGeneratorVisitor extends OCLParserBaseVisitor<OclAstNode> {

    @Override
    public OclAstNode visitOclStatement(OCLParser.OclStatementContext ctx  ) {
        int index = 0;
        Operation operation = new Operation();
        for( OCLParser.ExpressionContext cContext : ctx.expression() ) {
            OclExpression oclExpression = (OclExpression)visitExpression(cContext);
            ExpressionInOcl expressionInOcl = new ExpressionInOcl(oclExpression);
            Constraint constraint = new Constraint(expressionInOcl);
            String stereotype = ctx.stereotype(index).getText();
            if(stereotype == "body" ) {
                operation.setBody(constraint);
            } else if (stereotype == "post") {
                operation.addPost(constraint);
            } else if (stereotype == "pre") {
                operation.addPre(constraint);
            }
            ++index;
        }
 //       OclAstNode rootNode =  new ExpressionInOcl();
        return operation;
    }

}

package com.sparsity.ocl.ast.printer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparsity.ocl.ast.*;

/**
 * Created by aprat on 17/12/15.
 */
public class OclAstJsonPrinter implements OclAstPrinter {
    String text = null;

    public OclAstJsonPrinter() {
        text = new String();
    }

    public String getText() {
        return text;
    }

    public void visit(Operation operation) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            text = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(operation);
        } catch(Exception e ) {

        }
    }

    public void visit(Constraint constraint) {

    }

    public void visit(Parameter parameter) {

    }

    public void visit(TypedElement typedElement) {

    }

    public void visit(LetExpression letExpression) {

    }

    public void visit(Variable variable) {

    }

    public void visit(Expression expression) {

    }

    public void visit(ExpressionInOcl expressionInOcl) {

    }

    public void visit(OclExpression oclExpression) {

    }
}

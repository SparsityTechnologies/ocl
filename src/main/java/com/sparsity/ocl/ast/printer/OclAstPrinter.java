package com.sparsity.ocl.ast.printer;

import com.sparsity.ocl.ast.*;

/**
 * Created by aprat on 16/12/15.
 */
public interface OclAstPrinter {
    public void visit(Operation operation);
    public void visit(Constraint constraint);
    public void visit(Parameter parameter);
    public void visit(TypedElement typedElement);
    public void visit(LetExpression letExpression);
    public void visit(Variable variable);
    public void visit(Expression expression);
    public void visit(ExpressionInOcl expressionInOcl);
    public void visit(OclExpression oclExpression);
}

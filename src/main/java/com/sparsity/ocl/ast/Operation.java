package com.sparsity.ocl.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aprat on 16/12/15.
 */
public class Operation extends OclAstNode {

    protected List<Parameter> parameters = null;
    protected List<Constraint> pre = null;
    protected List<Constraint> post = null;
    protected Constraint body = null;


    public Operation() {
        this.parameters = new ArrayList<Parameter>();
        this.pre = new ArrayList<Constraint>();
        this.post = new ArrayList<Constraint>();
    }

    public void addParameter(Parameter parameter) {
        this.parameters.add(parameter);
    }

    public Parameter getParameter(int i) {
        return this.parameters.get(i);
    }

    public int numParameters() {
        return parameters.size();
    }

    public void addPost(Constraint constraint) {
        post.add(constraint);
    }

    public Constraint getPost( int i ) {
        return post.get(i);
    }

    public int numPost() {
        return post.size();
    }

    public void addPre(Constraint constraint) {
        pre.add(constraint);
    }

    public Constraint getPre( int i ) {
        return pre.get(i);
    }

    public int numPre() {
        return pre.size();
    }

    public Constraint getBody() {
        return body;
    }

    public void setBody(Constraint body) {
        this.body = body;
    }
}

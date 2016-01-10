/* Copyright 2015-2016 Sparsity Technologies
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.*/
package com.sparsity.ocl.ast;

import com.sparsity.ocl.ast.printer.OclAstPrinter;

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
    protected String name = null;


    public Operation( String operationName ) {
        this.name = operationName;
        this.parameters = new ArrayList<Parameter>();
        this.pre = new ArrayList<Constraint>();
        this.post = new ArrayList<Constraint>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public void accept(OclAstPrinter printer) {
        System.err.println("Unimplemented visitor");
        assert false;
    }

    @Override
    public void accept(OclAstVisitor visitor) {
        visitor.visit(this);
    }
}

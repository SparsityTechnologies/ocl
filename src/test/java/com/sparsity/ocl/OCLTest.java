package com.sparsity.ocl;


import com.sparsity.ocl.ast.Operation;
import com.sparsity.ocl.ast.printer.OclAstJsonPrinter;
import org.antlr.v4.runtime.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.sparsity.ocl.*;

import java.io.*;

/**
 * Created by aprat on 14/12/15.
 */
public class OCLTest {
     public String convertStreamToString(InputStream is)
            throws IOException {
        //
        // To convert the InputStream to String we use the
        // Reader.read(char[] buffer) method. We iterate until the
        // Reader return -1 which means there's no more data to
        // read. We use the StringWriter class to produce the string.
        //
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    public void testQuery(String query ) throws Exception {
        ANTLRInputStream inputStream = new ANTLRInputStream(query);
        OCLLexer lexer = new OCLLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OCLParser parser = new OCLParser(tokens);
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        OCLParser.OclStatementContext oclStatement = parser.oclStatement();
        OCLASTGeneratorVisitor visitor = new OCLASTGeneratorVisitor();
        Operation operation = (Operation)visitor.visitOclStatement(oclStatement);
        OclAstJsonPrinter textPrinter = new OclAstJsonPrinter();
        textPrinter.visit(operation);
        System.out.println(textPrinter.getText());
    }

    private void printHeader(String header) {
        int sideSize = header.length()*2 + 2;
        for(int i = 0; i < sideSize; ++i) {
            System.out.print("*");
        }
        System.out.println();
        for(int i = 0; i < (sideSize - 2)/4; ++i) {
            System.out.print("*");
        }
        System.out.print(" ");
        System.out.print(header);
        System.out.print(" ");
        for(int i = 0; i < (sideSize - 2)/4; ++i) {
            System.out.print("*");
        }
        System.out.println();
        for(int i = 0; i < sideSize; ++i) {
            System.out.print("*");
        }
        System.out.println();
    }

    @Test
    public void ldbcQuery1() throws Exception {
        printHeader("Testing LDBC Interactive Query 1");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query1.ocl")));
    }

    @Test
    public void ldbcQuery2() throws Exception {
        printHeader("Testing LDBC Interactive Query 2");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query2.ocl")));
    }

    @Test
    public void ldbcQuery3() throws Exception {
        printHeader("Testing LDBC Interactive Query 3");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query3.ocl")));
    }

    @Test
    public void ldbcQuery4() throws Exception {
        printHeader("Testing LDBC Interactive Query 4");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query4.ocl")));
    }

    @Test
    public void ldbcQuery5() throws Exception {
        printHeader("Testing LDBC Interactive Query 5");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query5.ocl")));
    }

    @Test
    public void ldbcQuery6() throws Exception {
        printHeader("Testing LDBC Interactive Query 6");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query6.ocl")));
    }

    @Test
    public void ldbcQuery7() throws Exception {
        printHeader("Testing LDBC Interactive Query 7");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query7.ocl")));
    }

    @Test
    public void ldbcQuery8() throws Exception {
        printHeader("Testing LDBC Interactive Query 8");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query8.ocl")));
    }

    @Test
    public void ldbcQuery9() throws Exception {
        printHeader("Testing LDBC Interactive Query 9");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query9.ocl")));
    }

    @Test
    public void ldbcQuery10() throws Exception {
        printHeader("Testing LDBC Interactive Query 10");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query10.ocl")));
    }

    @Test
    public void ldbcQuery11() throws Exception {
        printHeader("Testing LDBC Interactive Query 11");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query11.ocl")));
    }

    @Test
    public void ldbcQuery12() throws Exception {
        printHeader("Testing LDBC Interactive Query 12");
        testQuery(convertStreamToString(
                getClass().getResourceAsStream("/ldbc/interactive/query12.ocl")));
    }
}

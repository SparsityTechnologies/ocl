package com.sparsity.ocl;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import com.sparsity.ocl.*;

/**
 * Created by aprat on 14/12/15.
 */
public class OCLTest {

    @Test
    public void dummyTest() throws Exception {
        ANTLRInputStream inputStream = new ANTLRInputStream(getClass().getResourceAsStream("/test.in"));
        OCLLexer lexer = new OCLLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        OCLParser parser = new OCLParser(tokens);
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        OCLParser.ConstraintContext constraintContext = parser.constraint();
        ParseTreeWalker walker = new ParseTreeWalker();
        OCLParserExtendedListener listener = new OCLParserExtendedListener();
        walker.walk(listener, constraintContext);

    }
}

package io.kettil.schema;

import io.kettil.schema.ast.SchemaDef;
import io.kettil.schema.ast.SchemaExtractor;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.InputStream;

@UtilityClass
public class SchemaFactory {
    public static class ThrowingErrorListener extends BaseErrorListener {
        public static final ThrowingErrorListener INSTANCE = new ThrowingErrorListener();

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
            throws ParseCancellationException {
            throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
        }
    }

    public static SchemaDef parse(String text) {
        return parse(CharStreams.fromString(text));
    }

    @SneakyThrows
    public static SchemaDef parse(InputStream inputStream) {
        return parse(CharStreams.fromStream(inputStream));
    }

    private static SchemaDef parse(CharStream input) {
        SchemaLexer schemaLexer = new SchemaLexer(input);
        schemaLexer.removeErrorListeners();
        schemaLexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        CommonTokenStream tokens = new CommonTokenStream(schemaLexer);

        SchemaParser parser = new SchemaParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        SchemaParser.SchemaDefContext tree = parser.schemaDef();

//        Ast ast = new AstBuilder().visit(tree);
//        return (Schema) ast;


        SchemaExtractor extractor = new SchemaExtractor();
        ParseTreeWalker.DEFAULT.walk(extractor, tree);

        return extractor.getSchema();
    }
}

package io.kettil.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import io.kettil.schema.ast.SchemaDef;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SchemaDefFactoryTest {
    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();

    static final String[][] SCHEMAS = {
        {"arrow.schema", "arrow.schema.expected.json"},
        {"basic.schema", "basic.schema.expected.json"},
        {"crosstenant.schema", "crosstenant.schema.expected.json"},
        {"doccomments.schema", "doccomments.schema.expected.json"},
        {"empty.schema", "empty.schema.expected.json"},
        {"example.schema", "example.schema.expected.json"},
        {"global_admin.schema", "global_admin.schema.expected.json"},
        {"group.schema", "group.schema.expected.json"},
        {"indentedcomments.schema", "indentedcomments.schema.expected.json"},
        {"multidef.schema", "multidef.schema.expected.json"},
        {"multiparen.schema", "multiparen.schema.expected.json"},
        {"nil.schema", "nil.schema.expected.json"},
        {"parens.schema", "parens.schema.expected.json"},
        {"recursive_permissions.schema", "recursive_permissions.schema.expected.json"},
        {"recursive_permissions_diff_resources.schema", "recursive_permissions_diff_resources.schema.expected.json"},
        {"synthetic_relations.schema", "synthetic_relations.schema.expected.json"},
        {"wildcard.schema", "wildcard.schema.expected.json"},
    };

    static final String[][] SYNTAX_ERRORS = {
        {"broken.schema", "line 3:0 mismatched input '<EOF>' expecting {'}', 'relation', 'permission'}"},
        {"brokenwildcard.schema", "line 4:34 missing '*' at '|'"},
        {"permission_invalid_expression.schema", "line 2:21 mismatched input '-' expecting {'(', 'nil', IDENTIFIER}"},
        {"permission_missing_expression.schema", "line 3:0 mismatched input '}' expecting {'(', 'nil', IDENTIFIER}"},
        {"relation_invalid_type.schema", "line 2:18 mismatched input '-' expecting {'nil', IDENTIFIER}"},
        {"relation_missing_type.schema", "line 3:0 mismatched input '}' expecting {'nil', IDENTIFIER}"},
    };

    @Test
    void parse() throws IOException {
        for (String[] t : SCHEMAS) {
            System.out.println(t[0]);

            InputStream schemaStream = getClass().getClassLoader().getResourceAsStream(t[0]);
            SchemaDef ast = SchemaFactory.parse(schemaStream);
            JsonNode actualJson = MAPPER.convertValue(ast, JsonNode.class);

//            System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(actualJson));


            try (SequenceWriter writer = MAPPER.writerWithDefaultPrettyPrinter().writeValues(new File(t[1]))) {
                writer.write(ast);
            }

            InputStream expectedStream = getClass().getClassLoader().getResourceAsStream(t[1]);
            JsonNode expectedJson = MAPPER.readValue(expectedStream, JsonNode.class);
//
//            System.out.println(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(actualJson));

//            assertEquals(expectedJson, actualJson);
        }
    }

    @Test
    void syntaxErrors() {
        for (String[] t : SYNTAX_ERRORS) {
            System.out.println(t[0]);

            InputStream schemaStream = getClass().getClassLoader().getResourceAsStream(t[0]);

            try {
                SchemaDef ast = SchemaFactory.parse(schemaStream);
                fail("Should never get here");
            } catch (ParseCancellationException e) {
                assertEquals(t[1], e.getMessage());
            }
        }
    }
}
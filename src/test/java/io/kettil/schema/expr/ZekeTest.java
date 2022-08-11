package io.kettil.schema.expr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

class ZekeTest {
    private static final ObjectMapper JSON = new ObjectMapper().findAndRegisterModules();
    private static final ObjectMapper YAML = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));

    @Test
    public void kjell() throws JsonProcessingException {
        TypeDefinition td = new TypeDefinition();
        td.getRelations().put("admin",
            RelationExpr.typeRef("otto", "bvarney")
        );

        td.getRelations().put("writer",
            RelationExpr.or(
                RelationExpr.typeRef("pref", "name"),
                RelationExpr.typeRef("pref", "name2")
            )
        );

        td.getRelations().put("reader",
            RelationExpr.or(
                RelationExpr.wildcard("pref", "name2"),
                RelationExpr.group("pref", "name2", "mem"),
                RelationExpr.nil()
            )
        );


        td.getPermissions().put("myperm",
            PermissionExpr.union(
                PermissionExpr.relationRef("a"),
                PermissionExpr.intersect(
                    PermissionExpr.nil(),
                    PermissionExpr.relationRef("a", "b", "c")
                ),
                PermissionExpr.nil()
            )
        );

        Map<String, Map<String, TypeDefinition>> prefixes = new TreeMap<>();
        prefixes.computeIfAbsent("tenant", i -> new TreeMap<>()).put("user", td);


        JsonNode json = JSON.convertValue(prefixes, JsonNode.class);
        System.out.println(YAML.writerWithDefaultPrettyPrinter().writeValueAsString(json));

    }
}
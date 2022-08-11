package io.kettil.schema.expr;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
public class TypeDefinition {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Map<String, RelationExpr> relations = new TreeMap<>();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Map<String, PermissionExpr> permissions = new TreeMap<>();
}

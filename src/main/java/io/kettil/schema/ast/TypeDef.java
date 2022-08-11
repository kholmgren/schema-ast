package io.kettil.schema.ast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"@type", "name", "relations", "permissions"})
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class TypeDef extends Ast {
    TypeRefExpr name;
    List<RelationDef> relationDefs;
    List<PermissionDef> permissionDefs;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitTypeDef(this);
    }
}

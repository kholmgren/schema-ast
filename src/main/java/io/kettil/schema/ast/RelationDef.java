package io.kettil.schema.ast;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"@type", "name", "expression"})
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RelationDef extends Ast {
    String name;
    Ast expression;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitRelationDef(this);
    }
}

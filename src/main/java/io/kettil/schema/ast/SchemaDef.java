package io.kettil.schema.ast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"@type", "definitions"})
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SchemaDef extends Ast {
    List<TypeDef> typeDefs;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSchemaDef(this);
    }
}

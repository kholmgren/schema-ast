package io.kettil.schema.ast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"@type", "refs"})
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RelationRefExpr extends Ast {
    List<String> refs;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitRelationRefExpr(this);
    }
}

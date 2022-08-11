package io.kettil.schema.ast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"@type"})
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class NilRefExpr extends Ast {
    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitNilRefExpr(this);
    }
}

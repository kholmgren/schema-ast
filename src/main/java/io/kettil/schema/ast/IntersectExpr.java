package io.kettil.schema.ast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"@type", "operands"})
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class IntersectExpr extends Ast {
    List<Ast> operands;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitIntersectExpr(this);
    }
}

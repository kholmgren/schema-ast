package io.kettil.schema.ast;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
@JsonPropertyOrder({"@type", "ref", "member"})
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class GroupRefExpr extends Ast {
    TypeRefExpr ref;
    String member;

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitGroupRefExpr(this);
    }
}

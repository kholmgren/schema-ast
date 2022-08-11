package io.kettil.schema.expr;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelationExprGroup implements RelationExpr {
    RelationExprTypeRef typeRef;
    String member;
}

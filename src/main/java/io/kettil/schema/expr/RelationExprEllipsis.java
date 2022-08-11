package io.kettil.schema.expr;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelationExprEllipsis implements RelationExpr {
    RelationExprTypeRef typeRef;
}

package io.kettil.schema.expr;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelationExprWildcard implements RelationExpr {
    RelationExprTypeRef typeRef;
}

package io.kettil.schema.expr;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RelationExprTypeRef implements RelationExpr {
    String prefix;
    String id;
}

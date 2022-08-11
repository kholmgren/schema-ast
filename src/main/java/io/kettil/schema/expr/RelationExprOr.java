package io.kettil.schema.expr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Data
//@AllArgsConstructor
public class RelationExprOr extends ArrayList<RelationExpr> implements RelationExpr {
}

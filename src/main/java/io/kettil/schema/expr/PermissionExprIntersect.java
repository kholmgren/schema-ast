package io.kettil.schema.expr;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PermissionExprIntersect extends ArrayList<PermissionExpr> implements PermissionExpr {
}

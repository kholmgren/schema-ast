package io.kettil.schema.expr;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PermissionExprExclude extends ArrayList<PermissionExpr> implements PermissionExpr {
}

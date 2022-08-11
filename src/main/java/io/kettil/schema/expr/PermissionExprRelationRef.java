package io.kettil.schema.expr;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PermissionExprRelationRef extends ArrayList<String> implements PermissionExpr {
}

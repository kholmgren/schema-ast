package io.kettil.schema.expr;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Collections;

//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
//@JsonSubTypes({
//    @JsonSubTypes.Type(value = PermissionExprIntersect.class, name = "intersect"),
//    @JsonSubTypes.Type(value = PermissionExprUnion.class, name = "union"),
//    @JsonSubTypes.Type(value = PermissionExprExclude.class, name = "exclude"),
//    @JsonSubTypes.Type(value = PermissionExprRelationRef.class, name = "relation"),
//    @JsonSubTypes.Type(value = PermissionExprNil.class, name = "nil")
//})
@JsonSerialize()
public interface PermissionExpr {
    static PermissionExprIntersect intersect(PermissionExpr... operands) {
        PermissionExprIntersect ret = new PermissionExprIntersect();
        Collections.addAll(ret, operands);
        return ret;
    }

    static PermissionExprUnion union(PermissionExpr... operands) {
        PermissionExprUnion ret = new PermissionExprUnion();
        Collections.addAll(ret, operands);
        return ret;
    }

    static PermissionExprExclude exclude(PermissionExpr... operands) {
        PermissionExprExclude ret = new PermissionExprExclude();
        Collections.addAll(ret, operands);
        return ret;
    }

    static PermissionExprRelationRef relationRef(String... value) {
        PermissionExprRelationRef ret = new PermissionExprRelationRef();
        Collections.addAll(ret, value);
        return ret;
    }

    static PermissionExprNil nil() {
        return new PermissionExprNil();
    }
}

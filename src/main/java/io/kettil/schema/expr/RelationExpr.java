package io.kettil.schema.expr;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Collections;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
    @JsonSubTypes.Type(value = RelationExprOr.class, name = "or"),
    @JsonSubTypes.Type(value = RelationExprTypeRef.class, name = "type"),
    @JsonSubTypes.Type(value = RelationExprWildcard.class, name = "wildcard"),
    @JsonSubTypes.Type(value = RelationExprEllipsis.class, name = "ellipsis"),
    @JsonSubTypes.Type(value = RelationExprGroup.class, name = "group"),
    @JsonSubTypes.Type(value = RelationExprNil.class, name = "nil"),
})
public interface RelationExpr {
    static RelationExprOr or(RelationExpr... operands) {
        RelationExprOr ret = new RelationExprOr();
        Collections.addAll(ret, operands);
        return ret;
    }

    static RelationExprTypeRef typeRef(String prefix, String id) {
        return new RelationExprTypeRef(prefix, id);
    }

    static RelationExprWildcard wildcard(String prefix, String id) {
        return wildcard(new RelationExprTypeRef(prefix, id));
    }

    static RelationExprWildcard wildcard(RelationExprTypeRef ref) {
        return new RelationExprWildcard(ref);
    }

    static RelationExprEllipsis ellipsis(String prefix, String id) {
        return new RelationExprEllipsis(new RelationExprTypeRef(prefix, id));
    }

    static RelationExprEllipsis ellipsis(RelationExprTypeRef ref) {
        return new RelationExprEllipsis(ref);
    }

    static RelationExprGroup group(String prefix, String id, String member) {
        return new RelationExprGroup(new RelationExprTypeRef(prefix, id), member);
    }

    static RelationExprGroup group(RelationExprTypeRef ref, String member) {
        return new RelationExprGroup(ref, member);
    }

    static RelationExprNil nil() {
        return new RelationExprNil();
    }
}

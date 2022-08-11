package io.kettil.schema.ast;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SchemaDef.class, name = "schemaDef"),
    @JsonSubTypes.Type(value = TypeDef.class, name = "typeDef"),
    @JsonSubTypes.Type(value = RelationDef.class, name = "relationDef"),
    @JsonSubTypes.Type(value = PermissionDef.class, name = "permissionDef"),

    @JsonSubTypes.Type(value = OrExpr.class, name = "orExpr"),
    @JsonSubTypes.Type(value = IntersectExpr.class, name = "intersectExpr"),
    @JsonSubTypes.Type(value = UnionExpr.class, name = "unionExpr"),
    @JsonSubTypes.Type(value = ExcludeExpr.class, name = "excludeExpr"),

    @JsonSubTypes.Type(value = RelationRefExpr.class, name = "relationRefExpr"),
    @JsonSubTypes.Type(value = WildcardRefExpr.class, name = "wildcardRefExpr"),
    @JsonSubTypes.Type(value = EllipsisRefExpr.class, name = "ellipsisRefExpr"),
    @JsonSubTypes.Type(value = GroupRefExpr.class, name = "groupRefExpr"),
    @JsonSubTypes.Type(value = TypeRefExpr.class, name = "typeRefExpr"),

    @JsonSubTypes.Type(value = NilRefExpr.class, name = "nilRefExpr"),
})
public abstract class Ast {
    public abstract <T> T accept(Visitor<T> visitor);

    public interface Visitor<T> {
        T visitSchemaDef(SchemaDef ast);

        T visitTypeDef(TypeDef ast);

        T visitRelationDef(RelationDef ast);

        T visitPermissionDef(PermissionDef ast);

        T visitOrExpr(OrExpr ast);

        T visitIntersectExpr(IntersectExpr ast);

        T visitUnionExpr(UnionExpr ast);

        T visitExcludeExpr(ExcludeExpr ast);

        T visitRelationRefExpr(RelationRefExpr ast);

        T visitWildcardRefExpr(WildcardRefExpr ast);

        T visitEllipsisRefExpr(EllipsisRefExpr ast);

        T visitGroupRefExpr(GroupRefExpr ast);

        T visitTypeRefExpr(TypeRefExpr ast);

        T visitNilRefExpr(NilRefExpr ast);
    }
}

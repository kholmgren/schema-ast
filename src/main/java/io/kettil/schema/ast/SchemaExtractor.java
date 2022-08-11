package io.kettil.schema.ast;

import io.kettil.schema.SchemaBaseListener;
import io.kettil.schema.SchemaParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static java.util.stream.Collectors.toList;

public class SchemaExtractor extends SchemaBaseListener {
    List<TypeDef> typeDefs = new ArrayList<>();
    List<RelationDef> relationDefs;
    List<PermissionDef> permissionDefs;
    Stack<Ast> astStack = new Stack<>();


    public SchemaDef getSchema() {
        return new SchemaDef(typeDefs);
    }

    @Override
    public void enterSchemaDef(SchemaParser.SchemaDefContext ctx) {
    }

    @Override
    public void exitSchemaDef(SchemaParser.SchemaDefContext ctx) {
        assert astStack.size() == 0;
    }

    @Override
    public void enterTypeDef(SchemaParser.TypeDefContext ctx) {
        assert relationDefs == null;
        relationDefs = new ArrayList<>();

        assert permissionDefs == null;
        permissionDefs = new ArrayList<>();
    }

    @Override
    public void exitTypeDef(SchemaParser.TypeDefContext ctx) {
        typeDefs.add(new TypeDef(
            (TypeRefExpr) astStack.pop(),
            relationDefs,
            permissionDefs));

        relationDefs = null;
        permissionDefs = null;
    }


    @Override
    public void enterRelationDef(SchemaParser.RelationDefContext ctx) {
//        assert astStack.size() == 1;
    }

    @Override
    public void exitRelationDef(SchemaParser.RelationDefContext ctx) {
        relationDefs.add(new RelationDef(ctx.IDENTIFIER().getText(), astStack.pop()));
    }

    @Override
    public void enterPermissionDef(SchemaParser.PermissionDefContext ctx) {
//        assert astStack.size() == 1;
    }

    @Override
    public void exitPermissionDef(SchemaParser.PermissionDefContext ctx) {
        permissionDefs.add(new PermissionDef(ctx.IDENTIFIER().getText(), astStack.pop()));
    }

    @Override
    public void enterRelationExprOr(SchemaParser.RelationExprOrContext ctx) {
    }

    @Override
    public void exitRelationExprOr(SchemaParser.RelationExprOrContext ctx) {
        Ast right = astStack.pop();
        Ast left = astStack.pop();

        astStack.push(new OrExpr(Arrays.asList(left, right)));
    }

    @Override
    public void enterRelationExprTypeRef(SchemaParser.RelationExprTypeRefContext ctx) {
    }

    @Override
    public void exitRelationExprTypeRef(SchemaParser.RelationExprTypeRefContext ctx) {
        //No-op
//        astStack.push(new Ref(ctx.typeName().getText()));
    }

    @Override
    public void enterRelationExprNil(SchemaParser.RelationExprNilContext ctx) {
    }

    @Override
    public void exitRelationExprNil(SchemaParser.RelationExprNilContext ctx) {
        astStack.push(new NilRefExpr());
    }

    @Override
    public void enterRelationExprTypeRefGroup(SchemaParser.RelationExprTypeRefGroupContext ctx) {
    }

    @Override
    public void exitRelationExprTypeRefGroup(SchemaParser.RelationExprTypeRefGroupContext ctx) {
        astStack.push(new GroupRefExpr(
            (TypeRefExpr) astStack.pop(),
            ctx.member.getText()));
    }

    @Override
    public void enterRelationExprTypeRefEllipsis(SchemaParser.RelationExprTypeRefEllipsisContext ctx) {
    }

    @Override
    public void exitRelationExprTypeRefEllipsis(SchemaParser.RelationExprTypeRefEllipsisContext ctx) {
        astStack.push(new EllipsisRefExpr((TypeRefExpr) astStack.pop()));
    }

    @Override
    public void enterRelationExprTypeRefWildcard(SchemaParser.RelationExprTypeRefWildcardContext ctx) {
    }

    @Override
    public void exitRelationExprTypeRefWildcard(SchemaParser.RelationExprTypeRefWildcardContext ctx) {
        astStack.push(new WildcardRefExpr((TypeRefExpr) astStack.pop()));
    }

    @Override
    public void enterPermissionExprNil(SchemaParser.PermissionExprNilContext ctx) {
    }

    @Override
    public void exitPermissionExprNil(SchemaParser.PermissionExprNilContext ctx) {
        astStack.push(new NilRefExpr());
    }

    @Override
    public void enterPermissionExprIntersect(SchemaParser.PermissionExprIntersectContext ctx) {
    }

    @Override
    public void exitPermissionExprIntersect(SchemaParser.PermissionExprIntersectContext ctx) {
        Ast right = astStack.pop();
        Ast left = astStack.pop();

        astStack.push(new IntersectExpr(Arrays.asList(left, right)));
    }

    @Override
    public void enterPermissionExprExclude(SchemaParser.PermissionExprExcludeContext ctx) {
    }

    @Override
    public void exitPermissionExprExclude(SchemaParser.PermissionExprExcludeContext ctx) {
        Ast right = astStack.pop();
        Ast left = astStack.pop();

        astStack.push(new ExcludeExpr(Arrays.asList(left, right)));
    }

    @Override
    public void enterPermissionExprParen(SchemaParser.PermissionExprParenContext ctx) {
    }

    @Override
    public void exitPermissionExprParen(SchemaParser.PermissionExprParenContext ctx) {
        //No-op
    }

    @Override
    public void enterPermissionExprUnion(SchemaParser.PermissionExprUnionContext ctx) {
    }

    @Override
    public void exitPermissionExprUnion(SchemaParser.PermissionExprUnionContext ctx) {
        Ast right = astStack.pop();
        Ast left = astStack.pop();

        astStack.push(new UnionExpr(Arrays.asList(left, right)));
    }

    @Override
    public void enterPermissionExprRelationRef(SchemaParser.PermissionExprRelationRefContext ctx) {
    }

    @Override
    public void exitPermissionExprRelationRef(SchemaParser.PermissionExprRelationRefContext ctx) {
        astStack.push(new RelationRefExpr(ctx.IDENTIFIER().stream()
            .map(ParseTree::getText)
            .collect(toList())));
    }

    @Override
    public void enterTypeName(SchemaParser.TypeNameContext ctx) {
    }

    @Override
    public void exitTypeName(SchemaParser.TypeNameContext ctx) {
        astStack.push(new TypeRefExpr(ctx.getText()));
    }
}

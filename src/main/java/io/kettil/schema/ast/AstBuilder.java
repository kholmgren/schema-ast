//package io.kettil.schema.ast;
//
//import io.kettil.schema.SchemaBaseVisitor;
//import io.kettil.schema.SchemaParser;
//import org.antlr.v4.runtime.tree.ParseTree;
//
//import static java.util.stream.Collectors.toList;
//
//public class AstBuilder extends SchemaBaseVisitor<Ast> {
//    @Override
//    public Ast visitSchemaDef(SchemaParser.SchemaDefContext ctx) {
//        return new Schema(ctx.typeDef().stream()
//            .map(this::visitTypeDef)
//            .map(Definition.class::cast)
//            .collect(toList()));
//    }
//
//    @Override
//    public Ast visitTypeDef(SchemaParser.TypeDefContext ctx) {
//        return new Definition(
//            (Ref) this.visitTypeName(ctx.typeName()),
//            ctx.relationDef().stream()
//                .map(this::visitRelationDef)
//                .map(Relation.class::cast)
//                .collect(toList()),
//            ctx.permissionDef().stream()
//                .map(this::visitPermissionDef)
//                .map(Permission.class::cast)
//                .collect(toList()));
//    }
//
//    @Override
//    public Ast visitRelationDef(SchemaParser.RelationDefContext ctx) {
//        return new Relation(
//            ctx.IDENTIFIER().getText(),
//            this.visit(ctx.relationExpr()));
//    }
//
//    @Override
//    public Ast visitPermissionDef(SchemaParser.PermissionDefContext ctx) {
//        return new Permission(
//            ctx.IDENTIFIER().getText(),
//            this.visit(ctx.permissionExpr()));
//    }
//
//    @Override
//    public Ast visitRelationExprOr(SchemaParser.RelationExprOrContext ctx) {
//        return new OrOp(ctx.relationExpr().stream()
//            .map(this::visit)
//            .collect(toList()));
//    }
//
//    @Override
//    public Ast visitWildcardRef(SchemaParser.WildcardRefContext ctx) {
//        return new WildcardRef((Ref) this.visitTypeName(ctx.typeName()));
//    }
//
//    @Override
//    public Ast visitEllipsisRef(SchemaParser.EllipsisRefContext ctx) {
//        return new EllipsisRef((Ref) this.visitTypeName(ctx.typeName()));
//    }
//
//    @Override
//    public Ast visitGroupRef(SchemaParser.GroupRefContext ctx) {
//        return new GroupRef(
//            (Ref) this.visitTypeName(ctx.typeName()),
//            ctx.member.getText());
//    }
//
//    @Override
//    public Ast visitDefinitionRef(SchemaParser.DefinitionRefContext ctx) {
//        return this.visit(ctx.typeName());
//    }
//
//    @Override
//    public Ast visitNilRel(SchemaParser.NilRelContext ctx) {
//        return new Nil();
//    }
//
//    @Override
//    public Ast visitPermissionParenExpr(SchemaParser.PermissionParenExprContext ctx) {
//        return this.visit(ctx.permissionExpr());
//    }
//
//    @Override
//    public Ast visitPermissionIntersectOp(SchemaParser.PermissionIntersectOpContext ctx) {
//        return new IntersectOp(ctx.permissionExpr().stream()
//            .map(this::visit)
//            .collect(toList()));
//    }
//
//    @Override
//    public Ast visitPermissionUnionOp(SchemaParser.PermissionUnionOpContext ctx) {
//        return new UnionOp(ctx.permissionExpr().stream()
//            .map(this::visit)
//            .collect(toList()));
//    }
//
//    @Override
//    public Ast visitPermissionExcludeOp(SchemaParser.PermissionExcludeOpContext ctx) {
//        return new ExcludeOp(ctx.permissionExpr().stream()
//            .map(this::visit)
//            .collect(toList()));
//    }
//
//    @Override
//    public Ast visitPermissionRef(SchemaParser.PermissionRefContext ctx) {
//        return new WalkExpression(ctx.IDENTIFIER().stream()
//            .map(ParseTree::getText)
//            .collect(toList()));
//    }
//
//    @Override
//    public Ast visitNilPerm(SchemaParser.NilPermContext ctx) {
//        return new Nil();
//    }
//
//    //        @Override
////        public Ast visitPermissionNilExpression(SchemaParser.PermissionNilExpressionContext ctx) {
////            return new Nil();
////        }
//
////        @Override
////        public Ast visitWalkOp(SchemaParser.WalkOpContext ctx) {
////            return new WalkOperation(ctx.IDENTIFIER().stream()
////                .map(ParseTree::getText)
////                .collect(toList()));
////        }
//
//
////    @Override
////    public Ast visitTypeRef(SchemaParser.TypeRefContext ctx) {
////        return new Ref(ctx.getText());
////    }
//
////        @Override
////        public Ast visitNil(SchemaParser.NilContext ctx) {
////            return new Nil();
////        }
//}

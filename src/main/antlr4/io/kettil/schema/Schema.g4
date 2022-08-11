grammar Schema;

//TODO: semicolons and synthetic semicolons after statements?

schemaDef
    : typeDef* EOF
    ;

typeDef
    : 'definition' typeName '{' (relationDef | permissionDef)* '}'
    ;

relationDef
    : 'relation' IDENTIFIER ':' relationExpr
    ;

permissionDef
    : 'permission' IDENTIFIER '=' permissionExpr
    ;

relationExpr
    : relationExpr ('|' relationExpr)+  #relationExprOr
    | typeName ':' '*'                  #relationExprTypeRefWildcard
    | typeName '#' '...'                #relationExprTypeRefEllipsis
    | typeName '#' member=IDENTIFIER    #relationExprTypeRefGroup
    | typeName                          #relationExprTypeRef
    | NIL                               #relationExprNil
    ;

permissionExpr
    : '(' permissionExpr ')'                #permissionExprParen
    | permissionExpr ('&' permissionExpr)+  #permissionExprIntersect
    | permissionExpr ('+' permissionExpr)+  #permissionExprUnion
    | permissionExpr ('-' permissionExpr)+  #permissionExprExclude
    | IDENTIFIER ('->' IDENTIFIER)*         #permissionExprRelationRef
    | NIL                                   #permissionExprNil
    ;

//walkOp
//    : IDENTIFIER ('->' IDENTIFIER)+
//    ;

//wildcardRef
//    : typeRef ':' '*'
//    ;

//ellipsisRef // TOOD: what does an ellipsis signify?
//    : typeRef '#' '...'
//    ;



typeName
    : (prefix=IDENTIFIER '/')? id=IDENTIFIER
    ;

NIL
    : 'nilRefExpr'
    ;

// [a-z][a-z0-9_]{1,62}[a-z0-9]
// TODO: validate length <= 64
IDENTIFIER
    : [a-z]([a-z0-9_]+[a-z0-9])?
    ;

SINGLE_LINE_COMMENT
   : '//' .*? (NEWLINE | EOF) -> skip
   ;

MULTI_LINE_COMMENT
   : '/*' .*? '*/' -> skip
   ;

fragment NEWLINE
   : [\r\n]
   ;

WS
   : [ \t\n\r\u00A0] + -> skip
   ;

%{ 
    #include <stdio.h>
    #include <stdlib.h>
    int result;
%}
%token NUM
%left '+' '-'
%left '*' '/'
%%
    expr: e { result = $1; } ;
    e: e'+'e { $$ = $1 + $3; }
    |e'-'e { $$ = $1 - $3; }
    |e'*'e { $$ = $1 * $3; }
    |e'/'e { $$ = $1 / $3; }
    |'('e')' { $$ = $2; }
    |NUM { $$ = $1; }
    ;
%%
main() {
    printf("Type the expressinon ");
    if(!yyparse()) {
        printf("Result: %d", result);
        printf("Valid expression");
    }
}
yyerror() {
    printf("Invalid expression");
    exit(0);
}
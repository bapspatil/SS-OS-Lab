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
    printf("Type the expression: ");
    if(!yyparse()) {
        printf("Result: %d\n", result);
        printf("\nValid expression\n\n");
    }
}
yyerror() {
    printf("\nInvalid expression\n\n");
    exit(0);
}
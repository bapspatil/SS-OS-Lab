%{
    #include <stdio.h>
    #include <stdlib.h>
    int n, count = 0;
%}
%token A B
%%
    str: S '\n' {   if(n == count)
                        printf("Valid string\n");
                    else
                        yyerror();
                    return 0; 
                }
        ;
    S: x B
        ;
    x: A x { count++; }
        | A { count++; }
        |
        ;
%%
void main() {
    printf("Enter n: ");
    scanf("%d", &n);
    printf("Enter string where there are 'n' a's: ");
    yylex();
    yyparse();
}
yyerror() {
    printf("Invalid string\n");
    exit(0);
}
    

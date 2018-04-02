%{
    #include <stdio.h>
    #include <stdlib.h>
    int n, count = 0;
%}
%token A B
%%
    str: S '\n' {   if(n == count)
                        printf("Valid");
                    else
                        yyerror(); 
                };
    S: xB 
        ;
    x: Ax { count++; }
    |A { count++; }
%%
void main() {
    printf("Enter n: ");
    scanf("%d", &n);
    printf("Enter string: ");
    yyparse();
}
yyerror() {
    printf("Invalid string");
}
    
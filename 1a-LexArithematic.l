%{
    #include<stdio.h>
    int bc = 0, op = 0, id = 0;
%}
%%
[a-zA-Z]+[0-9]* { printf("Invalid expression"); exit(1); }
[0-9]*\.[0-9]+ { printf("Invalid expression"); exit(1); }
"+"|"*" { op++; printf("\nOperator\n"); ECHO; }
[0-9]+ { id++; printf("\nInteger\n"); ECHO; }
"/"|"-" { printf("Invalid operator"); exit(0); }
"(" { bc++; }
")" { if(--bc < 0) {
            printf("Invalid expression"); 
            exit(0);
        }
    }
. { printf("Invalid expression"); }
\n ;
%%
main() {
    printf("Enter the expression");
    yylex();
    if(bc == 0 && op == id-1)
        printf("Valid");
    else 
        printf("Invalid");
}
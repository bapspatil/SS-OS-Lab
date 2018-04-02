%{
    #include <stdio.h>
    int count = 0;
%}
%%
"/*"[^*/]*"*/" { count++; }
"//".* { count++; }
%%
main(int argc, char **argv) {
    FILE *fp1, *fp2;
    fp1 = fopen(argv[1], "r");
    if(argc == 3) {
        if(fp1 == NULL) {
            printf("File not found");
            exit(1);
        }
        yyin = fp1;
        fp2 = fopen(argv[2], "w");
        if(fp2 == NULL) {
            printf("Error opening file");
            exit(1);
        }
        yyout = fp2;
        yylex();
    } else {
        printf("Enter a.out sourceFile destfile");
    }
}
%{
#include <stdio.h>
%}

%%

\".*\" 						;
#.+ 						;
"int"|"float"|"char"|"main"|"void"|"if"|"else"|"printf"|"case"|"break"|"double"|"scanf"|"switch"|"return"|"exit" {printf("%s is a keyword \n", yytext);}
"+"|"-"|"*"|"/"|"^"|"="|"<="|">"|">="|"<"|"=="|"!="|"!" {printf("%s is a operator\n", yytext);}
[a-zA-Z_][a-zA-Z_0-9]* 					{printf("%s is an identifier\n",yytext);}
.|\n 							;

%%
void main() {
FILE *fp;
char file[10];
printf("\nEnter the filename: ");
scanf("%s", file);
fp = fopen(file, "r");
yyin = fp;
yylex();
}
int yywrap() {
return 1;
}

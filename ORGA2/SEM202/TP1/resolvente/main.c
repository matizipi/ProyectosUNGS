#include <stdio.h>

extern void resolvente( float a, float b, float c );

void main()
{
    float a;
    float b;
    float c;

    printf("Resolvente\n");

    printf("Ingrese el valor para \"a\":\n");
    scanf("%f", &a);
    printf("Ingrese el valor para \"b\":\n");
    scanf("%f", &b);
    printf("Ingrese el valor para \"c\":\n");
    scanf("%f", &c);

    resolvente( a, b, c);
}

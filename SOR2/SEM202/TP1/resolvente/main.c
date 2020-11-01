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

    /*if( a == 0 ){
        printf( "Math Error: parametro a=0\n"  );
    } else if( b*b >= 4*a*c ) {*/
        resolvente( a, b, c);
/*    } else {
        printf("Las raices pertenecen al conjunto del los imaginario.\n");
    }*/
}

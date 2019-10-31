#include "Point.h"
#include <stdio.h>
#include <stdlib.h>

int main()
{
    Point a, b, c;
    Point e(1.2, 2.2);

    a.SetX(5.3);
    a.SetY(3.1);

    printf("%f %f \n", a.GetX(), a.GetY());

    Point d(a);
    printf("%f %f \n", d.GetX(), d.GetY());

    if(a==d)
        printf("a = d \n");
    
    if(b>c)
        printf("b <= c \n");
}
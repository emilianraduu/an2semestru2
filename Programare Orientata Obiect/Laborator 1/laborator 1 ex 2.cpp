#include <stdlib.h>
#include <stdio.h>
#include <cmath>

#define MATRIX_HEIGHT 6
#define MATRIX_WIDTH 5

void Circle(int* ptr,int cx,int cy,int ray) {

}

int main()
{
    FILE *fisier;
    char buffer[100];
    int v[1001],i=0,k=0;
    int Matrix[MATRIX_HEIGHT][MATRIX_WIDTH];

    fisier = fopen("input.txt", "r");
    if(fisier==NULL) perror("nu exista");

    while (!feof(fisier))
    { 
        fgets(buffer,sizeof(buffer),fisier);
        while(i<MATRIX_WIDTH)
            {
                v[i][k]=atoi(buffer);
                i++;
            }
        i=0;k++;
    } 
    bubbleSort(v,i);
    for(int k=0;k<i;k++)
        printf("%d\n",v[k]);
    fclose(fisier);
    return 0;
}
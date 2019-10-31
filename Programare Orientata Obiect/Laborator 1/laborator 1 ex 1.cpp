#include <stdlib.h>
#include <stdio.h>

void bubbleSort(int v[1001],int i)
{
    bool sort=true;
    int aux;
    while(sort)
    {
        sort=false;
        for(int k=0;k<i;k++)
        {
            if(v[k-1]>v[k])
            {
                aux=v[k-1];
                v[k-1]=v[k];
                v[k]=aux;
                sort=true;
            }
        }
        i=i-1;
    }   
}

int main()
{
    FILE *fisier;
    char buffer[100];
    int v[1001],i=0;

    fisier = fopen("input.txt", "r");
    if(fisier==NULL) perror("nu exista");

    while (!feof(fisier))
    { 
        fgets(buffer,sizeof(buffer),fisier);
        v[i]=atoi(buffer);
        i++;
    } 
    bubbleSort(v,i);
    for(int k=0;k<i;k++)
        printf("%d",v[k]);
    fclose(fisier);
    return 0;
}
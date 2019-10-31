#include "Multime.h"

Multime::Multime()
{
    Count = 0;
}
Multime::Multime(int *lista, int size)
{
    for(int i=0;i<size;i++){
        Numere[i] = *lista;
        
    }
}
int Multime::Min()
{
}
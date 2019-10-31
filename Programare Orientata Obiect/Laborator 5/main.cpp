#include <stdio.h>
#include "List.h"

int main()
{
    List<int> i;
    i.Add(5);
    i.Add(12);
    i.Add(10);
    i.Add(9);
     i.Add(12);
    i.Sort();
    i.Get();
}
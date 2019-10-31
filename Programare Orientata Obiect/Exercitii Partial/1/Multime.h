#include <stdio.h>
#include <string.h>
class Multime
{

public:
  int Numere[100];
  int Count;
  
  Multime();
  Multime(int *lista, int size);

  int Max();
  int Min();

  void Sort(int (*compare)(int, int));
  int GetSize()
  {
    return 1;
  }
};

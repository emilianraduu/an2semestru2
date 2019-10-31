#include <string>
#include <iostream>
using namespace std;
class Movie
{
  public:
    char name[100], movietime[100];
    Movie(char name[100], char movietime[100]){
        strcpy(this->name,name);
    }
    Movie(){};
};
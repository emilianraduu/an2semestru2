#include <cstring>
class Student
{
  public:
    int nota;
    char* nume;

    // Student()
    // {
    //     this->nota = (int)malloc(1 * sizeof(int));
    //     this->nume[100] = (char)malloc(100 * sizeof(char));
    // }
    Student(char* nume, int nota)
    {
        this->nota = nota;
        strcpy(this->nume, nume);
    }
    void operator{}(){
        
    }
    // int operator+(Studenti a, Studenti b){

    // }
};
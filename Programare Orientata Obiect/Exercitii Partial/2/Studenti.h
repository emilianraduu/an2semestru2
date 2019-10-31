#include <stdio.h>
#include <list>
#include <iostream> 
#include "Student.h"

using namespace std; 

class Studenti{
    public: 
    std::list<char*> stud;
    Studenti(Student a, Student b, Student c){
        stud.push_back(a.nume);
    }
    void getStuds(){
        std::cout<<stud.front();
    }
    int operator[](char nume[100]){
        return 1;
    }
};
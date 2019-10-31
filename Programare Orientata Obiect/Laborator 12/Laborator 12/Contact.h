
#include <iostream>

using namespace std;


class Contact {
protected:
    Contact();
    string name;
    string type;
public:
    void del(){
        name = "";
    }
    string getName(){
        return name;
    }
    void setName(string name){
        this->name = name;
    }
    
};

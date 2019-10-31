
#ifndef Friend_h
#define Friend_h
#include <string.h>
using namespace std;


#endif /* Friend_h */

class Friend: public Contact {
public:
    string birthDate;
    long phoneNumber;
    string address;
    Friend(string nm)
    {
        name = nm;
        type = "Friend";
    }
    Friend(){
        name = "";
        type = "Friend";
    }
};

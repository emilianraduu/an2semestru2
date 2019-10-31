#define MAX 100
#include <cstring>
#include <initializer_list>
class WatchList
{

  public:
    char name[MAX];
    int time;
    Movie movie1, movie2, movie3;

    WatchList(): movie1(), movie2(), movie3()
    {
        this->movie1 = movie1;
        this->movie2 = movie2;
        this->movie3 = movie3;
    }
    void Watch(char name[MAX], int time)
    {
        strcpy(this->name, name);
        this->time = time;
    }
    WatchList &operator=(const WatchList &b) // compound assignment (does not need to be a member,
    {                                        // but often is, to modify the private members)
        /* addition of rhs to *this takes place here */
        return *this; // return the result by reference
    }
};
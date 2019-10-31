
#include <iostream>
#include "Contact.h"
#include "Classmate.h"
#include "Friend.h"
#include "Acquaintance.h"
#include "Agenda.h"

int main(int argc, const char * argv[]) {
    Agenda A;
    Friend C;
    C.setName("Dragos");
    A.addContact(C);
    cout<<A.findByName("Dragos");
    A.findClass();
    return 0;
}

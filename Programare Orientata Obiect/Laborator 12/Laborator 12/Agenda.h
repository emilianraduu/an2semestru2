
#include <iostream>
#include <vector>
#define MAX 100
using namespace std;
class Agenda {
public:
    vector<Contact> contactList;
    int nrOfContacts;
    Contact* findByName(string name){
        for(int iterator=0;iterator<=nrOfContacts;iterator++){
            if(contactList[iterator].getName()==name) return &contactList[iterator];
        }
        return nullptr;
    }
    Agenda(){
        nrOfContacts = 0;
        contactList = new vector<Contact>(MAX);
    }
    void addContact(Contact dummy){
        contactList[nrOfContacts] = dummy;
        nrOfContacts++;
    }
//    Friend* friendList(){
//        Friend listOfFriends[MAX];
//        int nrOfFriends = 0;
//        for(int iterator=0;iterator<=nrOfContacts;iterator++)
//        {
//            listOfFriends[nrOfFriends] = contactList[iterator];
//            nrOfFriends++;
//        }
//        return listOfFriends;
//    }
    void findClass(){
        for(int iterator=0;iterator<=nrOfContacts;iterator++){
            cout<<is_class<Contact>::value << '\n';
        }
    }
    void deleteContact(string name){
        for(int iterator=0;iterator<=nrOfContacts;iterator++){
            if(contactList[iterator].getName()==name)
            {
//                delete contactList[iterator];
                contactList[iterator]=contactList[iterator+1];
                contactList[iterator].del();
                nrOfContacts--;
            }
        }
    }
    Contact* findContact(string name){
        for(int iterator=0;iterator<=nrOfContacts;iterator++){
            if(contactList[iterator].getName()==name)
            {
                cout<<contactList[iterator].getName()<<" \n";
                return &contactList[iterator];
            }
        }
        return nullptr;
    }
    
    
};

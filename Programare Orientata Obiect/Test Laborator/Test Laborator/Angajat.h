//
//  Angajat.h
//  Test Laborator
//
//  Created by Emilian Radu on 05/06/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Angajat_h
#define Angajat_h

#include <string>
using namespace std;


class Angajat {
protected:
    int salariu;
    string nume;
    int id;
    
public:
    virtual string getNume();
    virtual int getSalariu();
    virtual bool setSalariu(int newSalariu);
    virtual void Lucreaza()=0;
    Angajat(string nume, int salariu, int id);
    
};


#endif /* Angajat_h */

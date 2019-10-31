//
//  Manager.h
//  Test Laborator
//
//  Created by Emilian Radu on 05/06/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Manager_h
#define Manager_h

#include "Angajat.h"
#include <iostream>
#include <list>
using namespace std;

class Manager : public Angajat {
private:
    list<Angajat*> echipa;
    string numeEchipa;
public:
    void Lucreaza();
    bool Angajeaza(Angajat* x);
    bool Concediaza(Angajat* x);
    Angajat* GetAngajat(int id);
    Manager(string numeEchipa, string nume, int salariu, int id);
};

#endif /* Manager_h */


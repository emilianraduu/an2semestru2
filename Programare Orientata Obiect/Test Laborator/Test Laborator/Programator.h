//
//  Programator.h
//  Test Laborator
//
//  Created by Emilian Radu on 05/06/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Programator_h
#define Programator_h
#include "Angajat.h"
#include <string>
using namespace std;

class Programator : public Angajat {
private:
    string cod;
public:
    void Lucreaza();
    void Add(string cod);
    Programator(string nume, int salariu, int id);
    string GetCode();
};


#endif /* Programator_h */

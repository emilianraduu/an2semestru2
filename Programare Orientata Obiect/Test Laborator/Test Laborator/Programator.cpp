//
//  Programator.cpp
//  Test Laborator
//
//  Created by Emilian Radu on 05/06/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#include <stdio.h>
#include "Programator.h"

using namespace std;

void Programator::Lucreaza(){
    
}
void Programator::Add(string cod){
    this->cod = this->cod + cod;
}
Programator::Programator(string nume, int salariu, int id){
    this->nume = nume;
    this->salariu = salariu;
    this->id = id;
}
string Programator::GetCode(){
    return this->cod;
}

//
//  Manager.cpp
//  Test Laborator
//
//  Created by Emilian Radu on 05/06/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#include <stdio.h>
#include "Manager.h"


void Manager::Lucreaza(){
    
}
bool Manager::Angajeaza(Angajat* x){
    if(this->echipa.add(x))
        return true;
    else return false;
}
bool Manager::Concediaza(Angajat* x){
    if(this->echipa.remove(x))
        return true;
    else return false;
}
Angajat* Manager::GetAngajat(int id){
    for(int i=this->echipa.begin(); i<this->echipa.end();i++){
        Angajat temp = this->echipa.index(i);
        if(temp.id == id)
            return temp;
    }
    return NULL;
}
Manager::Manager(string numeEchipa, string nume, int salariu, int id){
    this->numeEchipa = numeEchipa;
    this->nume = nume;
    this-> salariu = salariu;
    this->id = id;
};

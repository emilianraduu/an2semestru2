//
//  Grapefruit.h
//  Laborator 13
//
//  Created by Emilian Radu on 20/05/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Grapefruit_h
#define Grapefruit_h

#include <iostream>
#include "Visitor.h"
using namespace std;

class Grapefruit : public Fruit{
public:
    Grapefruit(){
    }
    void accept(Visitor &v);
    string thiss()
    {
        return "Grapefruit";
    }
};
/*virtual*/void Grapefruit::accept(Visitor &v)
{
    v.visit(this);
}

#endif /* Grapefruit_h */

//
//  Lemon.h
//  Laborator 13
//
//  Created by Emilian Radu on 20/05/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Lemon_h
#define Lemon_h
#include <iostream>
#include "Visitor.h"
using namespace std;



class Lemon : public Fruit {
public:
    Lemon(){}
    void accept(Visitor &v);
    string thiss()
    {
        return "Lemon";
    }
    
};

/*virtual*/void Lemon::accept(Visitor &v)
{
    v.visit(this);
}
#endif /* Lemon_h */

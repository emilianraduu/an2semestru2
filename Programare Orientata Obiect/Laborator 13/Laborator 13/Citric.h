//
//  Citric.h
//  Laborator 13
//
//  Created by Emilian Radu on 20/05/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Citric_h
#define Citric_h

#include "Fruit.h"
#include "Lemon.h"
#include "Grapefruit.h"
#include "Orange.h"
#include <iostream>
using namespace std;



class Citric: public Visitor
{
public:
//    int
    Citric(){}
    /*virtual*/void visit(Lemon *e)
    {
        cout << "citric " + e->thiss() << '\n';
    }
    /*virtual*/void visit(Grapefruit *e)
    {
        cout << "citric " + e->thiss() << '\n';
    }
    /*virtual*/void visit(Orange *e)
    {
        cout << "citric " + e->thiss() << '\n';
    }
    /*virtual*/void visit(Apple *e)
    {
//        cout << "do Up on " + e->thiss() << '\n';
    }
};
#endif /* Citric_h */

//
//  Orange.h
//  Laborator 13
//
//  Created by Emilian Radu on 20/05/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Orange_h
#define Orange_h
#include <iostream>
#include "Fruit.h"
#include "Visitor.h"
using namespace std;



class Orange : public Fruit{
public:
    Orange(){}
    void accept(Visitor &v);
    string thiss()
    {
        return "Orange";
    }
};

/*virtual*/void Orange::accept(Visitor &v)
{
    v.visit(this);
}
#endif /* Orange_h */

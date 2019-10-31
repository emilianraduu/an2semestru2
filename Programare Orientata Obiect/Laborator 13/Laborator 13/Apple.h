//
//  Apple.h
//  Laborator 13
//
//  Created by Emilian Radu on 20/05/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Apple_h
#define Apple_h
#include <iostream>
using namespace std;
#include "Visitor.h"
#include "Fruit.h"


class Apple : public Fruit {
public:
    Apple(){
        this->size = 1;
        this->dimensions = 2;
    }
    void accept(Visitor &v);
    string thiss()
    {
        return "Apple";
    }
};
/*virtual*/void Apple::accept(Visitor &v)
{
    v.visit(this);
}
#endif /* Apple_h */

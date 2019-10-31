//
//  Fruit.h
//  Laborator 13
//
//  Created by Emilian Radu on 20/05/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Fruit_h
#define Fruit_h
#include <iostream>
#include "Visitor.h"
using namespace std;
class Fruit {
public:
    int size, dimensions;
    virtual void accept(Visitor &v)=0;
};
#endif /* Fruit_h */


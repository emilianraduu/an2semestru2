//
//  Visitor.h
//  Laborator 13
//
//  Created by Emilian Radu on 20/05/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Visitor_h
#define Visitor_h
#include <iostream>
using namespace std;

class Apple;
class Grapefruit;
class Lemon;
class Orange;

class Visitor
{
public:
    virtual void visit(Apple *e) = 0;
    virtual void visit(Orange *e) = 0;
    virtual void visit(Grapefruit *e) = 0;
    virtual void visit(Lemon *e) = 0;
};







#endif /* Visitor_h */

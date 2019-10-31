//
//  main.cpp
//  Laborator 13
//
//  Created by Emilian Radu on 20/05/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#include <iostream>
#include "Apple.h"
#include "Fruit.h"
#include "Orange.h"
#include "Citric.h"
#include "NonCitric.h"

using namespace std;

int main(int argc, const char * argv[]) {
    
    Fruit *list[] =
    {
        new Apple(), new Orange(), new Grapefruit(), new Lemon()
    };
    Citric c;
    NonCitric n;
    for (int i = 0; i < 4; i++)
        list[i]->accept(c);
    for (int i = 0; i < 4; i++)
        list[i]->accept(n);
    return 0;
}

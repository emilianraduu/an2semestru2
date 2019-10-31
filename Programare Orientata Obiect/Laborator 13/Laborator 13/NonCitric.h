//
//  NonCitric.h
//  Laborator 13
//
//  Created by Emilian Radu on 20/05/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef NonCitric_h
#define NonCitric_h

#include <iostream>
using namespace std;


class NonCitric: public Visitor
{
public:
    NonCitric(){}
    /*virtual*/void visit(Apple *e)
    {
        cout << "non citric " + e->thiss() << '\n';
    }
    /*virtual*/void visit(Lemon *e)
    {
//        cout << "do Up on " + e->thiss() << '\n';
    }
    /*virtual*/void visit(Grapefruit *e)
    {
//        cout << "do Up on " + e->thiss() << '\n';
    }
    /*virtual*/void visit(Orange *e)
    {
//        cout << "do Up on " + e->thiss() << '\n';
    }
};
#endif /* NonCitric_h */

//
//  main.cpp
//  Laborator 10
//
//  Created by Emilian Radu on 22/04/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#include <iostream>
#include "Array.h"

using namespace std;

int main(int argc, const char * argv[]) {
    Array<string> a(10);
    a+="andi";
    a+="dragos";
    a+="balte";
    a+="corina";
    Array<char*> b(10);
    a.Sort();
    for(int i=0;i<a.GetSize();i++)
        cout<<a[i]<<" ";
//    cout<<a.Find(4);
    return 0;
}

//
//  ArrayIterator.h
//  Laborator 10
//
//  Created by Emilian Radu on 22/04/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef ArrayIterator_h
#define ArrayIterator_h


#endif /* ArrayIterator_h */
template<class T>

class ArrayIterator

{
    
private:
    
    int Current; // mai adaugati si alte date si functii necesare pentru iterator
    
public:
    
    ArrayIterator(){
        Current = 0;
    }
    
    ArrayIterator& operator ++ (){
        Current++;
    }
    
    ArrayIterator& operator -- (){
        Current--;
    }
    
    bool operator= (ArrayIterator<T> &){
//        if(Current=.Current)
            return true;
    }
    
    bool operator!=(ArrayIterator<T> &){
        
    }
    
    T* GetElement();
//        return
//    }
    
};


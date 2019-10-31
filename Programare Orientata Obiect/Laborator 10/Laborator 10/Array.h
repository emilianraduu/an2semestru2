//
//  Array.h
//  Laborator 10
//
//  Created by Emilian Radu on 22/04/2019.
//  Copyright © 2019 Emilian Radu. All rights reserved.
//

#ifndef Array_h
#define Array_h
#include "Compare.h"
#include "ArrayIterator.h"

#endif /* Array_h */
template<class T>

class Array

{
    
private:
    
    T** List; // lista cu pointeri la obiecte de tipul T*
    
    int Capacity; // dimensiunea listei de pointeri
    
    int Size; // cate elemente sunt in lista
    
public:
    
    Array(){
        Capacity = 0;
        Size = 0;
    } // Lista nu e alocata, Capacity si Size = 0
    
    ~Array(){
        delete List;
        Capacity=0;
        Size =0;
    } // destructor
    
    Array(int capacity){
        Capacity = capacity;
        List = new T*[Capacity];
    } // Lista e alocata cu 'capacity' elemente
    
    Array(const Array<T> &otherArray){
        printf("HELLO");
    } // constructor de copiere
    
    
    
    T& operator[] (int index){
        try{
            if(index>Capacity)
                throw(-1);
        }
        catch(int e){
            if(e==-1){
                std::cout<<"Eroare \n";
                index=0;
            }
            
            else
                return *List[index];
        }
        return *List[index];
        
    } // arunca exceptie daca index este out of range
    
    
    
    const Array<T>& operator+=(const T &newElem){
        List[Size] = new T;
        *List[Size] = newElem;
        Size++;
        List[Size] = new T;
        return *this;
    } // adauga un element de tipul T la sfarsitul listei si returneaza this
    
    const Array<T>& Insert(int index, const T &newElem){
        *List[index] = newElem;
        return *this;
    }// adauga un element pe pozitia index, retureaza this. Daca index e invalid arunca o exceptie
    
    const Array<T>& Insert(int index, const Array<T> otherArray){
        return this[index];
    } // adauga o lista pe pozitia index, retureaza this. Daca index e invalid arunca o exceptie
    
    const Array<T>& Delete(int index){
        delete *List[index];
        return *this;
    } // sterge un element de pe pozitia index, returneaza this. Daca index e invalid arunca o exceptie
    
    
    
    bool operator=(const Array<T> &otherArray){
        return true;
    }
    
    
    
    void Sort(){
        for(int i=0; i<GetSize()-1;i++)
        {
            for(int j=i+1; j<GetSize();j++)
            {
                if(*List[i]>*List[j]){
                    T* aux;
                    aux=List[i];
                    List[i]=List[j];
                    List[j]=aux;
                }
            }
        }
    } // sorteaza folosind comparatia intre elementele din T
    
    void Sort(int(*compare)(const T&, const T&)){
        
    } // sorteaza folosind o functie de comparatie
    
    void Sort(Compare *comparator){
        
    } // sorteaza folosind un obiect de comparatie
    
    
    
    // functii de cautare - returneaza pozitia elementului sau -1 daca nu exista
    
    int BinarySearch(const T& elem){
        return 1;
    } // cauta un element folosind binary search in Array
    
    int BinarySearch(const T& elem, int(*compare)(const T&, const T&)){
        return 1;
    }//  cauta un element folosind binary search si o functie de comparatie
    
    int BinarySearch(const T& elem, Compare *comparator){
        return 1;
    }//  cauta un element folosind binary search si un comparator
    
    
    
    int Find(const T& elem);
//        for(int i=0;i<GetSize();i++)
//            if(*List[i]==elem)
//                return 1;
//            else return 0;
//    } // cauta un element in Array
    
    int Find(const T& elem, int(*compare)(const T&, const T&));//  cauta un element folosind o functie de comparatie
    
    int Find(const T& elem, Compare *comparator);//  cauta un element folosind un comparator
    
    
    
    int GetSize(){
        return Size;
    }
    
    int GetCapacity(){
        return Capacity;
    }
    
    
    
    ArrayIterator<T> GetBeginIterator(){
        
    }
    
    ArrayIterator<T> GetEndIterator(){
        
    }
    
};


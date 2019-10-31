#include <iostream>
using namespace std;
template <typename T>

class List

{

    T *Elemente;

    int Count, Allocated;

  public:
    List()
    {
        this->Elemente = new T[100];
        this->Count = 0;
        this->Allocated = 100;
    }

    ~List()
    {
        delete[] Elemente;
    }

    bool Add(const T &value)
    {
        if (Count == Allocated)
        {
            T *Elemente2 = new T[Allocated * 2];
            if (Elemente2 == nullptr)
                return false;
            for (int i = 0; i < Count; i++)
                Elemente2[i] = Elemente[i];
            delete[] Elemente;
        }
        this->Elemente[Count] = value;
        ++Count;
        return true;
    }

    void Get()
    {
        int i = Count;
        while (i)
        {
            printf("%d ", this->Elemente[i]);
            i--;
        }
    }
    T &operator[](int index)
    {
        if (index >= 0 && index < Count)
            return Elemente[index];
    }

    void Sort()
    {
        int i, j;
        for (i = 0; i < Count - 1; i++)
            for (j = 0; j < Count - i - 1; j++)
                if (Elemente[j] > Elemente[j + 1])
                    {
                        T aux = Elemente[j];
                        Elemente[j] = Elemente[j+1];
                        Elemente[j+1] = aux;
 
                    }
    }
};

#include <iostream>
#include <vector>
#include <stdio.h>
#include <functional>

using namespace std;

template <class T>
void Sort(std::vector<T> lista, int (*Compare)(T &e1, T &e2))
{
    for (auto i = lista.begin(); i != lista.end(); ++i)
    {
        for (auto j = lista.begin(); j != lista.end(); ++j)
        {
            if (Compare(*i, *j) == 0)
            {
                swap(*i, *j);
            }
        }
    }
}

template <class T>
void Sort2(std::vector<T> lista, std::function<int(T &e1, T &e2)> fct)
{
    for (auto i = lista.begin(); i != lista.end(); ++i)
    {
        for (auto j = lista.begin(); j != lista.end(); ++j)
        {
            if (fct(*i, *j) == 0)
            {
                swap(*i, *j);
            }
        }
    }
}

int CmpInt(int &a, int &b)
{
    if (a < b)
        return 1;
    else
        return 0;
}

int main()
{
    auto x = 4.3;
    // vector<int> lista = {1 ,2 ,3 ,4};
    // std::function<int(int &e1, int &e2)> fct = [](int &a, int &b) ->int {
    //     if (a < b)
    //         return 1;
    //     else
    //         return 0;
    // };

    // Sort2<int>(lista, fct);
    return 0;
}

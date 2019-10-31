#include <fstream>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>

using namespace std;

int main()
{
    int comma;
    string buffer, fullNumbers;
    vector<int> v(100);

    ifstream file("input.txt");

    while (getline(file, buffer))
    {
        fullNumbers += buffer;
    }
    comma = fullNumbers.find(",", comma);
    while (comma != -1)
    {
        fullNumbers.erase(comma, 1);
        fullNumbers.insert(comma, " ");
        comma = fullNumbers.find(",", comma - 1);
    }
    int i=0;
    while (fullNumbers.find(" ")!= -1)
    {
        stringstream geek(fullNumbers);
        geek >> v[i];
        cout << v[i];
        i++;
        
    }

    return 0;
}
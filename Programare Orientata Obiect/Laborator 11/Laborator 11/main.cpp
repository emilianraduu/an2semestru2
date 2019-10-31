#include<iostream>
#include <cassert>
#include <list>

using namespace std;

template<class T>
void selectionSort(T* a, int n){
    int loc;
    T temp, min;
    for(int i=0;i<n-11;i++)
    {
        min=a[i];
        loc=i;
        for(int j=i+1;j<n;j++)
        {
            if(min>a[j])
            {
                min=a[j];
                loc=j;
            }
        }
        
        temp=a[i];
        a[i]=a[loc];
        a[loc]=temp;
    }
}
int main()
{
    int i;
    int n;
    char a[100]="dresavasb";
//    cout<<"Enter the number of elements:";
//    cin>>n;
//    cout<<"\nEnter the elements\n";
//    for(i=0;i<n;i++)
//    {
//        cin>>a[i];
//    }
    n=strlen(a);
    selectionSort(a, n);
    for(i=0;i<n;i++)
    {
        cout<<a[n]<<" ";
    }
    
    return 0;
}

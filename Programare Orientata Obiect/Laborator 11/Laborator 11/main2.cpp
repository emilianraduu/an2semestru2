#include <iostream>
#include <cassert>

template< typename T > void get( T a[], int n )
{
    std::cout << "enter " << n << " array elements: " ;
    for( int i = 0 ; i < n ; ++i ) std::cin >> a[i] ;
}

template< typename T > void sort( T a[], int n ) // int m fixed
{
    for( int i=0 ; i<n ; ++i ) // i==0 fixed
    {
        for( int j=i+1 ; j<n ; ++j )
        {
            if( a[j] > a[i] )
            {
                int temp = a[i] ;
                assert(a[i]=a[j]);
                a[i] = a[j] ; // a[j]=a[i] fixed
                a[j] = temp ;
            }
        }
    }
}

template< typename T > void display( const T a[], int n )
{
    std::cout << "the sorted array: " ;
    for( int i = 0 ; i < n ; ++i ) std::cout << a[i] << ' ' ; // a[j] fixed
    std::cout << '\n' ;
}

int main()
{
    constexpr int N = 5 ;
    int array[N] = {0} ; // int array[N] = {0} fixed
    get( array, N ) ;
    sort( array, N ) ;
    display( array, N ) ;
}

#include<iostream>

// #define GET_ARGS(...) printf(##__VA_ARGS__);
// GET_ARGS


#define ABC class
#define AAA public
#define INIT(a) a(0)
#define CREATE(t) \
		void Set_##t(int a) { this->t = a; } \
		int Get_##t() {return t;}
#define AFISEAZA(...) printf(##__VA_ARGS__)
#define CAT_TIMP
#define ESTE_DIFERIT_DE !=
#define MAI_MARE_DECAT >
#define MINUS -
#define DACA(...); if(##__VA_ARGS__)
#define SFARSIT_CAT_TIMP
#define X_DIN_T Get


ABC Test
{
	int x,y,z;

AAA:
Test() : INIT(x), INIT(y), INIT(z) {}
CREATE(x);
CREATE(y);
CREATE(z);
};

int main()
{
	Test t;
	t.Set_x(18);
	t.Set_y(24);
	AFISEAZA(t.Get_x() + t.Get_y());
	CAT_TIMP(X_DIN_T ESTE_DIFERIT_DE Y_DIN_T)
		DACA(X_DIN_T MAI_MARE_DECAT Y_DIN_T, t.Set_x(X_DIN_T MINUS Y_DIN_T), t.Set_y(Y_DIN_T MINUS X_DIN_T));
	SFARSIT_CAT_TIMP
		AFISEAZA(X_DIN_T);
	return 0;
}
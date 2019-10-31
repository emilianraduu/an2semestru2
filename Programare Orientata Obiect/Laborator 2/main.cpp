#include "MyString.h"

int main()
{
    MyString a,b,c,d;
    MyString h("bonjour");
    b.Add("buna");
    a.Add("salut");
    a.Add(b);
    d.Set(b);
    c.Set("ola");
    printf("%d ", c.Compare("o"));
    h.Delete(0,1);
    printf("%s \n", d.GetText());
    printf("%d \n", d.GetSize());
    printf("%s \n", h.GetText());
    printf("%d \n", h.GetSize());
    printf("%s \n", a.GetText());
    printf("%d \n", a.GetSize());
    printf("%s \n", b.GetText());
    printf("%d \n", b.GetSize());
    printf("%s \n", c.GetText());
    printf("%d \n", c.GetSize());
    return 0;
}
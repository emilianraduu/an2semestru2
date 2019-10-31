#include <stdlib.h>
#include <stdio.h>
#include <cstring>
class MyString
{
    char *sir;
    unsigned int AllocatedSize;
    unsigned int Size;

  public:
    MyString()
    {
        this->sir = new char[16];
        this->AllocatedSize = sizeof(sir);
        this->Size = strlen(this->sir);
    } // aloca 16 octeti pentru sir-ul
    MyString(const char *text)
    {
        int length = strlen(text);
        this->sir = new char[length + 1];
        strcpy(this->sir,text);
        this->AllocatedSize = sizeof(sir);
        this->Size = length;
    } // aloca spatiu suficient pentru a copia si continutul lui text in sir
    ~MyString()
    {
        delete[] this->sir;
        this->AllocatedSize=0;
        this->Size=0;
    } // dealoca stringul

    unsigned int GetSize()
    {
        if(this->AllocatedSize != 0)
            return this->AllocatedSize;
        else
            return -1;
        
    }

    void Set(const char *text)
    {
        if(text != NULL)
            strcpy(sir, text);
        else printf("[ SET ] Nu am ce sa copiez.");
    } // copie in sir pe text. Daca e nevoie, redimensioneaza pe sir
    void Set(MyString &m)
    {
        if(m.sir != NULL)
            strcpy(sir, m.sir);
        else printf("[ SET ] Nu am ce sa copiez.");
    } // similar ca la functia Set de mai sus

    void Add(const char *text)
    {
        if(this->AllocatedSize<strlen(text))
            printf("[ Eroare ] Spatiu insuficient.");
        strcat(this->sir, text);
    }                      // adauga la sfarsitul sirului curent pe text. Daca e nevoie redimensioneaza pe sir
    void Add(MyString &m)
    {
        if(m.sir != NULL)
            strcat(this->sir, m.sir);
        else printf("[ ADD ] Eroare la copiere.");
    } // similar ca Add de mai sus

    const char *GetText()
    {
        if(this->sir != NULL)
            return this->sir;
        else return "[ EMPTY ]";
    } // returneaza textul

    // returneaza un obiect nou MyString in care copie din obiectul curent sub-sirul care incepe de la pozitia start si are lunginea size.
    // daca pozitia start e in afara sirului meu, sau dimensiunea ceruta (size) e prea mare, functia returneaza NULL
    MyString *SubString(unsigned int start, unsigned int size);
        /*if (start > this->AllocatedSize)
        {
            printf("[ START ] Pozitia de start depaseste spatiul alocat.");
            return NULL;
        }
        if (size > this->AllocatedSize)
        {
            printf("[ SIZE ] Dimensiunea ceruta depaseste spatiul alocat.");
            return NULL;
        }
        //strcpy(*SubString->sir, this->sir);
        //return *SubString;*/

    // sterge caracterele din sir de la pozitia start, de dimensiunea size. Returneaza true daca parametri sunt corecti.
    // daca pozitia start ein afara sirului meu, sau dimensiunea ceruta (size) e prea mare, functia returneaza false
    bool Delete(unsigned int start, unsigned int size)
    {
        if (start > this->AllocatedSize)
        {
            printf("[ START ] Pozitia de start depaseste lungimea textului.");
            return false;
        }
        if (size > this->AllocatedSize)
        {
            printf("[ SIZE ] Dimensiunea ceruta depaseste spatiul alocat.");
            return false;
        }
        while (start)
        {
            this->sir++;
            start--;
        }
        while (size)
        {
            this->sir=this->sir++;
            this->sir++;
            this->AllocatedSize--;
            size--;
        }

        return true;
    }

    int Compare(const char *text)
    {
        if(text==NULL)
            return -2;
        if(strlen(this->sir)>strlen(text))
            return -1;
        if(strlen(this->sir)==strlen(text))
            return 0;
        if(strlen(this->sir)<strlen(text))
            return 1;
    } // compara sirul curent cu un alt text. Returneaza -1 daca sirul e mai mic decat text, 0 daca sunt egale si 1 altfel
    int Compare(MyString &m);      // similar cu functia Compare de mai sus

    char GetChar(unsigned int index); // returneaza caracterrul de pe pozitia index. Daca pozitia e in afara sirului returneaza 0

    // insereaza textul text la pozitia index in sir. Daca index e in afara index-ului returneaza false;
    // face si redimensionare daca e cazul
    bool Insert(unsigned int index, const char *text);

    bool Insert(unsigned int index, MyString &m); // similar cu functia Insert de mai sus.

    int Find(const char *text); // returneaza prima pozitie pe care a gasit sub-sirul text in 'sir'. Daca nu il gaseste returneaza -1

    int FindLast(const char *text); // returneaza ultima pozitie pe care a gasit sub-sirul text in 'sir'. Daca nu il gaseste returneaza -1
};
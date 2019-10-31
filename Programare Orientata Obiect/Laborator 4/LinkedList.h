#include "Container.h"
#include "Node.h"
#include <cstdlib>
#include <stdio.h>
#include <stdlib.h>
class LinkedList : public Container
{
  private:
    int length;
    Node *head;

  public:
    LinkedList()
    {
        this->length = 0;
        this->head = NULL;
    }
    ~LinkedList()
    {
        Node* copyHead;
        while(head!=NULL){
            copyHead=head->next;
            delete head;
            head = copyHead;
        }
        this->length=0;
    }

    void getList()
    {
        Node *head = this->head;
        int i = 1;
        while (head)
        {
            printf("%d ", head->data);
            head = head->next;
            i++;
        }
    }
    bool Add(int value)
    {
        if(value){
        Node *node = new Node();
        node->data = value;
        node->next = this->head;
        this->head = node;
        this->length++;
        return true;
        }
        else return false;
    }
    bool Del(int value){
        return true;
    }
    int Count(int value){
        return 0;
    }
    bool Exists(int value){
        return true;
    }
    int *GetSortedArray(){
        return 0;
    }
    int GetCount(){
        return 0;
    }
};
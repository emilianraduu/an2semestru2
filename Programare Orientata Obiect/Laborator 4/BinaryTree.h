
class BinaryTree
{
  private:
    /* data */
  public:
    BinaryTree(){

    }
    ~BinaryTree(){

    }
    bool Add(int value);
    bool Del(int value);
    int Count(int value);
    bool Exists(int value);
    int* GetSortedArray();
    int GetCount();
};

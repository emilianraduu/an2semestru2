#include <cmath>
class Point
{
  public:
    Point()
    {
        this->x = 0;
        this->y = 0;
    }
    Point(double x, double y)
    {
        // check if null
        this->x = x;
        this->y = y;
    }

    Point(const Point &number)
    {
        this->x = number.x;
        this->y = number.y;
    }
    Point &operator=(const Point &number)
    {
        this->x = number.x;
        this->y = number.y;
        return *this;
    }

    void SetX(double x)
    {
        this->x = x;
    }
    double GetX() const
    {
        return this->x;
    }

    void SetY(double y)
    {
        this->y = y;
    }
    double GetY() const
    {
        return this->y;
    }

    Point operator+(const Point &number)
    {
        this->x += number.x;
        this->y += number.y;
        return *this;
    }
    Point operator*(const Point &number)
    {
        this->x = this->x * number.x;
        this->y = this->y * number.y;
        return *this;
    }
    Point operator-(const Point &number)
    {
        this->x = this->x - number.x;
        this->y = this->y - number.y;
        return *this;
    }
    Point operator/(const Point &number)
    {
        if (number.x != 0 && number.y != 0)
        {
            this->x = this->x / number.x;
            this->y = this->y / number.y;
            return *this;
        }
        return *this;
    }

    friend bool operator==(const Point &n1, const Point &n2)
    {
        if (sqrt(((n1.GetX() - n2.GetX()) * (n1.GetX() - n2.GetX())) + ((n1.GetY() - n2.GetY()) * (n1.GetY() - n2.GetY()))) == 0)
            return true;
        return false;
    }
    friend bool operator!=(const Point &n1, const Point &n2)
    {
        if (sqrt(((n1.GetX() - n2.GetX()) * (n1.GetX() - n2.GetX())) + ((n1.GetY() - n2.GetY()) * (n1.GetY() - n2.GetY()))) != 0)
            return true;
        return false;
    }
    friend bool operator<(const Point &n1, const Point &n2)
    {
        if (sqrt(n1.GetX() * n1.GetX() + n1.GetY()*n1.GetY()) < sqrt(n2.GetX() * n2.GetX() + n2.GetY()*n2.GetY()))
            return true;
        return false;
    }
    friend bool operator>(const Point &n1, const Point &n2)
    {
        if (sqrt(n1.GetX() * n1.GetX() + n1.GetY()*n1.GetY()) > sqrt(n2.GetX() * n2.GetX() + n2.GetY()*n2.GetY()))
            return true;
        return false;
    }
    friend bool operator>=(const Point &n1, const Point &n2)
    {
        if (sqrt(n1.GetX() * n1.GetX() + n1.GetY()*n1.GetY()) >= sqrt(n2.GetX() * n2.GetX() + n2.GetY()*n2.GetY()))
            return true;
        return false;
    }
    friend bool operator<=(const Point &n1, const Point &n2)
    {
        if (sqrt(n1.GetX() * n1.GetX() + n1.GetY()*n1.GetY()) <= sqrt(n2.GetX() * n2.GetX() + n2.GetY()*n2.GetY()))
            return true;
        return false;
    }

    Point &operator++()
    {
        this->x = this->x + 1;
        this->y = this->y + 1;
        return *this;
    }
    const Point operator++(int dummy)
    {
        Point temp(*this);
        this->x = this->x + 1;
        this->y = this->y + 1;
        return temp;
    }

    Point &operator--()
    {
        this->x = this->x - 1;
        this->y = this->y - 1;
        return *this;
    }
    const Point operator--(int dummy)
    {
        Point temp(*this);
        this->x = this->x - 1;
        this->y = this->y - 1;
        return temp;
    }

  private:
    double x, y;
};
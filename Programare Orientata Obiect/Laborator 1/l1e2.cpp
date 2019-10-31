#include <stdlib.h>
#include <stdio.h>
#include <cmath>

#define MATRIX_HEIGHT 6
#define MATRIX_WIDTH 5

int dist(int p1_x, int p2_x, int p1_y, int p2_y)
{
    float a;
    a = sqrt(((p1_x - p2_x) * (p1_x - p2_x) + (p1_y - p2_y) * (p1_y - p2_y)));
    return ceil(a);
}

void Circle(int *ptr, int cx, int cy, int ray)
{
    for (int i = 0; i < MATRIX_HEIGHT; i++)
    {
        for (int j = 0; j < MATRIX_WIDTH; j++)
        {
            int d = dist(i,cx,j,cy);
            if (d == ray)
                *(ptr + (i * MATRIX_WIDTH) + j) = 1;
        }
    }
}

int main()
{
    FILE *fisier;
    char buffer[100];
    int v[1001], i = 0, j = 0;
    int Matrix[MATRIX_HEIGHT][MATRIX_WIDTH];

    fisier = fopen("matrix.txt", "r");
    if (fisier == NULL)
        perror("nu exista");

    for (i = 0; i < MATRIX_HEIGHT; i++)
    {
        for (j = 0; j < MATRIX_WIDTH; j++)
        {
            if (!fscanf(fisier, "%df", &Matrix[i][j]))
                break;
            printf("%d ", Matrix[i][j]);
        }
        printf("\n");
    }

    Circle(&Matrix[0][0], 2, 2, 2);
    printf("\n");
    for (i = 0; i < MATRIX_HEIGHT; i++)
    {
        for (j = 0; j < MATRIX_WIDTH; j++)
        {
            printf("%d ", Matrix[i][j]);
        }
        printf("\n");
    }
    fclose(fisier);
    return 0;
}
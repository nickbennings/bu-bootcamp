#include <stdio.h>

void print_math(int a, int b)
{
    printf("Sum: %d\n", a + b);
    printf("Product: %d\n", a * b);
}

int main()
{
    int firstNumber;
    int secondNumber;

    printf("Enter first number: ");
    scanf("%d", &firstNumber);

    printf("Enter second number: ");
    scanf("%d", &secondNumber);

    print_math(firstNumber, secondNumber);

    return 0;
}


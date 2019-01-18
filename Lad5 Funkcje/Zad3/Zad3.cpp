#include <iostream>
#include <cstdlib>
#include <ctime>
#define SIZE 50

using namespace std;

int dodaj(int *t, int n, int x){
    t[n]=x;
    return n+1;
}


int main()
{
    cout << "Podaj liczbe elementow pierwszej tablicy: ";
    int n;
    cin >> n;
    int * tab = (int*) malloc(SIZE);
    srand(time(NULL));
    for (int i=0;i<n;i++){
        tab[i]=rand()/(RAND_MAX+1.0)*100;
        cout << tab[i] << " ";
    }
    cout << endl << "Podaj element ktory chcesz dodac: ";
    int x;
    cin >> x;
    n = dodaj(tab, n, x);
    for (int i=0;i<n;i++){
        cout << tab[i] << " ";
    }
    cout << endl;
    return 0;
}

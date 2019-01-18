#include <iostream>
#include <cstdlib>
#include <ctime>
#define SIZE 50

using namespace std;

int usun(int *t, int n, int x){
    for(int i=0;i<n;i++){
        if (t[i]==x){
            t[i]=t[i+1];
            t[i+1]=x;
        }
    }
    return n-1;
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
    cout << endl << "Podaj element ktory chcesz usunac: ";
    int x;
    cin >> x;
    bool k = false;
    int licz = 0;
    for (int i=0;i<n;i++){
        if (tab[i]==x){
            k = true;
            licz++;
        }
    }
    if (k){
        for (int i=0;i<licz;i++){
            n = usun(tab, n, x);
        }
    }else{
        cout << "Nie ma takiego elementu" << endl;
    }
    
    for (int i=0;i<n;i++){
        cout << tab[i] << " ";
    }
    cout << endl;
    return 0;
}

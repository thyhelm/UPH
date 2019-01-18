#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;



void wypelnij(float t[], int n){
    srand(time(NULL));
    for(int i=0; i<n; i++){
        t[i]=3+rand()/(RAND_MAX+1.0)*1;
    }
}

void zamien(float t[], int n){
    for(int i=0; i<n; i++){
        t[i] = 1/t[i];
    }
}

int main(){
    cout << "Podaj wymiar tablicy" << endl;
    int n;
    cin >> n;
    float tab1[n];
    float tab2[n];
    wypelnij(tab1, n);
    for(int i=0; i<n; i++){
        tab2[i] = tab1[i];
    }
    zamien(tab1, n);
    for(int i=0; i<n; i++){
        cout << "Jeden dolar kosztuje " << tab2[i] << " zlotych a jeden zl kosztuje " << tab1[i] << " dolarow" << endl;
    }
    return 0;
}
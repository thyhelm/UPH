#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

void wypelnij(float t[], int n){
    srand(time(NULL));
    for(int i=0; i<n; i++){
        t[i]=rand()/(RAND_MAX+1.0)*6.28;
    }
}

void zamien(float t[], int n){
    for(int i=0; i<n; i++){
        t[i] = t[i]*(180/3.14);
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
        cout << tab2[i] << " radianow to " << tab1[i] << " stopni" << endl;
    }
    return 0;

}
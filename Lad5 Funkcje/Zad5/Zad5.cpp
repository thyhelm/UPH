#include <iostream>

using namespace std;

int suma(int *t, int n){
    int sum = 0;
    for(int i=0;i<n;i++){
        sum+=t[i];
    }
    return sum;
}

int main(){
    cout << "Podaj wymiar tablicy" << endl;
    int n;
    int s;
    cin >> n;
    int tab[n];
    for (int i=0;i<n;i++){
        cout << "Podaj " << i+1 << " element: ";  cin >> tab[i];
    }
    s = suma(tab, n);
    cout << s << " - suma elementow tablicy" << endl;
    return 0;
}
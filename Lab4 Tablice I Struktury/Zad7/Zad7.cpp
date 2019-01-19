#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

int main(){
    srand(time(NULL));
    cout << "Podaj przez ile dni chcesz przechowywac kurs dolara" << endl;
    int rozmiar;
    cin >> rozmiar;
    float * tab = new float[rozmiar];
    float sredni=0;
    for (int i=0;i<rozmiar;i++){
        tab[i] = 3 + rand() /(RAND_MAX*1.0/(4-3));
    }
    for (int i=0;i<rozmiar;i++){
        cout << "Dzien " << i+1 << ", wartosc: " << tab[i] << endl; 
        sredni += tab[i];
    }
    cout << "Sredni kurs dolara to: " << sredni/rozmiar << endl;
    return 0;
}
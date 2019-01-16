#include <iostream>

using namespace std;

struct TSamochod{
    string marka;
    string silnik;
    int rocznik;
    string numer_nadwozia;
};

int main()
{
    TSamochod baza[1000];
    string m, nn;
    int r;
    cout << "Podaj ile aut chcesz wprowadzic do bazy" << endl;
    int n;
    cin >> n;
    for(int i=0;i<n;i++){
        cout << "Samochod nr: " << i+1 << endl;
        cout << "Podaj marke: "; cin >> baza[i].marka;
        cout << "Podaj wielkosc silnika: "; cin >> baza[i].silnik;
        cout << "Podaj rocznik: "; cin >> baza[i].rocznik;
        cout << "Podaj numer nadwozia: "; cin >> baza[i].numer_nadwozia;
    }
    while (true){
        cout << "Wybierz co ma wykonać program: " << endl;
        cout << "...1...Wypisz z bazy samochody danej marki." << endl;
        cout << "...2...Wypisz z bazy samochody z danego rocznika. " << endl;
        cout << "...3...Wyszukaj samochod po numerze nadwozia " << endl;
        cout << "Naciśnij q aby wyjść z programu" << endl;

        char wyb;
        int kont=0;
        cin >> wyb;
        if (wyb == 'q'){
            return 0;

        }else{
            switch (wyb){
            case '1':
                cout << "Podaj marke" << endl;
                cin >> m;
                for (int i=0;i<n;i++){
                    if (m == baza[i].marka){
                        cout << baza[i].marka << endl;
                        cout << baza[i].silnik << endl;
                        cout << baza[i].rocznik << endl;
                        cout << baza[i].numer_nadwozia << endl;
                        cout << endl;
                        kont=1;
                    }
                }
                if (kont==0){
                    cout << "Brak wynikow" << endl;
                }
                break;
            case '2':
                cout << "Podaj rocznik" << endl;
                cin >> r;
                for (int i=0;i<n;i++){
                    if (r == baza[i].rocznik){
                        cout << baza[i].marka << endl;
                        cout << baza[i].silnik << endl;
                        cout << baza[i].rocznik << endl;
                        cout << baza[i].numer_nadwozia << endl;
                        cout << endl;
                        kont=1;
                    }
                }
                if (kont==0){
                    cout << "Brak wynikow" << endl;
                }
                break;
            case '3':
                cout << "Podaj numer nadwozia" << endl;
                cin >> nn;
                for (int i=0;i<n;i++){
                    if (nn == baza[i].numer_nadwozia){
                        cout << baza[i].marka << endl;
                        cout << baza[i].silnik << endl;
                        cout << baza[i].rocznik << endl;
                        cout << baza[i].numer_nadwozia << endl;
                        cout << endl;
                        kont=1;
                    }
                }
                if (kont==0){
                    cout << "Brak wynikow" << endl;
                }
                break;
            default:
                cout << "Blad w wyborze" << endl;
                break;
            }
        }
    }
    return 0;
}


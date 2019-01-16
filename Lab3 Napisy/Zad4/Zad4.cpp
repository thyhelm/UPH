#include <iostream>
#include <string>

using namespace std;

int main(){
    string napis1;
    int licznik = 0;
    cout << "Podaj napis" << endl;
    getline(cin, napis1);
    for(int i = 0; i<napis1.size();i++){
        if (napis1[i]==' '){
            int k = i;
            while (napis1[++k] == ' '){
                licznik++;
            }
            napis1.erase(i,licznik);
            licznik = 0;
        }
    }
    cout << napis1 << endl;
}



//Napisz program, który z podanego łańcucha znaków usunie powtarzające się spacje występujące obok siebie i zastąpi je pojedynczą spacją
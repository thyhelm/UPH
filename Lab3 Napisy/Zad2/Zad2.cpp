#include <iostream>
#include <string>

using namespace std;

int main(){
    string napis1, napis2;
    cout << "Podaj pierszy ciag znakow" << endl;
    cin >> napis1;
    cout << "Podaj drugi ciag znakow" << endl;
    cin >> napis2;
    if (napis1.size() == napis2.size()){
        cout << "podane ciagi sa rowne i maja po " << napis1.size() << " znakow" << endl;
    }
    else if(napis1.size() > napis2.size())
    {
        cout << "Pierwszy ciag jest dluzszy i ma " << napis1.size() << " znakow" << endl;
    }
    else{
        cout << "Drugi ciag jest dluzszy i ma " << napis2.size() << " znakow" << endl;
    }
    cout << "Ciagi razem maja " << napis1.size()+napis2.size() << " znakow" << endl;
}

//Napisz program, który sprawdzi czy podane dwa ciągi znaków są tej samej długości,
//jeśli nie to wypisze, który z ciągów jest dłuższy i ile ma znaków, jak również poda ile znaków, mają oba ciągi razem.
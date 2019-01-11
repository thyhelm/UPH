#include <iostream>

using namespace std;

int main(){
    float a1=5.0;
    float a2=15.0;
    float b1=1.0;
    float b2=6.0;
    if ((a1 < b1 && a2 < b1) || (b1 > a2 && b2 > a2)){
        cout << "Suma: <" << a1 << "," << a2 << ">+<" << b1 << "," << b2 << ">" << endl;
        cout << "Iloczyn: brak czesci wspolnej" << endl;
        cout << "Roznica A-B: <" << a1 << "," << a2 << ">" << endl;
        cout << "Roznica B-A: <" << b1 << "," << b2 << ">" << endl;
    }
    else if ((a1 < b1 && a2 < b1) || (b1 > a2 && b2 > a2))
    return 0;
} 

//Zad. 5. Dane są dwa przedziały: A=<a1,a2> i B=<b1,b2>. Napisz program, który liczy: sumę, iloczyn oraz różnicę A-B i B-A dla tych zbiorów. 
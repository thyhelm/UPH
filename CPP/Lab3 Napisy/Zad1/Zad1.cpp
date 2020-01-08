#include <iostream>
#include <string>

using namespace std;

int main(){
    string napis1, napis2;
    cout << "Podaj wyraz to sprawdzenia" << endl;
    cin >> napis1;
    int n = napis1.size();
    for(int i = 0; i<n;++i){
        napis2 += napis1[n-i-1];
    }
    if (napis1 == napis2){
        cout << "Wyraz jest polindromem" << endl;
    }else{
        cout << "Wyraz nie jest polindromem" << endl;
    }
}
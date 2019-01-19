#include <iostream>
#include <string>

using namespace std;

int main(){
    string napis1;
    cout << "Podaj napis" << endl;
    getline(cin, napis1);
    for(int i = 0; i < napis1.size(); i++){
        if (isupper(napis1[i])){
            napis1[i] = tolower(napis1[i]);
        }
    }
    cout << napis1 << endl;
}

//Napisz program, który w podanym ciągu znaków będzie zmieniał wszystkie duże litery podanego napisu na małe
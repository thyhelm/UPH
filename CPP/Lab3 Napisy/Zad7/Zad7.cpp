#include <iostream>
#include <string>

using namespace std;

int main()
{
    string s;
    int licznik = 0;
    cout << "Podaj napis" << endl;
    getline(cin, s);
    cout << "Podaj litere do sprawdzenia" << endl;
    char c, z;
    cin >> c;
    for(int i=0;i<s.length();i++){
        z = s[i];
        if ((tolower(c)) == (tolower(z))){
            licznik++;
        }
    }
    cout << "Podana litera wystepuje " << licznik << " razy" << endl;
    return 0;
}

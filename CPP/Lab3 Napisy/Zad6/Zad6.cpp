#include <iostream>
#include <string>

using namespace std;

int main()
{
    string s;
    int licznik = 0;
    int licz = 0;
    cout << "Podaj napis" << endl;
    getline(cin, s);
    char t[s.length()+2];
    for(int i=0;i<s.length();i++){
        t[i+1] = s[i];
    }
    t[0] = ' ';
    t[s.length()+1] = ' ';
    for(int i=1;i<=s.length();i++){
        if (isdigit(t[i])){
            if (t[i-1]==' '){
                licznik++;
            }
            else if (t[i+1]==' '){
                licznik++;
            }
        }
    }
    cout << "Napis zawiera " << licznik/2 << " liczb" << endl;
    return 0;
}

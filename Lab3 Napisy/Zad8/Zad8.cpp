#include <iostream>
#include <string>

using namespace std;

int main()
{
    string s1, s2;
    int licznik = 0;
    cout << "Podaj pierwszy napis" << endl;
    getline(cin, s1);

    cout << "Podaj drugi napis" << endl;
    getline(cin, s2);

    if (s1==s2){
        cout << '0' << endl;
    }
    else{
        for(int i=0;i<s1.length();i++){
            if (s1[i] < s2[i]){
                cout << "-1" << endl;
                break;
            }
            else{
                cout << "1" << endl;
                break;
            }
        }
    }

    return 0;
}
#include <iostream>

using namespace std;

int main()
{
    string napis1;
    int licznik = 0;
    int licz = 0;
    cout << "Podaj napis" << endl;
    getline(cin, napis1);
    for (int i = 0; i < napis1.size(); i++)
    {
        if (napis1[i] == ' ')
        {
            int k = i;
            while (napis1[++k] == ' '){
                licznik++;
            }
            napis1.erase(i,licznik);
            licznik = 0;
            licz++;
        }
    }
    if (napis1[napis1.size() - 1] != ' ')
    {
        licz++;
    }
    cout << "Podany napis zawiera " << licz << " wyrazow" << endl;
}
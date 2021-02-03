package stos;

import java.util.Scanner;

public class palindrom {
    public void program(){
        System.out.println("Podaj wyraz do sprawdzenia");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        StackT stos = new StackT(str.length());
        if (str.length()%2!=0){
            for(int i=0;i<str.length();i++){
                if(i<(str.length()-1)/2){
                    stos.push((int)str.charAt(i));
                }else if (stos.top() == (int)str.charAt(i)){
                    stos.pop();
                }
            }
            stos.wypisz();
            if (stos.isEmpty()){
                System.out.println("Wyraz jest palindromem");
            }
            else {
                System.out.println("Wyraz nie jest palindromem");
            }
        }
        else {
            System.out.println("Wyraz nie jest palindromem");
        }
    }
}

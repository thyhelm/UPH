package stos;

import java.util.Scanner;

public class nawiasy {
    public void program(){
        System.out.println("Wpisz ciąg nawiasów:");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        char[] chars = new char[str.length()];
        for(int i=0;i<str.length();i++){
            chars[i] = str.charAt(i);
        }
        StackT stos = new StackT(str.length());
        char lewy = '(';
        char prawy = ')';
        try {
            for(char x :chars){
                if(lewy == x){
                    stos.push((int)lewy);
                }
                if(prawy == x) {
                    stos.pop();
                }
            }
            if (stos.isEmpty()) {
                System.out.println("Nawiasy są git");
            }
            else {
                System.out.println("Nawiasy nie są git");
            }
        }
        catch (Exception e){
            System.out.println("Nawiasy nie są git");
        }

    }
}

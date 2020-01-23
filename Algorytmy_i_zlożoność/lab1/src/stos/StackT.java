package stos;

public class StackT implements IStack {
    int[] stack;
    int stopien;

    public StackT(int maks){
        stack = new int[maks-1];
        stopien = 0;
    }

    @Override
    public void push(int dane) {
        stack[stopien] = dane;
        stopien++;
    }

    @Override
    public void pop() {
        if (stopien >= 0){
            stack[stopien] = 0;
            stopien--;
        }
        else {
            System.out.println("Stos jest pusty");
        }
    }

    @Override
    public int top() {
        return stack[stopien-1];
    }

    @Override
    public boolean isEmpty() {
        return stack[0]==0;
    }

    public void wypisz() {
        for(int x : stack) {
            System.out.println(x);
        }
    }
}

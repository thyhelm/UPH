package stos;

public class StackT implements IStack {
    int top;
    int[] tab;

    public StackT(int x) {
        tab = new int[x-1];
        top = 0;
    }

    @Override
    public void push(int dane) {
        tab[top] = dane;
        top++;
    }

    @Override
    public void pop() {
        if(top>=0) {
            tab[top-1] = 0;
            top--;
        }
        else {System.err.println("Stack is empty");}
    }

    @Override
    public int top() {
        return tab[top-1];
    }

    @Override
    public boolean isEmpty() {
        return tab[0]==0;
    }

    public void wypisz(){
        for(int x:tab){
            System.out.println(x);
        }
    }
}

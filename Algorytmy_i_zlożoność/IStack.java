package stos;

/**
 * Interfejs stosu przechowujacego liczby calkowite.
 */
public interface IStack {
    /**
     * Wklada dane na stos
     * @param dane dane do wlozenia na wierzcholek stosu
     */
    public void push(int dane);
    
    /**
     * Zdejmuje ze stosu jeden element.
     */
    public void pop();
    
    /**
     * Zwraca wartosc znajdujaca sie na wierzchu stosu.
     * @return wartosc z wierzcholka
     */
    public int top();
    
    
    /**
     * Sprawdza czy stos jest pusty.
     * @return true jesli stos jest pusty
     */
    public boolean isEmpty();
}

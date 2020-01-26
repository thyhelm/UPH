package Sort;
public abstract class SortZ {

    public enum SortMethod {

        SELECTION, INSERTION, QUICKSORT, COUNTSORT;
    }

    int[] tab; // tablica liczb do sortowania
    int ile;      // ile element�w jest w tablicy

    protected abstract int losuj(int w_max); // zwraca losowa liczbe z przedzialu 0..w_max
	
	public abstract void wypelnij(int n, int wartosc_maksymalna);// wypelnia tablice n losowymi liczbami z przedzialu 0..w_max
	
	public abstract void wypisz(); // wypisuje zawartosc tablicy tab
	
	public abstract boolean compare(int a, int b, boolean rosnaco); // zwraca wynik por�wanania liczb a <= b lub a >= b w zale�no�ci od parametru rosnaco
	
    protected abstract void selectionsort(boolean rosnaco);

    protected abstract void insertsort(boolean rosnaco);
	
	protected abstract void quicksort(boolean rosnaco);
	
	protected abstract void countsort(boolean rosnaco);


    public abstract void sortuj(boolean rosnaco, SortMethod metoda); // wywoluje odpowiedni algorytm sortowania 

    
}

package Sort;

public class Main {
    public static void main(String[] args){
        MySort ms = new MySort();
        ms.wypelnij(10,100);
        ms.wypisz();
        System.out.println(ms.compare(2,4, true));
        System.out.println("\nSortowanie przez selekcje:");
        ms.sortuj(true, SortZ.SortMethod.SELECTION);
        ms.wypisz();
        ms.sortuj(false, SortZ.SortMethod.SELECTION);
        ms.wypisz();
        System.out.println("\nSortowanie przez wstawianie:");
        ms.sortuj(true, SortZ.SortMethod.INSERTION);
        ms.wypisz();
        ms.sortuj(false, SortZ.SortMethod.INSERTION);
        ms.wypisz();
    }
}

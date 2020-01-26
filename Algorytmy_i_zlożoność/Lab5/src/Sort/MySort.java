package Sort;

import java.util.Random;

public class MySort extends SortZ{

    @Override
    protected int losuj(int w_max) {
        Random r = new Random();
        return r.nextInt(w_max);
    }

    @Override
    public void wypelnij(int n, int wartosc_maksymalna) {
        tab = new int[n];
        for(int i=0; i<n; i++){
            tab[i] = losuj(wartosc_maksymalna);
        }
    }

    @Override
    public void wypisz() {
        for (int x : tab) {
            System.out.print(x+" ");
        }
        System.out.println();
    }

    @Override
    public boolean compare(int a, int b, boolean rosnaco) {
        if (rosnaco) {
            if (a<b) return true;
            else return false;
        }
        else {
            if (a<b) return false;
            else return true;
        }
    }

    @Override
    protected void selectionsort(boolean rosnaco) {
        if (rosnaco) {
            int n = tab.length;
            for (int i = 0; i < n - 1; i++) {
                int min = i;
                for (int j = i + 1; j < n; j++)
                    if (tab[j] < tab[min])
                        min = j;

                int temp = tab[min];
                tab[min] = tab[i];
                tab[i] = temp;
            }
        }
        else {
            int n = tab.length;
            for (int i = 0; i < n - 1; i++) {
                int max = i;
                for (int j = i + 1; j < n; j++)
                    if (tab[j] > tab[max])
                        max = j;

                int temp = tab[max];
                tab[max] = tab[i];
                tab[i] = temp;
            }
        }
    }

    @Override
    protected void insertsort(boolean rosnaco) {
        if (rosnaco) {
            int n = tab.length;
            for (int i = 1; i < n; ++i) {
                int key = tab[i];
                int j = i - 1;
                while (j >= 0 && tab[j] > key) {
                    tab[j + 1] = tab[j];
                    j = j - 1;
                }
                tab[j + 1] = key;
            }
        }
        else {
            int n = tab.length;
            for (int i = 1; i < n; ++i) {
                int key = tab[i];
                int j = i - 1;
                while (j >= 0 && tab[j] < key) {
                    tab[j + 1] = tab[j];
                    j = j - 1;
                }
                tab[j + 1] = key;
            }
        }
    }

    @Override
    protected void quicksort(boolean rosnaco) {

    }

    @Override
    protected void countsort(boolean rosnaco) {

    }

    @Override
    public void sortuj(boolean rosnaco, SortMethod metoda) {
        switch (metoda) {
            case SELECTION:
                selectionsort(rosnaco);
                break;
            case INSERTION:
                insertsort(rosnaco);
                break;
            case QUICKSORT:
                quicksort(rosnaco);
                break;
            case COUNTSORT:
                countsort(rosnaco);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + metoda);
        }

    }
}

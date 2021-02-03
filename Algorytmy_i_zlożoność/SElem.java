package stos;

/**
 * Element stosu jako struktury wiazanej. Przechowuje liczby calkowite.
 */
public class SElem {
    private int data;
    private SElem under;

    public SElem() {
        data = -1;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public SElem getUnder() {
        return under;
    }

    public void setUnder(SElem under) {
        this.under = under;
    }
    
    
}

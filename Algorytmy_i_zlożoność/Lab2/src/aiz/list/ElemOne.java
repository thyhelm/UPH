package aiz.list;

/**
 * Element generycznej listy jednokierunkowej.
 */
public class ElemOne<T> {
    private T data;
    private ElemOne next;

    public ElemOne(T data) {
        this.data = data;
    }

    public ElemOne(T data, ElemOne next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ElemOne getNext() {
        return next;
    }

    public void setNext(ElemOne next) {
        this.next = next;
    }
    
}

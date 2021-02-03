package aiz.list;


import java.util.ArrayList;
import java.util.List;

public class ListOne implements IList<Object>{

    private List<Object> list = new ArrayList<>();
    private int next = 0;


    @Override
    public void addFirst(Object newData) {
        this.list[next] = newData;
        this.next = this.next + 1;
    }

    @Override
    public void addLast(Object newData) {

    }

    @Override
    public void addAtPosition(Object newData, int position) throws ListException {

    }

    @Override
    public int size() {
        return list.length;
    }

    @Override
    public Object removeFirst() throws ListException {
        return null;
    }

    @Override
    public Object removeLast() throws ListException {
        return null;
    }

    @Override
    public Object remove(int position) throws ListException {
        return null;
    }

    @Override
    public int find(Object dataToFind) {
        return 0;
    }

    @Override
    public boolean contains(Object data) {
        return false;
    }

    @Override
    public void print() {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}

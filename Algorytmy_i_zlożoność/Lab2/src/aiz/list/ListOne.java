package aiz.list;


public class ListOne<T> implements IList<T>{

    private ElemOne<T> first, last;


    @Override
    public void addFirst(T newData) {
        ElemOne<T> old;
        if(first != null){
            old = first;
            first = new ElemOne(newData, old);
        }
        else {
            first = new ElemOne(newData);
            last = first;
        }
    }

    @Override
    public void addLast(T newData) {
        if (first != null){
            last.setNext(new ElemOne(newData));
            last = last.getNext();
        }
        else {
            first = new ElemOne(newData);
            last = first;
        }

    }

    @Override
    public void addAtPosition(T newData, int position) throws ListException {
        ElemOne<T> temp, temp2;
        temp = first;
        int size = this.size();
        if (position>=0 && position<size){
            for (int i=0; i<size;i++){
                if (position==0 && i==0){
                    this.addFirst(newData);
                }
                if (i==position-1){
                    temp2 = temp.getNext();
                    temp.setNext(new ElemOne(newData, temp2));
                } else {
                    temp = temp.getNext();
                }
            }
        } else {
            throw new ListException("Bad position");
        }
    }

    @Override
    public int size() {
        ElemOne<T> temp;
        temp = first;
        if (temp == null){
            return 0;
        }
        else {
            if (temp.getNext() == null) {
                return 1;
            } else {
                int size = 1;
                while (temp.getNext()!=null){
                    size++;
                    temp = temp.getNext();
                }
                return size;
            }
        }
    }

    @Override
    public T removeFirst() throws ListException {
        ElemOne<T> temp;
        T deleted;
        if (first!=null){
            if (first.getNext()==null){
                first = null;
                return first.getData();
            }else {
                deleted = first.getData();
                temp = first.getNext();
                first = temp;
                return deleted;
            }
        }
        else {
            throw new ListException("List is empty");
        }
    }

    @Override
    public T removeLast() throws ListException {
        ElemOne<T> temp, temp2 = null;
        temp = first;
        if (first!=null){
            if (first.getNext()==null){
                first = null;
                return first.getData();
            }else {
                while (temp.getNext() != null){
                    temp2 = temp;
                    temp = temp.getNext();
                }
                temp2.setNext(null);
                return last.getData();
            }
        }
        else {
            throw new ListException("List is empty");
        }
    }

    @Override
    public T remove(int position) throws ListException {
        ElemOne<T> temp, temp2;
        temp = first;
        int size = this.size();
        if (position>=0 && position<size){
            for (int i=0; i<size;i++){
                if (position==0 && i==0){
                    return this.removeFirst();
                }
                if (i==position-1){
                    temp2 = temp.getNext();
                    temp.setNext(temp2.getNext());
                    return temp2.getData();
                } else {
                    temp = temp.getNext();
                }
            }
        } else {
            throw new ListException("Bad position");
        }
        return null;
    }

    @Override
    public int find(T dataToFind) {
        ElemOne<T> temp;
        temp = first;
        int index = 0;
        if(temp != null){
            while (temp != null){
                if (temp.getData()==dataToFind){
                    return index;
                }
                index++;
                temp = temp.getNext();
            }
        }
        return -1;
    }

    @Override
    public boolean contains(T data) {
        ElemOne<T> temp;
        temp = first;
        if(temp != null){
            while (temp != null){
                if (temp.getData()==data){
                    return true;
                }
                temp = temp.getNext();
            }
        }
        return false;
    }

    @Override
    public void print() {
        ElemOne<T> temp;
        temp = first;
        if(temp != null){
            while (temp != null){
                System.out.println(temp.getData());
                temp = temp.getNext();
            }
        }
    }
}

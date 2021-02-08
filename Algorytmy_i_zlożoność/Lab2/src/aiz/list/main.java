package aiz.list;

public class main {
    public static void main(String[] args) throws ListException {
        ListOne listOne = new ListOne();
        listOne.addFirst("Test1");
        listOne.addFirst("Test2");
        listOne.addFirst("Test3");
        listOne.addLast(1);
        listOne.addLast(2);
        listOne.addLast(3);
        listOne.addFirst("Test4");
        listOne.addAtPosition(4, 0);
        listOne.addAtPosition(4, 5);
        System.out.println(listOne.removeFirst()+" - deleted");
        System.out.println(listOne.removeFirst()+" - deleted");
        System.out.println(listOne.removeLast()+" - deleted");
        System.out.println(listOne.remove(4)+" - deleted");
        System.out.println("Test3 at positions "+listOne.find("Test3"));
        System.out.println("Test1 at positions "+listOne.find("Test1"));
        System.out.println("4 exist ="+listOne.contains(4));
        System.out.println("2 exist ="+listOne.contains(2));
        System.out.println("6 exist ="+listOne.contains(6));
        listOne.print();
    }
}

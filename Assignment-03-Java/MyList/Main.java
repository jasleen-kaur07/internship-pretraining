public class Main {

    public static void main(String[] args) {

        MyList list = new MyList();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        System.out.println("Initial List:");
        list.display();

        System.out.println("Element at index 2:");
        System.out.println(list.get(2));

        list.deleteByIndex(1);

        System.out.println("After deleting index 1:");
        list.display();

        list.deleteByValue(30);

        System.out.println("After deleting value 30:");
        list.display();

        System.out.println("Current Size:");
        System.out.println(list.size());
    }
}
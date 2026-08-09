import java.util.*;

public class JavaIterator {

    static Iterator<Object> func(ArrayList<Object> mylist) {

        Iterator<Object> it = mylist.iterator();

        while (it.hasNext()) {

            Object element = it.next();

            if (element instanceof String) {
                break;
            }
        }

        return it;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of integers: ");
        int n = sc.nextInt();

        System.out.print("Enter number of strings: ");
        int m = sc.nextInt();

        ArrayList<Object> mylist = new ArrayList<>();

        System.out.println("Enter integers:");
        for (int i = 0; i < n; i++) {
            mylist.add(sc.nextInt());
        }

        mylist.add("###");

        System.out.println("Enter strings:");
        for (int i = 0; i < m; i++) {
            mylist.add(sc.next());
        }

        Iterator<Object> it = func(mylist);

        while (it.hasNext()) {
            System.out.println((String) it.next());
        }

        sc.close();
    }
}
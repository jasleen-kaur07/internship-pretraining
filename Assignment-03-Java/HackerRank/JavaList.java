import java.util.ArrayList;
import java.util.Scanner;

public class JavaList {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        int q = sc.nextInt();

        for (int i = 0; i < q; i++) {

            String operation = sc.next();

            if (operation.equals("Insert")) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                list.add(x, y);
            } else if (operation.equals("Delete")) {
                int x = sc.nextInt();
                list.remove(x);
            }
        }

        for (int num : list) {
            System.out.print(num + " ");
        }

        sc.close();
    }
}
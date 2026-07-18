import java.util.HashMap;
import java.util.Scanner;

public class JavaMap {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        in.nextLine();

        HashMap<String, Integer> phoneBook = new HashMap<>();

        for (int i = 0; i < n; i++) {

            String name = in.nextLine();
            int phone = in.nextInt();
            in.nextLine();

            phoneBook.put(name, phone);
        }

        while (in.hasNext()) {

            String name = in.nextLine();

            if (phoneBook.containsKey(name)) {
                System.out.println(name + "=" + phoneBook.get(name));
            } else {
                System.out.println("Not found");
            }
        }

        in.close();
    }
}
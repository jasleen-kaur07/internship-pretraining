import java.util.Scanner;

public class ValidUsername {

    public static final String regularExpression =
            "^[A-Za-z][A-Za-z0-9_]{7,29}$";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        if (username.matches(regularExpression)) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }

        sc.close();
    }
}
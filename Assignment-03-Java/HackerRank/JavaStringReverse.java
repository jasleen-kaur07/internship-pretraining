import java.util.Scanner;

public class JavaStringReverse {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String A = sc.next();

        String reverse = "";

        for (int i = A.length() - 1; i >= 0; i--) {
            reverse += A.charAt(i);
        }

        if (A.equals(reverse)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        sc.close();
    }
}

import java.util.Scanner;

public class JavaInttoString {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        String s = String.valueOf(n);

        System.out.println(s);

        scanner.close();
    }
}
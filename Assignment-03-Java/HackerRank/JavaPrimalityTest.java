import java.math.BigInteger;
import java.util.Scanner;

public class JavaPrimalityTest {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        String input = sc.nextLine();

        BigInteger number = new BigInteger(input);

        if (number.isProbablePrime(1)) {
            System.out.println("Prime");
        } else {
            System.out.println("Not Prime");
        }

        sc.close();
    }
}
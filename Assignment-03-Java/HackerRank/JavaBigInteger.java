import java.math.BigInteger;
import java.util.Scanner;

public class JavaBigInteger {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        BigInteger a = new BigInteger(sc.nextLine());

        System.out.print("Enter second number: ");
        BigInteger b = new BigInteger(sc.nextLine());

        BigInteger sum = a.add(b);
        BigInteger product = a.multiply(b);

        System.out.println("\nSum = " + sum);
        System.out.println("Product = " + product);

        sc.close();
    }
}
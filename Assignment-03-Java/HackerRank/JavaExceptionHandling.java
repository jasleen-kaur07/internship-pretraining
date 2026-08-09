import java.util.InputMismatchException;
import java.util.Scanner;

public class JavaExceptionHandling {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter x: ");
            int x = sc.nextInt();

            System.out.print("Enter y: ");
            int y = sc.nextInt();

            System.out.println("Result = " + (x / y));

        } catch (ArithmeticException e) {

            System.out.println(e);

        } catch (InputMismatchException e) {

            System.out.println("java.util.InputMismatchException");

        }

        sc.close();
    }
}
import java.util.Scanner;

public class Java1DArray {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] a = new int[n];

        System.out.println("Enter " + n + " elements:");

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println("Array Elements:");

        for (int i = 0; i < n; i++) {
            System.out.println(a[i]);
        }

        sc.close();
    }
}
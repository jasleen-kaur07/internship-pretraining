import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class JavaBigDecimal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of values: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] numbers = new String[n];

        System.out.println("Enter the numbers:");

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextLine();
        }

        Arrays.sort(numbers, new Comparator<String>() {

            @Override
            public int compare(String a, String b) {

                BigDecimal bd1 = new BigDecimal(a);
                BigDecimal bd2 = new BigDecimal(b);

                return bd2.compareTo(bd1);
            }
        });

        System.out.println("\nSorted Numbers:");

        for (String num : numbers) {
            System.out.println(num);
        }

        sc.close();
    }
}
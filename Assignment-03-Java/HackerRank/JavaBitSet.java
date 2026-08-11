import java.util.BitSet;
import java.util.Scanner;

public class JavaBitSet {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N (size) and M (operations): ");
        int N = sc.nextInt();
        int M = sc.nextInt();

        BitSet B1 = new BitSet(N);
        BitSet B2 = new BitSet(N);

        System.out.println("Enter the operations:");

        for (int i = 0; i < M; i++) {

            String operation = sc.next();
            int set = sc.nextInt();
            int value = sc.nextInt();

            BitSet first = (set == 1) ? B1 : B2;

            switch (operation) {

                case "AND":
                    if (value == 1)
                        first.and(B1);
                    else
                        first.and(B2);
                    break;

                case "OR":
                    if (value == 1)
                        first.or(B1);
                    else
                        first.or(B2);
                    break;

                case "XOR":
                    if (value == 1)
                        first.xor(B1);
                    else
                        first.xor(B2);
                    break;

                case "FLIP":
                    first.flip(value);
                    break;

                case "SET":
                    first.set(value);
                    break;

                default:
                    System.out.println("Invalid Operation");
            }

            System.out.println(B1.cardinality() + " " + B2.cardinality());
        }

        sc.close();
    }
}
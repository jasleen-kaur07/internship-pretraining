import java.util.Scanner;

public class JavaStringTokens {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String s = scan.nextLine();

        if (s.trim().isEmpty()) {
            System.out.println(0);
        } else {

            String[] tokens = s.trim().split("[ !,?._'@]+");

            System.out.println("Number of tokens: " + tokens.length);

            for (String token : tokens) {
                System.out.println(token);
            }
        }

        scan.close();
    }
}
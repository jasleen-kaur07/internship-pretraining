import java.util.Scanner;

public class JavaEndofFile {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int line = 1;

        while (scanner.hasNext()) {

            String input = scanner.nextLine();

            System.out.println(line + " " + input);

            line++;

        }

        scanner.close();
    }
}





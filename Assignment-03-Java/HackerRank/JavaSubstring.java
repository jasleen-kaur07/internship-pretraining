import java.util.Scanner;

public class JavaSubstring {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int start = sc.nextInt();
        int end = sc.nextInt();

        String result = s.substring(start, end);

        System.out.println(result);

        sc.close();
    }
}
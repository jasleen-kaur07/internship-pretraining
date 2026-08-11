import java.util.Scanner;

class MyRegex {

    String pattern =
        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
}

public class JavaRegex {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MyRegex myRegex = new MyRegex();

        System.out.print("Enter IP Address: ");
        String ip = sc.nextLine();

        if (ip.matches(myRegex.pattern)) {
            System.out.println("Valid IP Address");
        } else {
            System.out.println("Invalid IP Address");
        }

        sc.close();
    }
}
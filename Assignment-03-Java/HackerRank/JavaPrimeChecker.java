import java.util.Scanner;

class Prime {

    public void checkPrime(int... numbers) {

        for (int n : numbers) {
            if (isPrime(n)) {
                System.out.print(n + " ");
            }
        }

        System.out.println();
    }

    private boolean isPrime(int n) {

        if (n < 2)
            return false;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }

        return true;
    }
}

public class JavaPrimeChecker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int n3 = sc.nextInt();
        int n4 = sc.nextInt();
        int n5 = sc.nextInt();

        Prime ob = new Prime();

        ob.checkPrime(n1);
        ob.checkPrime(n1, n2);
        ob.checkPrime(n1, n2, n3);
        ob.checkPrime(n1, n2, n3, n4, n5);

        sc.close();
    }
}
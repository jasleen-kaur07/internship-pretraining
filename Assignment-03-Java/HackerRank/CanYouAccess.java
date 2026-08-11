import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CanYouAccess {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter a number: ");
        int num = Integer.parseInt(br.readLine().trim());

        Inner inner = new Inner();

        Inner.Private p = inner.new Private();

        System.out.println(num + " is " + p.powerof2(num));

        Object o = p;

        System.out.println("An instance of class: " +
                o.getClass().getCanonicalName() +
                " has been created");
    }

    static class Inner {

        private class Private {

            private String powerof2(int num) {

                return ((num & (num - 1)) == 0)
                        ? "power of 2"
                        : "not a power of 2";
            }
        }
    }
}
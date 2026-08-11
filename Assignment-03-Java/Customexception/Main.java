public class Main {

    public static void main(String[] args) {

        try {

            NumberChecker.checkNumber(5);
            NumberChecker.checkNumber(15);

        } catch (MoreThanOneDigitException e) {

            System.out.println(e.getMessage());

        }

    }

}
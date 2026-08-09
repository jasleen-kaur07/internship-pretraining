public class NumberChecker {

    public static void checkNumber(int number) throws MoreThanOneDigitException {

        if (number < -9 || number > 9) {
            throw new MoreThanOneDigitException("Number has more than one digit.");
        }

        System.out.println("Valid one-digit number: " + number);
    }

}
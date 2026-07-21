import java.util.Calendar;
import java.util.Scanner;

public class JavaDateAndTime {

    public static String findDay(int month, int day, int year) {

        Calendar cal = Calendar.getInstance();

        cal.set(year, month - 1, day);

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        String[] days = {
            "SUNDAY",
            "MONDAY",
            "TUESDAY",
            "WEDNESDAY",
            "THURSDAY",
            "FRIDAY",
            "SATURDAY"
        };

        return days[dayOfWeek - 1];
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter month: ");
        int month = scanner.nextInt();

        System.out.print("Enter day: ");
        int day = scanner.nextInt();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        String result = findDay(month, day, year);

        System.out.println("Day: " + result);

        scanner.close();
    }
}
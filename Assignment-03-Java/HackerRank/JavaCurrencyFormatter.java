import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class JavaCurrencyFormatter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter payment: ");
        double payment = scanner.nextDouble();

        
        Locale us = Locale.US;
       
        Locale india = new Locale("en", "IN");

        Locale china = Locale.CHINA;

        Locale france = Locale.FRANCE;

        String usCurrency = NumberFormat.getCurrencyInstance(us).format(payment);
        String indiaCurrency = NumberFormat.getCurrencyInstance(india).format(payment);
        String chinaCurrency = NumberFormat.getCurrencyInstance(china).format(payment);
        String franceCurrency = NumberFormat.getCurrencyInstance(france).format(payment);

        System.out.println("US: " + usCurrency);
        System.out.println("India: " + indiaCurrency);
        System.out.println("China: " + chinaCurrency);
        System.out.println("France: " + franceCurrency);

        scanner.close();
    }
}
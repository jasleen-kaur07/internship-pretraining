public class Main {

    public static void main(String[] args) {

        double amount = 1000;

        ICICI icici = new ICICI();
        HDFC hdfc = new HDFC();

        System.out.println("ICICI Transaction Charge: " + icici.calculateCharge(amount));
        System.out.println("HDFC Transaction Charge: " + hdfc.calculateCharge(amount));

    }

}
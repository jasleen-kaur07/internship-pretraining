public class ICICI extends Bank {

    public ICICI() {
        transactionCharge = 15.0;
    }

    @Override
    public double calculateCharge(double amount) {
        return transactionCharge + (amount * 0.01);
    }

}
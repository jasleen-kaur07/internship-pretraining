public class Bank {

    protected double transactionCharge;

    public Bank() {
        transactionCharge = 10.0;
    }

    public double calculateCharge(double amount) {
        return transactionCharge;
    }

}
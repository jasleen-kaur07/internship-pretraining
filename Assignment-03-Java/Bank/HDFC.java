public class HDFC extends Bank {

    public HDFC() {
        transactionCharge = 12.0;
    }

    @Override
    public double calculateCharge(double amount) {
        return transactionCharge + (amount * 0.008);
    }

}
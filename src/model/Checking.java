package model;


public class Checking extends Account {

    private static String accType = "Checking";

    //  Constructores

    public Checking(double initialDeposit){

        super();
        this.setBalance(initialDeposit);
        this.checkInterest();
    }

    //  Metodos

    @Override
    public String toString() {
        return  "Account Type: "    + accType + " Account\n" +
                "Account Number: "  + this.getAccNumber() + "\n" +
                "Balance: "         + this.getBalance() + "\n" +
                "Interest Rate: "   + this.getInterest() + "%\n ";
    }
}

package model;

public class Savings extends Account {

    private static String accType = "Savings";

    //  Constructores

    public Savings(double initialDeposit){

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

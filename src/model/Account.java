package model;

import java.io.Serializable;

/**
 * Created by takk on 05/06/2017.
 */
public class Account implements Serializable {

    private double  balance = 0;
    private double  interest = 0.01;
    private int     accNumber;
    private static int  numberOfAccs = 1;


    //  Constructores

    Account(){
        accNumber = numberOfAccs++;
    }

    public Account(double balance,
                   double interest,
                   int accNumber) {

        this.balance = balance;
        this.interest = interest;
        this.accNumber = accNumber;
    }

    //  Accesores

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Multiplicamos el interes por 100 para que salga el tanto por ciento
     * @return
     */
    public double getInterest() {
        return interest * 100;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    //  Metodos

    public void withdraw(double amount){

        if(amount > balance){
            System.out.println("You have insufficient funds.");
            return;
        }
        balance = balance - amount;
        System.out.println("You have withdrawn " + amount + "€");
        System.out.println("Your is balance is: " + balance + "€");

    }
    public void deposit(double amount){

        if (amount <= 0 ){
            System.out.println("Enter a positive amount.");
            return;
        }
        checkInterest();
        amount = amount + (amount * interest);
        balance = balance + amount;

        System.out.println("You have deposited " + amount + "€" + " with an interest rate of " + getInterest() + "%");
        System.out.println("Your is balance is: " + balance + "€");

    }

    public void checkInterest(){

        if ( balance > 1000 ){
            this.setInterest(0.07);
        }else{
            this.setInterest(0.02);
        }
    }


}

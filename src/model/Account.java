package model;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 3476980086996915495L;
    private double      balance = 0;
    private double      interest = 0.01;
    private int         accNumber;
    private static int  numberOfAccs = recoverIndex();



    //  Constructores

    public Account(){
        accNumber =  numberOfAccs;
        numberOfAccs++;
    }

    public Account(double balance,
                   double interest) {

        this.balance = balance;
        this.interest = interest;
        accNumber = numberOfAccs++;
    }

    //  Accesores

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     *  Multiplicamos el interes por 100 para que salga el tanto por ciento
     *  @return
     */
    public double getInterest() {
        return this.interest * 100;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getAccNumber() {
        return accNumber;
    }

    //  Metodos

    /**
     *  Metodo con el que "sacamos dinero"
     *  restamos una cantidad introducida como parametro a balance
     *  @param amount
     */

    public void withdraw(double amount){

        if(amount > balance){
            System.out.println("You have insufficient funds.");
            return;
        }
        checkInterest();
        balance = balance - amount;
        System.out.println("You have withdrawn " + amount + "€");
        System.out.println("Your is balance is: " + balance + "€");

    }

    /**
     *  Metodo con el que "ingresamos"
     *  sumamos una cantidad introducida como parametro a balance
     *  @param amount
     */
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

    /**
     *  Comprueba el balance, si es superior a 1000
     *  setea los intereses a un valor determinado
     */

    public void checkInterest(){

        if ( balance > 1000 ){
            this.setInterest(0.07);
        }else{
            this.setInterest(0.02);
        }
    }

    public static int recoverIndex(){
        Bank bank = new Bank();

            return  bank.customers.size() + 1;

    }
}

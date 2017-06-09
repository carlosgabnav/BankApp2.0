package model;

import java.io.Serializable;
import java.util.Comparator;

public class Customer implements Comparable<Customer>,Comparator<Customer>, Serializable{

    private static final long serialVersionUID = 5144261382049047231L;
    private String  firstName;
    private String  lastName;
    private Account account;

    //  Constructores

    public Customer(){

    }

    public Customer(Account account){
        this.account = account;
    }

    public Customer(String firstName,
                    String lastName,
                    Account account) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }

    //  Accesores

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    //  Métodos

    @Override
    public String toString() {

        return  "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                account;
    }

    public String accInfo() {

        return  "Account Number: " + account.getAccNumber() + " | " +
                "First Name: " + firstName + " | " +
                "Last Name: " + lastName + " | " +
                "Balance: " + account.getBalance();

    }


    public Account getAccount() {

        return account;
    }

    //  Comparadores

    @Override
    public int compareTo(Customer o) {

        return this.getFirstName().compareToIgnoreCase(o.getFirstName());
    }

    @Override
    public int compare(Customer customer1, Customer customer2) {

        return Integer.compare(customer1.getAccount().getAccNumber(),customer2.getAccount().getAccNumber());
    }

    public static Comparator<Customer> compareByBalance = new Comparator<Customer>() {

        @Override
        public int compare(Customer customer1, Customer customer2) {

            return (int) (customer1.getAccount().getBalance() - customer2.getAccount().getBalance());
        }
        };


    }


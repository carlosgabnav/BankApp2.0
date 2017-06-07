package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;


/**
 * Created by takk on 05/06/2017.
 */
public class Bank implements Serializable {

    public List<Customer> customers = new ArrayList<>();

    // Accesores

    public void addCostumer(Customer customer) {

        customers.add(customer);
    }

    public void deleteAccount() {

        int acc = chooseAccount();
        customers.remove(acc);

    }

    public Customer getCustomer(int account) {
        return customers.get(account);
    }

    public void showCustomers() {
        for (Customer customer : customers) {

            System.out.println(customer.accInfo());
        }
    }


    public ArrayList<Customer> getAllCustomers() {

        return (ArrayList) customers;
    }

    // Metodos


    public void movementList() {
        int acc = chooseAccount();

        if (acc >= 0) {
            System.out.println(getCustomer(acc).getAccount());
        } else {
            System.out.println("That account doesnt exists");
        }
    }

    private int chooseAccount() {
        Scanner sc = new Scanner(System.in);
        int acc;
        if (customers.isEmpty()) {
            System.out.println("No customer at the bank.");
            acc = -1;
        } else {
            System.out.println("Accounts: ");
            int i = 0;
            for (Customer custo : customers) {
                System.out.println((i + 1) + "-" + customers.get(i).accInfo());
                i++;
            }
            try {
                System.out.println("Select an account: ");
                acc = sc.nextInt();
                if (acc < 0 || acc > customers.size()) {
                    acc = -1;
                } else {
                    customers.indexOf(acc);

                }
            } catch (InputMismatchException e) {
                acc = -1;
                System.out.println("Invalid account.");
            }
        }
        return acc;
    }

    public void makeWithdraw() {
        Scanner sc = new Scanner(System.in);
        int acc = chooseAccount();

        if (acc >= 0) {
            double amount = 0;
            try {
                System.out.println("How much do u like to withdraw?: ");
                amount = sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Sorry enter another amount ");
            }
            getCustomer(acc).getAccount().withdraw(amount);
        }
    }

    public void makeDeposit() {
        Scanner sc = new Scanner(System.in);
        int acc = chooseAccount();

        if (acc >= 0) {
            double amount = 0;
            try {
                System.out.println("How much do u like to deposit?: ");
                amount = sc.nextDouble();
                if (amount < 0) {
                    amount = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Sorry enter another amount ");
            }
            getCustomer(acc).getAccount().deposit(amount);
        }
    }

    public void newAccount() {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        String firstName, lastName, accType;
        double initialDeposit = 0;

        boolean validType = false;
        while (!validType) {
            do {
                System.out.println("Please enter an account type (checking/savings): ");
                accType = sc.nextLine();
                if (accType.equalsIgnoreCase("checking") || accType.equalsIgnoreCase("savings")) {
                    validType = true;
                } else {
                    System.out.println("Invalid account type, please enter \"checking\" or \"savings\"");
                }

            } while (!validType);

            System.out.println("Enter your first name: ");
            firstName = sc.nextLine();
            System.out.println("Great! Enter your last name: ");
            lastName = sc.nextLine();
            validType = false;
            while (!validType) {
                System.out.println("Please enter an inital deposit: ");

                try {
                    initialDeposit = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Deposit must be a number");
                }
                if (accType.equalsIgnoreCase("checking")) {
                    if (initialDeposit < 120) {

                        System.out.println("Checking account need a minimum of 120€ to open");
                    } else {

                        validType = true;
                    }
                } else if (accType.equalsIgnoreCase("savings")) {
                    if (initialDeposit < 30) {
                        System.out.println("Savings account need a minimum of 30% to open");
                    } else {
                        validType = true;
                    }
                }
            }

            // Tenemos todos los datos y podemos crear una cuenta.

            Account account;
            if (accType.equalsIgnoreCase("checking")) {
                account = new Checking(initialDeposit);
            } else {
                account = new Savings(initialDeposit);
            }

            Customer customer = new Customer(firstName, lastName, account);
            addCostumer(customer);
        }
    }

    /**
     * Método de ordenación por nombre.
     * Ordenación siguiendo la interfaz Comparable (método: compareTo)
     */
    public void sortByName() {
        Collections.sort(customers);
    }

    /**
     * Método de ordenación segun el numero de cuenta.
     * Ordenación siguiendo la interfaz Comparator (método: compare)
     */

    public void sortByAccNumber() {
        Collections.sort(customers, new Customer());
    }

    /**
     * Método de ordenación segun el balance de las cuentas
     * Ordenación siguiendo la interfaz estatica de Comparator
     */

    public void sortByBalance() {
        Collections.sort(customers, Customer.compareByBalance);
    }

}

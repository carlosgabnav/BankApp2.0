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

    private static final long serialVersionUID = -522684836898141431L;
    public List<Customer> customers = new ArrayList<>();

    // Accesores

    /**
     * Añade un Customer
     * @param customer
     */

    public void addCostumer(Customer customer) {

        customers.add(customer);
    }

    /**
     * Este metodo llama a chooseAccount y le asigna el valor que devuelve a acc
     * despues elimina la cuenta correspondiente al indice.
     */

    public void deleteAccount() {
        int acc = chooseAccount();

        try{
            customers.remove(acc);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Invalid account.");
        }


    }

    /**
     *  Devuelve una cuenta en un indice especifico.
     *  @param account
     *  @return
     */

    public Customer getCustomer(int account) {

        return customers.get(account);
    }

    /**
     *  Muestra todos los clientes que hay en la lista.
     */

    public void showCustomers() {
        for (Customer customer : customers) {

            System.out.println(customer.accInfo());
        }
    }


    public ArrayList<Customer> getAllCustomers() {

        return (ArrayList) customers;
    }

    // Metodos


    /**
     *  Metodo que accede a chooseAccount,
     *  elige una cuenta y devuelve la información sobre ella.
     *
     */
    public void movementList() {
        int acc = chooseAccount();

        if (acc >= 0) {
            System.out.println(getCustomer(acc));
        } else {
            System.out.println("That account doesnt exists");
        }
    }

    /**
     * Metodo que busca una cuenta en la lista y la devuelve
     * @return acc
     */
    private int chooseAccount() {
        Scanner sc = new Scanner(System.in);
        int index = 0;
        int acc = 0;
        if (customers.isEmpty()) {
            System.out.println("No customer at the bank.");
            acc = -1;
        } else {
            System.out.println("Accounts: ");
            for (Customer custo : customers) {
                System.out.println(custo.accInfo());
            }
            try {
                System.out.println("Select an account: ");
                index = sc.nextInt() ;
                if (index < 0 || index > customers.size()) {
                    acc = -1;
                    System.out.println("Invalid account number.");
                } else {
                    for (Customer customer: customers) {
                        if(customer.getAccount().getAccNumber() == index){

                            acc = customers.indexOf(customer);
                        }


                    }
                    //acc = customers.get(customers.indexOf(index)).getAccount().getAccNumber() ;
                }
            } catch (InputMismatchException e) {
                acc = -1;
                System.out.println("Invalid account number");
            }
        }
        return acc;
    }

    /**
     *  Metodo que accede a chooseAccount, elige una cuenta y pregunta que cantidad quieres sacar
     *  con la cantidad accede al metodo withdraw de la clase Account y lo ejecuta.
     */

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

    /**
     *  Metodo que accede a chooseAccount, elige una cuenta y pregunta que cantidad quieres sacar
     *  con la cantidad accede al metodo withdraw de la clase Account y lo ejecuta.
     */

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
        String firstName, lastName, accType;
        double initialDeposit = 0;

        boolean validType = false;
        while (!validType) {

            /**
             *  Asegura que la cuenta es "savings" o "checking"
             */
            validType = false;
            do {
                System.out.println("Please enter an account type (checking/savings): ");
                accType = sc.nextLine();
                if (accType.equalsIgnoreCase("checking") || accType.equalsIgnoreCase("savings")) {
                    validType = true;
                } else {
                    System.out.println("Invalid account type, please enter \"checking\" or \"savings\"");
                }

            } while (! validType);


            do{
                System.out.println("Enter your first name: ");
                firstName = sc.nextLine().trim().replaceAll("\\s+", " ");
            }while (firstName.equals(""));


            do{
                System.out.println("Great! Enter your last names: ");
                lastName = sc.nextLine().trim().replaceAll("\\s+", " ");

            }while (lastName.equals(""));


            /**
             *  Aseguramos que la cantidad introducida es un numero
             *  y que cumple los requisitos para cada cuenta
             */
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

            /**
             *  Tenemos todos los datos y podemos crear una cuenta.
             */

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

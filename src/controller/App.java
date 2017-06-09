package controller;

import model.*;

import java.io.*;
import java.util.*;

/**
 * Created by takk on 05/06/2017.
 */
public class App {

    private Bank bank;
    private Customer customer;

    public App() {
        this.bank = new Bank();
    }

    public void startApp() {
        int option;
        readFile();
        showHeader();
        while ((option = showMenu()) != 0) {
            switch (option) {
                case 1:
                    bank.newAccount();
                    break;
                case 2:
                    bank.deleteAccount();
                    break;
                case 3:
                    bank.makeDeposit();
                    break;
                case 4:
                    bank.makeWithdraw();
                    break;
                case 5:
                    break;
                case 6:
                    bank.movementList();
                    break;
                case 7:
                    bank.sortByName();
                    break;
                case 8:
                    bank.sortByAccNumber();
                    break;
                case 9:
                    bank.sortByBalance();
                    break;
                case 10:
                    bank.showCustomers();
                    break;
                case 11:
                    saveFile();
                    readFile();
                    break;
            }

        }
    }


    public void showHeader() {

        System.out.println("+------------------------------+");
        System.out.println("       Welcome to the Bank      ");
        System.out.println("                                ");
        System.out.println("                                ");
        System.out.println("+------------------------------+");

    }

    public int showMenu() {

        System.out.println("1) Open a new bank account.");
        System.out.println("2) Close a bank account.");
        System.out.println("3) Make a deposit.");
        System.out.println("4) Withdraw.");
        System.out.println("5) Print short account information");
        System.out.println("6) Print the detailed account information including last transactions");
        System.out.println("7) Sort customers by name");
        System.out.println("8) Sort customers by Account number");
        System.out.println("9) Sort customers by balance");
        System.out.println("10) Show all the customers");
        System.out.println("11) Save all changes");
        System.out.println("0) Quit");

        Scanner sc = new Scanner(System.in);
        int option = -1;
        try {
            System.out.println("Option: ");
            option = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Sorry, We don't recognize your input.");
        }
        return option;

    }

    public void readFile() {

        try {
            ObjectInputStream flujoLectura = new ObjectInputStream(new FileInputStream("banco.dat"));

            bank.customers = (List<Customer>) flujoLectura.readObject();
            flujoLectura.close();
            for (Customer customer : bank.customers) {
                System.out.println(customer);
            }

        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void saveFile() {
        try {
            ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream("banco.dat"));
            flujoSalida.writeObject(bank.customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




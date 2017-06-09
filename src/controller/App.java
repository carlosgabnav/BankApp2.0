package controller;

import model.*;

import java.io.*;
import java.util.*;

public class App {

    private Bank bank;

    public App() {
        this.bank = new Bank();
    }

    /**
     * Inicia la App y muestra el menu.
     */
    public void startApp() {
        int option;
        //loadAccs();
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
                    bank.movementList();
                    break;
                case 6:
                    bank.sortByName();
                    break;
                case 7:
                    bank.sortByAccNumber();
                    break;
                case 8:
                    bank.sortByBalance();
                    break;
                case 9:
                    bank.showCustomers();
                    break;
                case 10:
                    saveFile();
                    readFile();
                    break;
            }

        }

    }

    /**
     *  Carga cuentas por defecto
     */
//    public void loadAccs(){
//
//
//        bank.addCostumer(new Customer("Ana","Mier Dec illa",new Checking(300)));
//        bank.addCostumer(new Customer("Aitor","Tilla",new Checking(400)));
//        bank.addCostumer(new Customer("Jose Luis","Lamata Feliz",new Savings(150)));
//
//    }

    /**
     *  Imprime la cabecera del menu,
     */

    public void showHeader() {

        System.out.println(" __     __     ____                 _    ");
        System.out.println(" \\ \\   / /    |  _ \\               | |   ");
        System.out.println("  \\ \\_/ /___  | |_) |  __ _  _ __  | | __");
        System.out.println("   \\   // _ \\ |  _ <  / _` || '_ \\ | |/ /");
        System.out.println("    | || (_) || |_) || (_| || | | ||   < ");
        System.out.println("    |_| \\___/ |____/  \\__,_||_| |_||_|\\_\\");
        System.out.println("_____________________________________________");
        System.out.println();


    }

    /**
     *  Imprime el menu y devuelve la opcion elegida.
     * @return int
     */

    public int showMenu() {
        System.out.println();
        System.out.println("1   -   Open a new bank account.");
        System.out.println("2   -   Close a bank account.");
        System.out.println("3   -   Make a deposit.");
        System.out.println("4   -   Withdraw.");
        System.out.println("5   -   Print short account information");
        System.out.println("6   -   Sort customers by name");
        System.out.println("7   -   Sort customers by Account number");
        System.out.println("8   -   Sort customers by balance");
        System.out.println("9   -   Show all the customers");
        System.out.println("10  -   Save all changes");
        System.out.println("0   -   Quit");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        int option = -1;
        try {
            System.out.print("Option: ");
            option = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Sorry, We don't recognize your input.");
        }
        return option;

    }

    /**
     *
     *  Leer un archivo binario y lo muestra
     */

    public void readFile() {

        try {
            ObjectInputStream flujoLectura = new ObjectInputStream(new FileInputStream("Datos/banco.dat"));

            bank.customers = (List<Customer>) flujoLectura.readObject();
            flujoLectura.close();


        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     *  Guarda un archivo binario con el nombre "banco.dat"
     */
    public void saveFile() {
        try {
            ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream("Datos/banco.dat"));
            flujoSalida.writeObject(bank.customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Controller controller = Controller.Singleton();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();




        ArrayList<Client> clients = controller.getClients();
        ArrayList<BankAccount> bankAccounts = controller.getBankAccounts();
        ArrayList<Card> cards = controller.getCards();
        ArrayList<AppAccount> appAccounts = controller.getAppAccounts();
        ArrayList<CreditCard> creditCards = controller.getCreditCards();
        ArrayList<SavingAccount> savingAccounts = controller.getSavingAccounts();
        ArrayList<Transaction> transactions = controller.getTransactions();


        LoadDataFromFiles loadDataFromFiles = new LoadDataFromFiles();
        loadDataFromFiles.loadData(controller);


        controller.setTransactions(transactions);
        controller.setBankAccounts(bankAccounts);
        controller.setCards(cards);
        controller.setClients(clients);
        controller.setAppAccounts(appAccounts);
        controller.setSavingAccounts(savingAccounts);
        controller.setCreditCards(creditCards);

        int option;
        do {
            System.out.println("Choose an option");
            System.out.println();
            System.out.println("Option 1: Connect to your account");
            System.out.println("Option 2: Enter your card");
            System.out.println("Option 3: Connect as manager");
            System.out.println("Option 4: Exit");
            System.out.println();

            Scanner scan = new Scanner(System.in);
            option = scan.nextInt();
            if (option == 1) {
                AppAccountServices appAccountServices = new AppAccountServices();
                appAccountServices.firstOption(controller);
            } else if (option == 2) {
                CardServices cardServices = new CardServices();
                cardServices.secondOption(controller);
            }
            else if(option == 3){
                ManagerServices managerServices = new ManagerServices();
                managerServices.thirdOption(controller);
            }


        } while (option != 4);

        //Sortez clientii dupa nume
        clients.sort(Comparator.comparing(Client::getLastName));

        LoadDataToFiles loadDataToFiles = new LoadDataToFiles();
        loadDataToFiles.loadDataToFile(controller);

    }


}

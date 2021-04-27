package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Controller controller = Controller.Singleton();

        ArrayList<Client> clients = controller.getClients();
        ArrayList<BankAccount> bankAccounts = controller.getBankAccounts();
        ArrayList<Card> cards = controller.getCards();
        ArrayList<AppAccount> appAccounts = controller.getAppAccounts();
        ArrayList<CreditCard> creditCards = controller.getCreditCards();
        ArrayList<SavingAccount> savingAccounts = controller.getSavingAccounts();
        ArrayList<Transaction> transactions = controller.getTransactions();


        ReadFromFile readFromFile = new ReadFromFile();

        readFromFile.readClients("src/com/company/clients.csv", controller);
        controller.displayClients(controller.getClients());

        readFromFile.readAppAccounts("src/com/company/appAccounts.csv", controller);
        controller.displayAppAccounts(appAccounts);

        readFromFile.readBankAccounts("src/com/company/bankAccounts.csv", controller);
        readFromFile.readSavingAccounts("src/com/company/savAcc.csv", controller);
        controller.displayBankAccounts(bankAccounts);
        controller.displaySavingAccounts(savingAccounts);

        readFromFile.readCreditCards("src/com/company/creditCards.csv", controller);
        controller.displayCreditCards(creditCards);
        controller.displayBankAccounts(bankAccounts);
        controller.displaySavingAccounts(savingAccounts);

        readFromFile.readDebitCards("src/com/company/debitCards.csv", controller);
        controller.displayDebitCards(controller.debitCards);

        readFromFile.readTransactions("src/com/company/transactions.csv", controller);


        controller.displayClients(clients);


        controller.setTransactions(transactions);
        controller.setBankAccounts(bankAccounts);
        controller.setCards(cards);
        controller.setClients(clients);
        controller.setAppAccounts(appAccounts);
        controller.setSavingAccounts(savingAccounts);
        controller.setCreditCards(creditCards);

        controller.sort(clients);
        WriteToFiles writeToFile = new WriteToFiles();


        int option;
        do {
            System.out.println("Choose an option");
            System.out.println();
            System.out.println("Option 1: Connect to your account");
            System.out.println("Option 2: Enter your card");
            System.out.println("Option 3: Connect as manager");
            System.out.println("Option 4: Exit");

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


        try {
            FileWriter writeToClients = new FileWriter("src/com/company/clients.csv", false);
            FileWriter writeToAppAccounts = new FileWriter("src/com/company/appAccounts.csv", false);
            FileWriter writeToBankAccounts = new FileWriter("src/com/company/bankAccounts.csv", false);
            FileWriter writeToSavingAccounts = new FileWriter("src/com/company/savAcc.csv", false);
            FileWriter writeToCreditCards = new FileWriter("src/com/company/creditCards.csv", false);
            FileWriter writeToDebitCards = new FileWriter("src/com/company/debitCards.csv", false);
            FileWriter writeToTransactions = new FileWriter("src/com/company/transactions.csv", false);
            writeToCreditCards.close();
            writeToAppAccounts.close();
            writeToClients.close();
            writeToBankAccounts.close();
            writeToSavingAccounts.close();
            writeToDebitCards.close();
            writeToTransactions.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        clients.sort(Comparator.comparing(Client::getLastName));

        writeToFile.writeToClients(clients);
        writeToFile.writeToAppAccounts(appAccounts, controller);
        writeToFile.writeToBankAccounts(bankAccounts, controller);
        writeToFile.writeToSavingAccounts(savingAccounts, controller);
        writeToFile.writeToCreditCards(creditCards, controller);
        writeToFile.writeToDebitCards(controller.debitCards, controller);
        writeToFile.writeToTransactions(transactions, controller);


    }


}

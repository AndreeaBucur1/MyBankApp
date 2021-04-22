package com.company;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();

        ArrayList<Client> clients = controller.getClients();
        ArrayList<BankAccount> bankAccounts = controller.getBankAccounts();
        ArrayList<Card> cards = controller.getCards();
        ArrayList<AppAccount> appAccounts = controller.getAppAccounts();
        ArrayList<CreditCard> creditCards = controller.getCreditCards();
        ArrayList<SavingAccount> savingAccounts = controller.getSavingAccounts();
        ArrayList<Transaction> transactions = controller.getTransactions();



//        controller.addFunds(2, 1000);
//        controller.withdraw(2, 100);
//

        ReadFromFile readFromFile = new ReadFromFile();

        readFromFile.readClients("src/com/company/clients.csv",controller);
        controller.displayClients(controller.getClients());

        readFromFile.readAppAccounts("src/com/company/appAccounts.csv",controller);
        controller.displayAppAccounts(appAccounts);

//        readFromFile.readBankAccounts("src/com/company/bankAccounts.csv",controller);
        controller.displayBankAccounts(bankAccounts);

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


        }while(option != 4);


        try {
            FileWriter writeToClients = new FileWriter("src/com/company/clients.csv",false);
            FileWriter writeToAppAccounts = new FileWriter("src/com/company/appAccounts.csv",false);
            FileWriter writeToBankAccounts = new FileWriter("src/com/company/bankAccounts.csv",false);

            writeToAppAccounts.close();
            writeToClients.close();
            writeToBankAccounts.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeToFile.writeToClients(clients);
        writeToFile.writeToAppAccounts(appAccounts,controller);
//        writeToFile.writeToBankAccounts(bankAccounts,controller);



    }


}

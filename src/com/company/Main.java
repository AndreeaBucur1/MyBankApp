package com.company;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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


        controller.addClient("Lucy", "King", "0761714909", "lucyking@yahoo.com", 123456789);
        controller.addClient("Tom", "Smith", "0768952147", "tomsmith@yahoo.com", 123456798);
        controller.addClient("Olivia", "Green", "0789652475", "oliviagreen@yahoo.com", 123456879);
        controller.addBankAccount(1);
        controller.addBankAccount(3);
        controller.addSavingAccount(1);
        controller.addCreditCard(2);
        controller.addDebitCard(2);
        controller.addDebitCard(1);
        controller.addFunds(2, 1000);
        controller.withdraw(2, 100);
//        controller.withdraw(1, 1000);
        controller.transfer(2, 1, 100);
        controller.addAppAccount(1);
        controller.displayAppAccounts(appAccounts);

        controller.setTransactions(transactions);
        controller.setBankAccounts(bankAccounts);
        controller.setCards(cards);
        controller.setClients(clients);
        controller.setAppAccounts(appAccounts);
        controller.setSavingAccounts(savingAccounts);
        controller.setCreditCards(creditCards);

//
//
//        controller.displayBankAccounts(bankAccounts);
//        controller.displayCards(cards);
//
//        //Sort clients by last name
//        controller.sort(clients);
//        controller.displayClients(clients);
//
//        controller.displayTransactions(transactions);
//        controller.displayAppAccounts(appAccounts);
        System.out.println("Choose an option");
        System.out.println();
        System.out.println("Option 1: Connect to your account");
        System.out.println("Option 2: Enter your card");
        System.out.println("Option 3: Connect as manager");
        System.out.println("Option 4: Exit");

        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        if(option == 1){
            AppAccountServices appAccountServices = new AppAccountServices();
            appAccountServices.firstOption(controller);
        }
        else if(option == 2){
            CardServices cardServices = new CardServices();
            cardServices.secondOption(controller);
        }


    }

}

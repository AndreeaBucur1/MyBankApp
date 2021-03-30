package com.company;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.addClient("Lucy","King","0761714909","lucyking@yahoo.com",123456789);
        controller.addClient("Tom","Smith","0768952147","tomsmith@yahoo.com",123456798);


        ArrayList<Client> clients = controller.getClients();
        ArrayList<BankAccount> bankAccounts = controller.getBankAccounts();
        ArrayList<Card> cards = controller.getCards();
        ArrayList<AppAccount> appAccounts = controller.getAppAccounts();
        ArrayList<CreditCard> creditCards = controller.getCreditCards();
        ArrayList<SavingAccount> savingAccounts = controller.getSavingAccounts();


        controller.addBankAccount( 1);
        controller.addClient("Olivia","Green","0789652475","oliviagreen@yahoo.com",123456879);
        controller.addBankAccount( 3);
        controller.addSavingAccount(1);
        controller.addCreditCard(2);
        controller.addCard(2);
        controller.addDebitCard(1);
        controller.addFunds(2,1000);


        System.out.println();
        System.out.println("Banck Accounts:");
        for(BankAccount bankAccount : bankAccounts){
            System.out.println(bankAccount);
        }


        System.out.println();
        System.out.println("Cards: ");
        for(Card card : cards){
            System.out.println(card);
        }

        System.out.println();

         //Sorted arraylist

        controller.sort(clients);

        System.out.println("Clients:");
        for(Client client : clients){
            System.out.println(client);
        }
        System.out.println();
        System.out.println("App Accounts: ");
        controller.addAppAccount(1);
        for(AppAccount appAccount : appAccounts){
            System.out.println(appAccount);
        }
        System.out.println();
        controller.addAppAccount(1);





    }

}

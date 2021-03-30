package com.company;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

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


        controller.addClient("Lucy","King","0761714909","lucyking@yahoo.com",123456789);
        controller.addClient("Tom","Smith","0768952147","tomsmith@yahoo.com",123456798);
        controller.addClient("Olivia","Green","0789652475","oliviagreen@yahoo.com",123456879);
        controller.addBankAccount( 1);
        controller.addBankAccount( 3);
        controller.addSavingAccount(1);
        controller.addCreditCard(2);
        controller.addCard(2);
        controller.addDebitCard(1);
        controller.addFunds(2,1000);
        controller.withdraw(2,100);
        controller.withdraw(1,1000);
        controller.transfer(2,1,100);
        controller.addAppAccount(1);



        controller.displayBankAccounts(bankAccounts);
        controller.displayCards(cards);

        //Sort clients by last name
        controller.sort(clients);
        controller.displayClients(clients);

        controller.displayTransactions(transactions);
        controller.displayAppAccounts(appAccounts);

        String msg1 = new String();
        String msg2 = new String();
        msg1 = getMsg1("msg1");
        msg2 = getMsg1("msg2");

    }

    private static String getMsg1(String msg1) {
        return msg1 + "5";
    }

}

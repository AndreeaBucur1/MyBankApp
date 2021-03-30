package com.company;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.addClient("Lucy","King","0761714909","lucyking@yahoo.com");

        controller.addClient("Tom","Smith","0768952147","tomsmith@yahoo.com");


        ArrayList<Client> clients = controller.getClients();
        ArrayList<BankAccount> bankAccounts = controller.getBankAccounts();
        ArrayList<Card> cards = controller.getCards();


        controller.addBankAccount("ROMBA123456789", 1);
        controller.addBankAccount("ROMBA124356789", 1);
        controller.addClient("Olivia","Green","0789652475","oliviagreen@yahoo.com");
        controller.addBankAccount("ROMBA789523325", 3);


        System.out.println();
        System.out.println("Banck Accounts:");
        for(BankAccount bankAccount : bankAccounts){
            System.out.println(bankAccount);
        }

        controller.addCard(3);
        System.out.println();
        System.out.println("Cards: ");
        for(Card card : cards){
            System.out.println(card);
        }

        System.out.println();

        System.out.println("Clients:");
        for(Client client : clients){
            System.out.println(client);
        }

        Client client = controller.findClient(3);
        System.out.println(client.getBankAccounts());
        for (BankAccount bankAccount : client.getBankAccounts()){
            System.out.println(bankAccount.getCards());
        }

    }

}

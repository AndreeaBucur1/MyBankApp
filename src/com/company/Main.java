package com.company;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.addClient("Bucur","Andreea","0761714909","andreeabucur@yahoo.com");

        controller.addClient("Constantin","Diana","0768952147","dianaconstantin@yahoo.com");


        ArrayList<Client> clients = controller.getClients();
        ArrayList<BankAccount> bankAccounts = controller.getBankAccounts();



        controller.addBankAccount("ROMBA123456789", 1);
        controller.addBankAccount("ROMBA124356789", 1);
        controller.addClient("Albu","Mihail","0789652475","mihailalbu@yahoo.com");
        for(Client client : clients){
            System.out.println(client);
        }
        Client client = controller.findClient(1);
        System.out.println(client);
        System.out.println(bankAccounts);
    }
}

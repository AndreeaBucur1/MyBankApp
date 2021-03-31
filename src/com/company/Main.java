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
        controller.addCard(2);
        controller.addDebitCard(1);
        controller.addFunds(2, 1000);
        controller.withdraw(2, 100);
//        controller.withdraw(1, 1000);
        controller.transfer(2, 1, 100);
        controller.addAppAccount(1);
        controller.displayAppAccounts(appAccounts);

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
            System.out.println("Enter your access token: ");
            int accessToken = scan.nextInt();
            System.out.println("Enter your password: ");
            String password = scan.next();
            AppAccount appAccount = controller.findAppAccountByAccessToken(accessToken);
            if(appAccount != null){
                if(appAccount.getPassword().compareTo(password) != 0){
                    System.out.println("Wrong password");
                    System.exit(0);
                }
                else{
                    Client client = controller.findClientByAppAccountId(appAccount.getAppAccountId());
                    System.out.println("Choose an option:");
                    System.out.println();
                    System.out.println("Option 1: Check balance");
                    System.out.println("Option 2: Transfer money");
                    System.out.println("Option 3: Exit");

                    int newOption = scan.nextInt();
                    if(newOption == 1){
                        ArrayList<BankAccount> yourBankAccounts = client.getBankAccounts();
                        for(BankAccount bankAccount : yourBankAccounts){
                            System.out.println("Bank account: " + bankAccount.getIBAN());
                            System.out.println("Balance: " + bankAccount.getBalance());
                            System.out.println();
                        }

                    }
                }
            }
            else{
                System.out.println("Wrong access token");
            }
//            AppAccountServices appAccountServices = new AppAccountServices();
//            appAccountServices.firstOption(controller.appAccounts,controller.clients);

        }



    }

}

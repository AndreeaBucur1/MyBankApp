package com.company;
import java.util.ArrayList;
import java.util.Scanner;

public class AppAccountServices {
    public void firstOption(ArrayList<AppAccount> appAccounts,ArrayList<Client> clients){

        Controller controller = new Controller();
        controller.setAppAccounts(appAccounts);
        controller.setClients(clients);
        System.out.println(appAccounts);
        System.out.println("Enter your access token: ");
        Scanner scan = new Scanner(System.in);
        int accessToken = scan.nextInt();
        System.out.println("Enter your password: ");
        String password = scan.next();
//        System.out.println(controller.appAccounts);
        AppAccount appAccount = controller.findAppAccountByAccessToken(accessToken);
        if (appAccount != null) {
            if (appAccount.getPassword().compareTo(password) != 0) {
                System.out.println("Wrong password");
                System.exit(0);
            } else {
                Client client = controller.findClientByAppAccountId(appAccount.getAppAccountId());
                System.out.println(client);
                System.out.println("Choose an option:");
                System.out.println();
                System.out.println("Option 1: Check balance");
                System.out.println("Option 2: Transfer money");
                System.out.println("Option 3: Exit");

                int newOption = scan.nextInt();
                if (newOption == 1) {
                    ArrayList<BankAccount> yourBankAccounts = client.getBankAccounts();
                    for (BankAccount bankAccount : yourBankAccounts) {
                        System.out.println("Bank account: " + bankAccount.getIBAN());
                        System.out.println("Balance: " + bankAccount.getBalance());
                        System.out.println();
                    }

                }
            }
        }
        else {
            System.out.println("Wrong access token");
        }
    }
}

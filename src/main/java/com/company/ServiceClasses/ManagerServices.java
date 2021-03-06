package com.company.ServiceClasses;

import com.company.*;
import com.company.BankAccounts.BankAccount;
import com.company.Cards.CreditCard;
import com.company.Cards.DebitCard;
import com.company.Client.AppAccount;
import com.company.Client.Client;
import com.company.Database.DatabaseConnection;
import com.company.Database.GetFromDatabase;

import java.sql.SQLException;
import java.util.Scanner;

public class ManagerServices {
    public void thirdOption(Controller controller){
        WriteToFiles writeToFiles = new WriteToFiles();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        DeleteFromDatabase deleteFromDatabase = new DeleteFromDatabase();
        boolean okPass = false;
        do{
            System.out.print("Enter your access token: ");
            Scanner scanner = new Scanner(System.in);
            int accessToken = scanner.nextInt();
            if(accessToken == 1){
                System.out.print("Enter your password: ");
                String password = scanner.next();
                if(password.compareTo("manager") != 0){
                    System.out.println("Wrong password");
                    AppAccount managerAccount = new AppAccount(accessToken,password);

                }
                    else {
                        okPass = true;
                        int option;
                        do {


                            System.out.println();
                            System.out.println("Option 1: Add client");
                            System.out.println("Option 2: Add app account for a client");
                            System.out.println("Option 3: Add bank account");
                            System.out.println("Option 4: Add card");
                            System.out.println("Option 5: Display all data");
                            System.out.println("Option 6: Delete credit card");
                            System.out.println("Option 7: Delete debit card");
                            System.out.println("Option 8: Delete bank account");
                            System.out.println("Option 9: Delete client");
                            System.out.println("Option 10: Delete app account");
                            System.out.println("Option 11: Exit");
                            System.out.println();
                            System.out.print("Choose an option: ");

                            option = scanner.nextInt();
                            if (option > 11) {
                                System.out.print("Choose a valid option: ");
                                System.out.println();
                            } else if (option == 1) {
                               controller.addClient();
                            } else if (option == 2) {
                                System.out.println("This are all the clients:");
                                controller.displayClients();
                                System.out.print("Enter the id of the client you want to add app account: ");
                                int clientId = scanner.nextInt();
                                try {
                                    controller.addAppAccount(clientId);
                                    writeToFiles.writeToAudit("Add app account");
                                } catch (MyException e) {

                                }

                            } else if (option == 3) {
                                System.out.println("This are all the clients:");
                                controller.displayClients();
                                System.out.print("Enter the id of the client you want to add the bank account to: ");
                                int clientId = scanner.nextInt();
                                int newOption;
                                do {
                                    System.out.println("Choose an option: ");
                                    System.out.println("1: Bank account");
                                    System.out.println("2: Saving account");
                                    System.out.println("3: Abort");
                                    System.out.println();
                                    newOption = scanner.nextInt();
                                    if (newOption == 1) {
                                        try {
                                            controller.addBankAccount(clientId);
                                            writeToFiles.writeToAudit("Add bank account");
                                        } catch (MyException e) {
                                            e.printStackTrace();
                                        }
                                    } else if (newOption == 2) {
                                        try {
                                            controller.addSavingAccount(clientId);
                                            writeToFiles.writeToAudit("Add saving account");
                                        } catch (MyException e) {
                                            e.printStackTrace();
                                        }
                                    } else if (newOption == 3) {
                                        System.out.println("Canceled.");
                                    } else {
                                        System.out.print("Enter a valid option: ");
                                    }
                                } while (!(newOption == 1 || newOption == 2 || newOption == 3));
                            }
                            else if(option == 4){
                                controller.displayClients();
                                System.out.print("Enter the id of the client: ");
                                int clientId = scanner.nextInt();
                                Client client = null;
                                try {
                                    client = controller.findClientById(clientId);
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                                if(client != null) {
                                    System.out.println("This are the bank accounts of this client:");
                                    try {
                                        client.setBankAccounts(getFromDatabase.getClientsBankAccounts(client.getClientId()));
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                    int bankAccountNr = 0;
                                    try {
                                        for (BankAccount bankAccount : getFromDatabase.getClientsBankAccounts(client.getClientId())) {
                                            System.out.println(++bankAccountNr + " " + bankAccount);
                                        }
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                    boolean validBankAccountId = false;
                                    int bankAccId;
                                    do {
                                        System.out.println("Enter the id of the bank account you want to add the card to:");
                                        System.out.print("Enter -1 to abort: ");
                                        bankAccId = scanner.nextInt();
                                        if (bankAccId <= client.getBankAccounts().size())
                                            validBankAccountId = true;
                                    } while (!validBankAccountId);
                                    if (bankAccId == -1) {
                                        System.out.println("Canceled");
                                    } else {
                                        if (bankAccId <= client.getBankAccounts().size()) {
                                            BankAccount bankAccount = client.getBankAccounts().get(bankAccId - 1);
                                            boolean validOption = false;
                                            System.out.println("Choose an option: ");
                                            do {

                                                System.out.println();
                                                System.out.println("1: Credit card");
                                                System.out.println("2: Debit card");
                                                System.out.println("3: Abort");
                                                System.out.println();
                                                int op = scanner.nextInt();
                                                if (op == 3) {
                                                    System.out.println("Canceled");
                                                    validOption = true;
                                                } else if (op == 1) {
                                                    try {
                                                        controller.addCreditCard(bankAccount.getBankAccountId());
                                                        writeToFiles.writeToAudit("Add credit card");
                                                        validOption = true;
                                                    } catch (MyException e) {
                                                        e.printStackTrace();
                                                    }
                                                } else if (op == 2) {
                                                    try {
                                                        controller.addDebitCard(bankAccount.getBankAccountId());
                                                        writeToFiles.writeToAudit("Add debit card");
                                                        validOption = true;
                                                    } catch (MyException e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    System.out.print("Choose a valid option: ");
                                                }
                                            } while (!validOption);

                                        }
                                    }
                                }
                                else {
                                    System.out.println("This id is not valid.");
                                }

                            }
                            else if(option == 5){

                                boolean validOption = false;
                                do {
                                    System.out.println();
                                    System.out.println("1: Display clients");
                                    System.out.println("2: Display bank accounts");
                                    System.out.println("3: Display saving accounts");
                                    System.out.println("4: Display credit cards");
                                    System.out.println("5: Display debit cards");
                                    System.out.println("6: Display account statements");
                                    System.out.println("7: Display transactions");
                                    System.out.println("8: Display app accounts");
                                    System.out.println("9: Cancel");
                                    System.out.println();
                                    System.out.print("Choose an option: ");
                                    int newOption = scanner.nextInt();
                                    if(newOption == 1){
                                        controller.displayClients();
                                        validOption = true;
                                    }
                                    else if(newOption == 2){
                                        controller.displayBankAccounts();
                                        validOption = true;
                                    }
                                    else if(newOption == 3){
                                        try {
                                            controller.displaySavingAccounts();
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }
                                        validOption = true;
                                    }
                                    else if(newOption == 4){
                                        controller.displayCreditCards();
                                        validOption = true;
                                    }
                                    else if(newOption == 5){
                                        controller.displayDebitCards();
                                        validOption = true;
                                    }
                                    else if(newOption == 6){
                                        validOption = true;
                                    }
                                    else if(newOption == 7){
                                        controller.displayTransactions(controller.getTransactions());
                                        validOption = true;
                                    }
                                    else if(newOption == 8){
                                        controller.displayAppAccounts();
                                        validOption = true;
                                    }
                                    else if(newOption == 9){
                                        System.out.println("Canceled");
                                        validOption = true;
                                    }
                                    else {
                                        System.out.print("Choose a valid option: ");
                                    }
                                }while (!validOption);

                            }

                            else if(option == 6) {
                                controller.displayCreditCards();
                                System.out.print("Enter the number of the credit card you want to delete: ");
                                Long creditCardNumber = scanner.nextLong();
                                if (controller.findCardByNumber(creditCardNumber) != null) {
                                    if(controller.findCardByNumber(creditCardNumber) instanceof CreditCard) {
                                        try {
                                            CreditCard creditCard = (CreditCard) controller.findCardByNumber(creditCardNumber);
                                            deleteFromDatabase.deleteCreditCard(creditCard);
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }
                                    }
                                    else{
                                        System.out.println("You are trying to delete a debit card.");
                                    }
                                }
                            }

                            else if(option == 7){
                                controller.displayDebitCards();
                                System.out.print("Enter the number of the debit card you want to delete: ");
                                Long debitCardNumber = scanner.nextLong();
                                if(controller.findCardByNumber(debitCardNumber) != null){
                                    if(controller.findCardByNumber(debitCardNumber) instanceof DebitCard){
                                        try {
                                            DebitCard debitCard = (DebitCard) controller.findCardByNumber(debitCardNumber);
                                            deleteFromDatabase.deleteDebitCard(debitCard);
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }
                                    }
                                    else {
                                        System.out.println("You are trying to delete a credit card.");
                                    }
                                }
                            }

                            else if(option == 8){
                                System.out.print("Enter the id of the bank account you want to delete");
                                int bankAccountId = scanner.nextInt();
                                try {
                                    deleteFromDatabase.deleteBankAccount(controller.findBankAccountById(bankAccountId));
                                    System.out.println("Account deleted");
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }


                    } while (option != 11);

                    }
                }


            else {
                System.out.println("Wrong access token");
            }


        }while(!okPass);

    }
}

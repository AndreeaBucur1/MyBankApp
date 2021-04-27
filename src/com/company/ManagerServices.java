package com.company;

import java.util.Scanner;

public class ManagerServices {
    public void thirdOption(Controller controller){
        WriteToFiles writeToFiles = new WriteToFiles();
        boolean okPass = false;
        do{
            System.out.println("Enter your access token");
            Scanner scanner = new Scanner(System.in);
            int accessToken = scanner.nextInt();
            if(controller.findAppAccountByAccessToken(accessToken) != null){
                AppAccount managerAccount = controller.findAppAccountByAccessToken(accessToken);
                System.out.println(managerAccount);
                System.out.println("Enter your password");
                String password = scanner.next();
                if(managerAccount.getPassword().compareTo(password) != 0){
                    System.out.println("Wrong password");
                }
                else if(managerAccount.getPassword().compareTo(password) == 0) {
                    if (managerAccount.getAppAccountId() != 0) {
                        System.out.println("This account does not belong to the manager");
                    } else {
                        okPass = true;
                        int option;
                        do {
                            System.out.println("Choose an option");
                            System.out.println();
                            System.out.println("Option 1: Add client");
                            System.out.println("Option 2: Add app account for a client");
                            System.out.println("Option 3: Add bank account");
                            System.out.println("Option 4: Add card");
                            System.out.println("Option 5: Display all data");
                            System.out.println("Option 6: Exit");
                            System.out.println();

                            option = scanner.nextInt();
                            if (option > 6) {
                                System.out.println("Choose a valid option");
                                System.out.println();
                            } else if (option == 1) {
                                System.out.print("First name: ");
                                String firstName = scanner.next();
                                System.out.print("Last name: ");
                                String lastName = scanner.next();
                                System.out.print("Phone number: ");
                                String phoneNumber = scanner.next();
                                System.out.print("Email: ");
                                String email = scanner.next();
                                System.out.print("PNC: ");
                                Long pnc = scanner.nextLong();
                                Client client = new Client(firstName, lastName, phoneNumber, email, pnc);
                                controller.getClients().add(client);
                                writeToFiles.writeToAudit("Add client");
                            } else if (option == 2) {
                                System.out.println("This are all the clients:");
                                controller.displayClients(controller.getClients());
                                System.out.println("Enter the id of the client you want to add app account");
                                int clientId = scanner.nextInt();
                                try {
                                    controller.addAppAccount(clientId);
                                } catch (MyException e) {
                                    e.printStackTrace();
                                }
                                writeToFiles.writeToAudit("Add app account");
                            } else if (option == 3) {
                                System.out.println("This are all the clients:");
                                controller.displayClients(controller.getClients());
                                System.out.println("Enter the id of the client you want to add the bank account to: ");
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
                                        System.out.println("Enter a valid option.");
                                    }
                                } while (!(newOption == 1 || newOption == 2 || newOption == 3));
                            }
                            else if(option == 4){
                                controller.displayClients(controller.getClients());
                                System.out.println("Enter the id of the client: ");
                                int clientId = scanner.nextInt();
                                Client client = controller.findClient(clientId);
                                System.out.println("This are the bank accounts of this client:");
                                int bankAccountNr = 0;
                                for(BankAccount bankAccount : client.getBankAccounts()){
                                    System.out.println(++bankAccountNr + " " + bankAccount);
                                }
                                System.out.println("Enter the id of the bank account you want to add the card to:");
                                boolean validBankAccountId = false;
                                int bankAccId;
                                do{
                                    System.out.println("Enter the id of the bank account you want to add the card to:");
                                    System.out.println("Enter -1 to abort");
                                    bankAccId = scanner.nextInt();
                                    if(bankAccId <= client.getBankAccounts().size())
                                        validBankAccountId = true;
                                }while(!validBankAccountId);
                                if(bankAccId == -1){
                                    System.out.println("Canceled");
                                }
                                else{
                                    if(bankAccId <= client.getBankAccounts().size()) {
                                        BankAccount bankAccount = client.getBankAccounts().get(bankAccId-1);
                                        boolean validOption = false;
                                        do {
                                            System.out.println("Choose an option:");
                                            System.out.println();
                                            System.out.println("1: Credit card");
                                            System.out.println("2: Debit card");
                                            System.out.println("3: Abort");
                                            System.out.println();
                                            int op = scanner.nextInt();
                                            if (op == 3){
                                                System.out.println("Canceled");
                                                validOption = true;
                                            }
                                            else if(op == 1){
                                                try {
                                                    controller.addCreditCard(bankAccount.getBankAccountId());
                                                    writeToFiles.writeToAudit("Add credit card");
                                                    validOption = true;
                                                } catch (MyException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            else if(op == 2){
                                                try {
                                                    controller.addDebitCard(bankAccount.getBankAccountId());
                                                    writeToFiles.writeToAudit("Add debit card");
                                                    validOption = true;
                                                } catch (MyException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }while (!validOption);

                                    }
                                }

                            }
                            else if(option == 5){
                                boolean validOption = false;
                                controller.displayDebitCards(controller.getDebitCards());
                                controller.displayCreditCards(controller.getCreditCards());
//                                do {
//                                    System.out.println("Choose an option: ");
//                                    System.out.println();
//                                    System.out.println("1: Display clients");
//                                    System.out.println("2: Display bank accounts");
//                                    System.out.println("3: Display saving accounts");
//                                    System.out.println("4: Display credit cards");
//                                    System.out.println("5: Display debit cards");
//                                    System.out.println("6:");
//                                }while (!validOption);

                            }


                    } while (option != 6);

                    }
                }

            }
            else {
                System.out.println("Wrong access token");
            }


        }while(!okPass);

    }
}

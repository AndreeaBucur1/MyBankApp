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
                                    newOption = scanner.nextInt();
                                    if (newOption == 1) {
                                        try {
                                            controller.addBankAccount(clientId);
                                        } catch (MyException e) {
                                            e.printStackTrace();
                                        }
                                    } else if (newOption == 2) {
                                        try {
                                            controller.addSavingAccount(clientId);
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
                            else if(option == 3){
                                System.out.println("Enter the id of the client: ");
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

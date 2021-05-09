package com.company;
import java.util.ArrayList;
import java.util.Scanner;

public class AppAccountServices {
    public void firstOption(Controller controller) {
        WriteToFiles writeToFiles = new WriteToFiles();
        boolean okPass = false;
        do {
            System.out.println("Enter your access token: ");
            Scanner scan = new Scanner(System.in);
            int accessToken = scan.nextInt();
            System.out.println("Enter your password: ");
            String password = scan.next();
            AppAccount appAccount = controller.findAppAccountByAccessToken(accessToken);

            if (appAccount != null && appAccount.getAppAccountId() != 0) {
                if (appAccount.getPassword().compareTo(password) != 0) {
                    System.out.println("Wrong password");
                } else {
                    writeToFiles.writeToAudit("Connection to the app account");
                    okPass = true;

                    Client client = controller.findClientByAppAccountId(appAccount.getAppAccountId());
                    int newOption;
                    do {
                        System.out.println(client);
                        System.out.println("Choose an option:");
                        System.out.println();
                        System.out.println("Option 1: Check balance");
                        System.out.println("Option 2: Transfer money");
                        System.out.println("Option 3: Get an account statement");
                        System.out.println("Option 4: Exit");
                        System.out.println();


                        newOption = scan.nextInt();
                        if (newOption == 1) {
                            ArrayList<BankAccount> clientBankAccounts = client.getBankAccounts();
                            for (BankAccount bankAccount : clientBankAccounts) {
                                System.out.println("Bank account: " + bankAccount.getIBAN());
                                System.out.println("Balance: " + bankAccount.getBalance());
                                System.out.println();
                                writeToFiles.writeToAudit("Balance check");
                            }

                        } else if (newOption == 2) {
                            ArrayList<BankAccount> clientBankAccounts = client.getBankAccounts();
                            int nr = 0;
                            System.out.println("This are your bank accounts:");
                            for (BankAccount bankAccount : clientBankAccounts) {
                                nr++;
                                System.out.println(nr + " " + bankAccount);
                            }
                            System.out.println("Enter the number of the bank account you want to transfer money from");
                            int cardNr = scan.nextInt();
                            if (cardNr > clientBankAccounts.size()) {
                                System.out.println("Invalid option");
                            } else {
                                BankAccount transferMoneyFrom = clientBankAccounts.get(cardNr - 1);
                                System.out.println(transferMoneyFrom);
                                System.out.println("Enter the IBAN of the bank account you want to transfer money to");
                                String iban = scan.next();
                                BankAccount transferMoneyTo = controller.findBankAccountByIban(iban);
                                if (transferMoneyTo == null) {
                                    System.out.println("Wrong IBAN");
                                } else {
                                    System.out.println("Enter the sum you want to send");
                                    float sum = scan.nextFloat();

                                    System.out.println(transferMoneyFrom);
                                    System.out.println(transferMoneyTo);

                                    try {
                                        controller.transfer(transferMoneyFrom.getBankAccountId(), transferMoneyTo.getBankAccountId(), sum);
                                    } catch (MyException e) {
                                        e.printStackTrace();
                                    }
                                    writeToFiles.writeToAudit("Money transfer");


                                }

                            }

                        }
                        else if(newOption == 3) {
                            System.out.println("Enter the number of the month: ");
                            int month = scan.nextInt();
                            System.out.println("This are your bank accounts:");
                            for(BankAccount bankAccount : client.getBankAccounts())
                                System.out.println("1" + bankAccount);
                            System.out.println("Enter the number of the account");
                            int bankAccountNr = scan.nextInt();
                            BankAccount bankAccount = client.getBankAccounts().get(bankAccountNr-1);
                            AccountStatement accountStatement = controller.accountStatement(bankAccount.getBankAccountId(),month);
                            if(accountStatement == null)
                                System.out.println("No transactions this month");
                            else
                                System.out.println(accountStatement);
                            writeToFiles.writeToAudit("Account statement");



                        }
                        else if (newOption == 4) {
                            System.out.println("You signed out of your account");
                        }
                    } while (newOption != 4);
                }
            } else {
                System.out.println("Wrong access token");
            }

        }while(!okPass);
    }
}

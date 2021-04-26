package com.company;

import java.util.Scanner;

public class CardServices {
    public void secondOption(Controller controller) {
        WriteToFiles writeToFiles = new WriteToFiles();
        boolean okCard = false;
        do {
            System.out.println("Enter your card: ");
            Scanner scan = new Scanner(System.in);
            long cardNumber = scan.nextLong();
            Card card = controller.findCardByNumber(cardNumber);

            if (card != null) {
                okCard = true;
                int option;
                do {
                    System.out.println("Choose an option: ");
                    System.out.println("Option 1: Check balance");
                    System.out.println("Option 2: Withdraw money");
                    System.out.println("Option 3: Add funds");
                    System.out.println("Option 4: Make a payment");
                    System.out.println("Option 5: Exit");
                    option = scan.nextInt();
                    if (option == 1) {
//                BankAccount bankAccount = controller.findBankAccount(card.getBankAccountId());
                        System.out.println("Balance: " + controller.checkBalance(card.getBankAccountId()));
                        writeToFiles.writeToAudit("Check Balance");
                    } else if (option == 2) {
                        System.out.println("Enter the sum you want to withdraw: ");
                        int sum = scan.nextInt();
                        BankAccount bankAccount = controller.findBankAccount(card.getBankAccountId());
                        System.out.println(bankAccount);

                        try {
                            controller.withdraw(card.getCardId(), sum);
                        } catch (MyException e) {
                            e.printStackTrace();
                        }

                        writeToFiles.writeToAudit("Money withdrawal");

                    } else if (option == 3) {
                        System.out.println("Enter the sum you want to add to your account");
                        int sum = scan.nextInt();
                        BankAccount bankAccount = controller.findBankAccount(card.getBankAccountId());
                        System.out.println(bankAccount);
                        controller.addFunds(card.getBankAccountId(),sum);
                        System.out.println(bankAccount);
                        writeToFiles.writeToAudit("Add funds");
                    }
                    else  if(option == 4){
                        System.out.println("Enter the sum you want to pay with your card");
                        float sum = scan.nextFloat();
                        BankAccount bankAccount = controller.findBankAccount(card.getBankAccountId());
                        System.out.println(bankAccount);
                        try {
                            controller.payment(card.getBankAccountId(),sum);
                        } catch (MyException e) {
                            e.printStackTrace();
                        }
                        System.out.println(bankAccount);
                        writeToFiles.writeToAudit("Payment");
                    }
                    else if(option == 5){
                        System.out.println("You removed your card");
                        writeToFiles.writeToAudit("Remove card");
                    }

                } while (option != 5);
            }
        } while(okCard == false);
    }
}

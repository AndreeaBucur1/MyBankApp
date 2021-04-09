package com.company;

import java.util.Scanner;

public class CardServices {
    public void secondOption(Controller controller) {
        boolean okCard = false;
        do {
            controller.displayCards(controller.getAllCards());
            System.out.println("Enter your card: ");
            Scanner scan = new Scanner(System.in);
            long cardNumber = scan.nextLong();
            Card card = controller.findCardByNumber(cardNumber);
            System.out.println(card);
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
                    } else if (option == 2) {
                        System.out.println("Enter the sum you want to withdraw: ");
                        int sum = scan.nextInt();
                        BankAccount bankAccount = controller.findBankAccount(card.getBankAccountId());
                        System.out.println(bankAccount);
                        controller.withdraw(card.getCardId(), sum);
                        System.out.println(bankAccount);

                    } else if (option == 3) {
                        System.out.println("Enter the sum you want to add to your account");
                        int sum = scan.nextInt();
                        BankAccount bankAccount = controller.findBankAccount(card.getBankAccountId());
                        System.out.println(bankAccount);
                        controller.addFunds(card.getBankAccountId(),sum);
                        System.out.println(bankAccount);
                    }
                    else  if(option == 4){
                        System.out.println("Enter the sum you want to pay with your card");
                        float sum = scan.nextFloat();
                        BankAccount bankAccount = controller.findBankAccount(card.getBankAccountId());
                        System.out.println(bankAccount);
                        controller.payment(card.getBankAccountId(),sum);
                        System.out.println(bankAccount);
                    }
                    else if(option == 5){
                        System.out.println("You removed your card");
                    }

                } while (option != 5);
            }
        } while(okCard == false);
    }
}

package com.company;

import java.util.Scanner;

public class CardServices {
    public void secondOption(Controller controller){
        System.out.println("Enter your card: ");
        Scanner scan = new Scanner(System.in);
        long cardNumber = scan.nextLong();
        Card card = controller.findCardByNumber(cardNumber);
        controller.displayCards(controller.allCards);
        System.out.println(card);
        if(card != null){
            System.out.println("Choose an option: ");
            System.out.println("Option 1: Check balance");
            System.out.println("Option 2: Withdraw money");
            System.out.println("Option 3: Add funds");
            System.out.println("Option 4: Exit");
            int option = scan.nextInt();
            if (option == 1){
//                BankAccount bankAccount = controller.findBankAccount(card.getBankAccountId());
                System.out.println("Balance: " + controller.checkBalance(card.getBankAccountId()));
            }
            else if(option == 2){
                System.out.println("Enter the sum you want to withdraw: ");
                int sum = scan.nextInt();

            }
        }
    }
}

package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class ReadFromFile {
    public void readClients(String filePath,Controller controller){
        File file = new File(filePath);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (reader.hasNextLine()){
            String data = reader.nextLine();
            String[] tokens = data.split(",");
            if(tokens.length == 5) {
                controller.addClient(tokens[0], tokens[1], tokens[2], tokens[3], Long.valueOf(tokens[4]));
            }
            else{
                Client client = new Client(Integer.valueOf(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4],Integer.valueOf(tokens[5]), Long.valueOf(tokens[6]));
                controller.getClients().add(client);
            }

        }
        reader.close();
    }

    public void readAppAccounts(String filePath,Controller controller){
        File file = new File(filePath);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (reader.hasNextLine()){
            String data = reader.nextLine();
            String[] tokens = data.split(",");
            if(tokens.length == 1) {
                controller.addAppAccount(Integer.valueOf(tokens[0]));

            }
            else{
                AppAccount appAccount = new AppAccount(Integer.valueOf(tokens[0]),Integer.valueOf(tokens[1]), tokens[2]);
                controller.getAppAccounts().add(appAccount);
            }

        }
        reader.close();
    }

    public void readBankAccounts(String filePath, Controller controller){
        File file = new File(filePath);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (reader.hasNextLine()){
            String data = reader.nextLine();
            String[] tokens = data.split(",");
            if(tokens.length == 1) {
                controller.addBankAccount(Integer.valueOf(tokens[0]));
            }
            else{
                BankAccount bankAccount = new BankAccount(Integer.valueOf(tokens[0]),tokens[1],Float.valueOf(tokens[2]), LocalDate.parse(tokens[3]));
                controller.getBankAccounts().add(bankAccount);
                Client client = controller.findClient(Integer.valueOf(tokens[4]));
                client.getBankAccounts().add(bankAccount);
                controller.getAllBankAccounts().add(bankAccount);


            }

        }
        reader.close();
    }


    public void readSavingAccounts(String filePath,Controller controller){
        File file = new File(filePath);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (reader.hasNextLine()){
            String data = reader.nextLine();
            String[] tokens = data.split(",");
            if(tokens.length == 1) {
                controller.addSavingAccount(Integer.valueOf(tokens[0]));
            }
            else{
                SavingAccount savingAccount = new SavingAccount(Integer.valueOf(tokens[0]),tokens[1],Float.valueOf(tokens[2]),LocalDate.parse(tokens[3]),Float.valueOf(tokens[4]));
                controller.getSavingAccounts().add(savingAccount);
                Client client = controller.findClientByBankAccountId(Integer.valueOf(tokens[5]));
                client.getBankAccounts().add(savingAccount);
                controller.getSavingAccounts().add(savingAccount);

            }

        }
        reader.close();
    }

    public void readCreditCards(String filePath,Controller controller){
        File file = new File(filePath);
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (reader.hasNextLine()){
            String data = reader.nextLine();
            String[] tokens = data.split(",");
            if(tokens.length == 1) {
                System.out.println(tokens[0]);
                System.out.println(controller.getAllBankAccounts());
                controller.addCreditCard(Integer.valueOf(tokens[0]));
            }
            else{
                CreditCard creditCard = new CreditCard(Integer.valueOf(tokens[0]),Integer.valueOf(tokens[1]),Long.valueOf(tokens[2]),Integer.valueOf(tokens[3]),LocalDate.parse(tokens[4]),Float.valueOf(tokens[5]),LocalDate.parse(tokens[6]));
                controller.addCreditCard(creditCard.getCardId());
                BankAccount bankAccount = controller.findBankAccount(creditCard.getBankAccountId());
                bankAccount.getCards().add(creditCard);
            }

        }
        reader.close();
    }




}

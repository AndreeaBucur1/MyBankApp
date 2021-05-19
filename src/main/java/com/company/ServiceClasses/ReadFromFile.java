package com.company.ServiceClasses;

import com.company.*;
import com.company.BankAccounts.BankAccount;
import com.company.BankAccounts.SavingAccount;
import com.company.Cards.CreditCard;
import com.company.Cards.DebitCard;
import com.company.Client.AppAccount;
import com.company.Client.Client;
import com.company.Transactions.MoneyTransfer;
import com.company.Transactions.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class ReadFromFile {
    public void readClients(String filePath, Controller controller){
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
                controller.addClient();
            }
            else{
                Client client = new Client(Integer.valueOf(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4],Integer.valueOf(tokens[5]), Long.valueOf(tokens[6]));
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
                try {
                    controller.addAppAccount(Integer.valueOf(tokens[0]));
                } catch (MyException e) {
                    e.printStackTrace();
                }

            }
            else{
                AppAccount appAccount = new AppAccount(Integer.valueOf(tokens[0]),Integer.valueOf(tokens[1]), tokens[2]);
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
                try {
                    controller.addBankAccount(Integer.valueOf(tokens[0]));
                } catch (MyException e) {
                    e.printStackTrace();
                }
            }
            else{
                BankAccount bankAccount = new BankAccount(Integer.valueOf(tokens[0]),tokens[1],Float.valueOf(tokens[2]), LocalDate.parse(tokens[3]));
                Client client = null;
                try {
                    client = controller.findClientById(Integer.valueOf(tokens[4]));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                client.getBankAccounts().add(bankAccount);


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
                try {
                    controller.addSavingAccount(Integer.valueOf(tokens[0]));
                } catch (MyException e) {
                    e.printStackTrace();
                }
            }
            else{
                SavingAccount savingAccount = new SavingAccount(Integer.valueOf(tokens[0]),tokens[1],Float.valueOf(tokens[2]),LocalDate.parse(tokens[3]),Float.valueOf(tokens[4]));
                Client client = null;
                try {
                    client = controller.findClientByBankAccountId(Integer.valueOf(tokens[5]));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                client.getBankAccounts().add(savingAccount);
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
                try {
                    controller.addCreditCard(Integer.valueOf(tokens[0]));
                } catch (MyException e) {
                    e.printStackTrace();
                }
            }
            else{
                CreditCard creditCard = new CreditCard(Integer.valueOf(tokens[0]),Integer.valueOf(tokens[1]),Long.valueOf(tokens[2]),Integer.valueOf(tokens[3]),LocalDate.parse(tokens[4]),Float.valueOf(tokens[5]),LocalDate.parse(tokens[6]));
                BankAccount bankAccount = controller.findBankAccountById(Integer.valueOf(tokens[1]));
                bankAccount.getCards().add(creditCard);

            }

        }
        reader.close();
    }

    public void readDebitCards(String filePath,Controller controller){
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
                try {
                    controller.addDebitCard(Integer.valueOf(tokens[0]));
                } catch (MyException e) {
                    e.printStackTrace();
                }
            }
            else{
                    DebitCard debitCard = new DebitCard(Integer.valueOf(tokens[0]),Integer.valueOf(tokens[1]),Long.valueOf(tokens[2]),Integer.valueOf(tokens[3]),LocalDate.parse(tokens[4]));
                    BankAccount bankAccount = controller.findBankAccountById(debitCard.getBankAccountId());
                    bankAccount.getCards().add(debitCard);



            }

        }
        reader.close();
    }

    public void readTransactions(String filePath, Controller controller){
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
                Transaction transaction = new Transaction(Integer.valueOf(tokens[0]),tokens[1],Integer.valueOf(tokens[2]),LocalDate.parse(tokens[3]),Float.valueOf(tokens[4]));
                controller.getTransactions().add(transaction);
            }
            else{
                MoneyTransfer moneyTransfer = new MoneyTransfer(Integer.valueOf(tokens[0]),tokens[1],Integer.valueOf(tokens[2]),LocalDate.parse(tokens[3]),Float.valueOf(tokens[4]),Integer.valueOf(tokens[5]));
                controller.getTransactions().add(moneyTransfer);
            }

        }
        reader.close();
    }


}

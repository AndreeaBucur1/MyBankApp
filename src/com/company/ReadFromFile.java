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
                System.out.println(bankAccount);
                Client client = controller.findClient(Integer.valueOf(tokens[4]));
                client.getBankAccounts().add(bankAccount);

            }

        }
        reader.close();
    }

}

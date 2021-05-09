package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

public class LoadDataToFiles {
    public void loadDataToFile(Controller controller){
        WriteToFiles writeToFile = new WriteToFiles();

        //Inainte de a scrie datele in fisier, le sterg pe cele existente

        try {
            FileWriter writeToClients = new FileWriter("src/main/java/com/company/clients.csv", false);
            FileWriter writeToAppAccounts = new FileWriter("src/main/java/com/company/appAccounts.csv", false);
            FileWriter writeToBankAccounts = new FileWriter("src/main/java/com/company/bankAccounts.csv", false);
            FileWriter writeToSavingAccounts = new FileWriter("src/main/java/com/company/savAcc.csv", false);
            FileWriter writeToCreditCards = new FileWriter("src/main/java/com/company/creditCards.csv", false);
            FileWriter writeToDebitCards = new FileWriter("src/main/java/com/company/debitCards.csv", false);
            FileWriter writeToTransactions = new FileWriter("src/main/java/com/company/transactions.csv", false);
            writeToCreditCards.close();
            writeToAppAccounts.close();
            writeToClients.close();
            writeToBankAccounts.close();
            writeToSavingAccounts.close();
            writeToDebitCards.close();
            writeToTransactions.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        writeToFile.writeToClients(controller.getClients());
        writeToFile.writeToAppAccounts(controller.getAppAccounts(), controller);
        writeToFile.writeToBankAccounts(controller.getBankAccounts(), controller);
        writeToFile.writeToSavingAccounts(controller.getSavingAccounts(), controller);
        writeToFile.writeToCreditCards(controller.getCreditCards(), controller);
        writeToFile.writeToDebitCards(controller.debitCards, controller);
        writeToFile.writeToTransactions(controller.getTransactions(), controller);


    }
}

package com.company.ServiceClasses;

import com.company.ServiceClasses.Controller;
import com.company.ServiceClasses.WriteToFiles;

import java.io.FileWriter;
import java.io.IOException;

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




    }
}
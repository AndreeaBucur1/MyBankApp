package com.company.ServiceClasses;

import com.company.ServiceClasses.Controller;
import com.company.ServiceClasses.ReadFromFile;

import java.sql.SQLException;

public class LoadDataFromFiles {
    public void loadData(Controller controller){
        ReadFromFile readFromFile = new ReadFromFile();

        readFromFile.readClients("src/main/java/com/company/clients.csv", controller);


        readFromFile.readAppAccounts("src/main/java/com/company/appAccounts.csv", controller);
        controller.displayAppAccounts();

        readFromFile.readBankAccounts("src/main/java/com/company/bankAccounts.csv", controller);
        readFromFile.readSavingAccounts("src/main/java/com/company/savAcc.csv", controller);
        controller.displayBankAccounts();
        try {
            controller.displaySavingAccounts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        readFromFile.readCreditCards("src/main/java/com/company/creditCards.csv", controller);
        controller.displayCreditCards();
        controller.displayBankAccounts();
        try {
            controller.displaySavingAccounts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        readFromFile.readDebitCards("src/main/java/com/company/debitCards.csv", controller);
        controller.displayDebitCards();

        readFromFile.readTransactions("src/main/java/com/company/transactions.csv", controller);




    }
}

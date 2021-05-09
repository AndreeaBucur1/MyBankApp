package com.company;

public class LoadDataFromFiles {
    public void loadData(Controller controller){
        ReadFromFile readFromFile = new ReadFromFile();

        readFromFile.readClients("src/main/java/com/company/clients.csv", controller);
        controller.displayClients(controller.getClients());

        readFromFile.readAppAccounts("src/main/java/com/company/appAccounts.csv", controller);
        controller.displayAppAccounts(controller.getAppAccounts());

        readFromFile.readBankAccounts("src/main/java/com/company/bankAccounts.csv", controller);
        readFromFile.readSavingAccounts("src/main/java/com/company/savAcc.csv", controller);
        controller.displayBankAccounts(controller.getBankAccounts());
        controller.displaySavingAccounts(controller.getSavingAccounts());

        readFromFile.readCreditCards("src/main/java/com/company/creditCards.csv", controller);
        controller.displayCreditCards(controller.getCreditCards());
        controller.displayBankAccounts(controller.getBankAccounts());
        controller.displaySavingAccounts(controller.getSavingAccounts());

        readFromFile.readDebitCards("src/main/java/com/company/debitCards.csv", controller);
        controller.displayDebitCards(controller.debitCards);

        readFromFile.readTransactions("src/main/java/com/company/transactions.csv", controller);


        controller.displayClients(controller.getClients());

    }
}

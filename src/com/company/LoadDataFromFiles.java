package com.company;

public class LoadDataFromFiles {
    public void loadData(Controller controller){
        ReadFromFile readFromFile = new ReadFromFile();

        readFromFile.readClients("src/com/company/clients.csv", controller);
        controller.displayClients(controller.getClients());

        readFromFile.readAppAccounts("src/com/company/appAccounts.csv", controller);
        controller.displayAppAccounts(controller.getAppAccounts());

        readFromFile.readBankAccounts("src/com/company/bankAccounts.csv", controller);
        readFromFile.readSavingAccounts("src/com/company/savAcc.csv", controller);
        controller.displayBankAccounts(controller.getBankAccounts());
        controller.displaySavingAccounts(controller.getSavingAccounts());

        readFromFile.readCreditCards("src/com/company/creditCards.csv", controller);
        controller.displayCreditCards(controller.getCreditCards());
        controller.displayBankAccounts(controller.getBankAccounts());
        controller.displaySavingAccounts(controller.getSavingAccounts());

        readFromFile.readDebitCards("src/com/company/debitCards.csv", controller);
        controller.displayDebitCards(controller.debitCards);

        readFromFile.readTransactions("src/com/company/transactions.csv", controller);


        controller.displayClients(controller.getClients());

    }
}

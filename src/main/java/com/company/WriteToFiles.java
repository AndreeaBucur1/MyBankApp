package com.company;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WriteToFiles {
    public void writeToAudit(String actionName){
        try {
            FileWriter writer = new FileWriter("src/main/java/com/company/audit.csv",true);
            String newAction = actionName + ',' + LocalDate.now();
            System.out.println(newAction);
            writer.write(newAction);
            writer.write('\n');
            writer.close();


        }
        catch (IOException e){
            e.printStackTrace();
        }


    }

    public void writeToClients(ArrayList<Client> clients){
        try {
            FileWriter writer = new FileWriter("src/main/java/com/company/clients.csv",true);
            for(Client client : clients) {
                String cl = client.getClientId() + "," + client.getFirstName() + ',' + client.getLastName() + ',' + client.getPhoneNumber() + ',' + client.getEmail() + ',' + client.getAppAccountId() + ',' + client.getPNC();
                writer.write(cl);
                writer.write('\n');

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void writeToAppAccounts(ArrayList<AppAccount> appAccounts,Controller controller){
        try {
            FileWriter writeToAppAccounts = new FileWriter("src/main/java/com/company/appAccounts.csv",true);
            for(AppAccount appAccount : appAccounts){
                String appAcc = appAccount.getAppAccountId() + "," + appAccount.getAccessToken() + "," + appAccount.getPassword();
                writeToAppAccounts.write(appAcc);
                writeToAppAccounts.write('\n');
            }
            writeToAppAccounts.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void writeToBankAccounts(ArrayList<BankAccount> bankAccounts,Controller controller){
        try {
            FileWriter writeToBankAccounts = new FileWriter("src/main/java/com/company/bankAccounts.csv",true);
            for(BankAccount bankAccount : bankAccounts){
                Client client = controller.findClientByBankAccountId(bankAccount.getBankAccountId());
                String bankAcc = bankAccount.getBankAccountId() + "," + bankAccount.getIBAN() + ',' + bankAccount.getBalance() + ',' + bankAccount.getOpeningDate() + ',' +  client.getClientId();
                writeToBankAccounts.write(bankAcc);
                writeToBankAccounts.write('\n');
            }
            writeToBankAccounts.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToSavingAccounts(ArrayList<SavingAccount> savingAccounts,Controller controller) {
        try{
            FileWriter writeToSavingAccounts = new FileWriter("src/main/java/com/company/savAcc.csv",true);
            for(SavingAccount savingAccount : savingAccounts){
                Client client = controller.findClientByBankAccountId(savingAccount.getBankAccountId());
                String bankAcc = savingAccount.getBankAccountId() + "," + savingAccount.getIBAN() + ',' + savingAccount.getBalance() + ',' + savingAccount.getOpeningDate() + ','  + savingAccount.getCommissionPct() + ',' +  client.getClientId();
                writeToSavingAccounts.write(bankAcc);
                writeToSavingAccounts.write('\n');
            }
            writeToSavingAccounts.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToCreditCards(ArrayList<CreditCard> creditCards,Controller controller){
        try{
            FileWriter writeToCreditCards = new FileWriter("src/main/java/com/company/creditCards.csv",true);
            for(CreditCard creditCard : creditCards){
                String creditC = creditCard.getCardId() + "," + creditCard.getBankAccountId() + ',' + creditCard.getCardNumber() + ',' + creditCard.getCVV() + ',' + creditCard.getExpirationDate() + ',' + creditCard.getAvailableBalance() + ',' + creditCard.getExpirationDate();
                writeToCreditCards.write(creditC);
                writeToCreditCards.write('\n');
            }
            writeToCreditCards.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToDebitCards(ArrayList<DebitCard> debitCards,Controller controller){
        try{
            FileWriter writeToDebitCards = new FileWriter("src/main/java/com/company/debitCards.csv",true);
            for(DebitCard debitCard : debitCards){
                String debitC = debitCard.getCardId() + "," + debitCard.getBankAccountId() + ',' + debitCard.getCardNumber() + ',' + debitCard.getCVV() + ',' + debitCard.getExpirationDate() + ',' + debitCard.getOverDraftLimit() + ',' + debitCard.getTransactionCommission();
                writeToDebitCards.write(debitC);
                writeToDebitCards.write('\n');
            }
            writeToDebitCards.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToTransactions(ArrayList<Transaction> transactions,Controller controller){
        try{
            FileWriter writeToTransactions = new FileWriter("src/main/java/com/company/transactions.csv",true);
            for(Transaction transaction : transactions){
                if(transaction instanceof Transaction){
                    String trans = transaction.getTransactionId() + "," + transaction.getTransactionName() + "," + transaction.getBankAccountId() + ',' + transaction.getDate() + ',' + transaction.getSold();
                    writeToTransactions.write(trans);
                    writeToTransactions.write('\n');
                }
                else if(transaction instanceof MoneyTransfer) {
                    String transfer = transaction.getTransactionId() + "," + transaction.getTransactionName() + "," + transaction.getBankAccountId() + ',' + transaction.getDate() + ',' + transaction.getSold() + ((MoneyTransfer) transaction).getTransferToBankAccountId();
                    writeToTransactions.write(transfer);
                    writeToTransactions.write('\n');
                }
            }
            writeToTransactions.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


}

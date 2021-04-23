package com.company;
import java.io.FileWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WriteToFiles {
    public void writeToAudit(String actionName){
        try {
            FileWriter writer = new FileWriter("src/com/company/audit.csv",true);
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
            FileWriter writer = new FileWriter("src/com/company/clients.csv",true);
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
            FileWriter writeToAppAccounts = new FileWriter("src/com/company/appAccounts.csv",true);
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
            FileWriter writeToBankAccounts = new FileWriter("src/com/company/bankAccounts.csv",true);
            for(BankAccount bankAccount : bankAccounts){
                Client client = controller.findClientByBankAccountId(bankAccount.getBankAccountId());
                String bankAcc = bankAccount.getBankAccountId() + "," + ',' + bankAccount.getIBAN() + ',' + bankAccount.getBalance() + ',' + bankAccount.getOpeningDate() + ',' +  client.getClientId();
                writeToBankAccounts.write(bankAcc);
                writeToBankAccounts.write('\n');
            }
            writeToBankAccounts.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

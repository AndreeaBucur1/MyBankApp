package com.company.Database;

import com.company.AppAccount;
import com.company.BankAccount;
import com.company.Client;
import com.company.SavingAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class DatabaseConnection {

    private static Connection connection = null;

    public Connection Connection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyBankApp", "root", "root");
                return connection;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


            return null;
        }
    }


    public void addClient(Client client){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO client (firstName, lastName, phoneNumber, email,pnc) values (?,?,?,?,?)");
            statement.setString(1,client.getFirstName());
            statement.setString(2,client.getLastName());
            statement.setString(3,client.getPhoneNumber());
            statement.setString(4,client.getEmail());
            statement.setLong(5,client.getPNC());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addAppAccount(AppAccount appAccount, int clientId){
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("INSERT INTO appaccount (accesstoken,password) values (?,?)");
            statement.setInt(1,appAccount.getAccessToken());
            statement.setString(2, appAccount.getPassword());
            statement.execute();

            ArrayList<AppAccount> appAccounts = getFromDatabase.getAppAccounts();
            appAccounts.sort(Comparator.comparing(AppAccount::getAppAccountId));
            int lastId = appAccounts.get(appAccounts.size()-1).getAppAccountId();
            PreparedStatement preparedStatement = connection.prepareStatement("update client set appaccountid = ? where clientid = ?");
            preparedStatement.setInt(1,lastId);
            preparedStatement.setInt(2,clientId);
            preparedStatement.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addBankAccount(BankAccount bankAccount, int clientId){
        PreparedStatement preparedStatement = null;
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO bankaccount (iban,balance,openingdate) values (?,?,?)");
            preparedStatement.setString(1,bankAccount.getIBAN());
            preparedStatement.setFloat(2,bankAccount.getBalance());
            preparedStatement.setDate(3, Date.valueOf(bankAccount.getOpeningDate()));
            preparedStatement.execute();
            ArrayList<BankAccount> bankAccounts = getFromDatabase.getBankAccounts();
            bankAccounts.sort(Comparator.comparing(BankAccount::getBankAccountId));
            int lastId = bankAccounts.get(bankAccounts.size()-1).getBankAccountId();
            System.out.println(lastId);
            addBankAccountToClient(lastId,clientId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addSavingAccount(SavingAccount savingAccount, int clientId){
        PreparedStatement preparedStatement = null;
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO bankaccount (iban,balance,openingdate) values (?,?,?)");
            preparedStatement.setString(1,savingAccount.getIBAN());
            preparedStatement.setFloat(2,savingAccount.getBalance());
            preparedStatement.setDate(3, Date.valueOf(savingAccount.getOpeningDate()));
            preparedStatement.execute();
            ArrayList<BankAccount> bankAccounts = getFromDatabase.getBankAccounts();
            bankAccounts.sort(Comparator.comparing(BankAccount::getBankAccountId));
            int lastId = bankAccounts.get(bankAccounts.size()-1).getBankAccountId();
            addBankAccountToClient(lastId,clientId);
            preparedStatement = null;
            preparedStatement = connection.prepareStatement("insert into savingaccount values (?,?)");
            preparedStatement.setInt(1,lastId);
            preparedStatement.setFloat(2,savingAccount.getCommissionPct());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addBankAccountToClient(int bankAccountId,int clientId){
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement("insert into hasbankaccount values (?,?)");
            System.out.println(bankAccountId);
            preparedStatement.setInt(1,bankAccountId);
            preparedStatement.setInt(2,clientId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}

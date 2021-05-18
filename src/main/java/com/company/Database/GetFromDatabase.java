package com.company.Database;

import com.company.AppAccount;
import com.company.BankAccount;
import com.company.Client;
import com.company.SavingAccount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetFromDatabase {



    public ArrayList<AppAccount> getAppAccounts() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from appaccount");
        ArrayList<AppAccount> accounts = new ArrayList<>();
        while (resultSet.next()){
            AppAccount account = new AppAccount(resultSet.getInt("appAccountId"),resultSet.getInt("accessToken"),resultSet.getString("password"));
            accounts.add(account);
        }
        return accounts;
    }

    public ArrayList<BankAccount> getBankAccounts() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from bankaccount");
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        while (resultSet.next()){
            BankAccount bankAccount = new BankAccount(resultSet.getInt("bankaccountid"),resultSet.getString("iban"),resultSet.getFloat("balance"),resultSet.getDate("openingdate").toLocalDate());
            bankAccounts.add(bankAccount);
        }
        System.out.println(bankAccounts);
        return bankAccounts;
    }

    public ArrayList<SavingAccount> getSavingAccounts() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from savingaccount");
        ArrayList<SavingAccount> savingAccounts = new ArrayList<>();
        while (resultSet.next()){
            int bankAccountId = resultSet.getInt("savingaccountid");
            float commission = resultSet.getFloat("commissionpct");
            ResultSet resultSet1 = statement1.executeQuery("select * from bankaccount where bankaccountid = " + bankAccountId);
            while(resultSet1.next()){
                SavingAccount savingAccount = new SavingAccount(resultSet1.getInt("bankaccountid"),resultSet1.getString("iban"),resultSet1.getFloat("balance"),resultSet1.getDate("openingdate").toLocalDate(),commission);
                savingAccounts.add(savingAccount);

            }
        }
        System.out.println(savingAccounts);
        return savingAccounts;
    }

    public ArrayList<Client> getClients() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from client");
        ArrayList<Client> clients = new ArrayList<>();
        while(resultSet.next()){
            Client client = new Client(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),resultSet.getLong(7));
            clients.add(client);
        }

        return clients;
    }



}

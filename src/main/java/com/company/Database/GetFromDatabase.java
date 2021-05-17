package com.company.Database;

import com.company.AppAccount;
import com.company.BankAccount;

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
        return bankAccounts;
    }
}

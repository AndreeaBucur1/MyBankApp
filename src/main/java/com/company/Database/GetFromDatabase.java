package com.company.Database;

import com.company.BankAccounts.BankAccount;
import com.company.BankAccounts.SavingAccount;
import com.company.Cards.Card;
import com.company.Cards.CreditCard;
import com.company.Cards.DebitCard;
import com.company.Client.AppAccount;
import com.company.Client.Client;
import com.company.ServiceClasses.Controller;

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
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery("select * from savingaccount where savingAccountId = " + resultSet.getInt("bankaccountid"));
            if(resultSet1.next() == false) {
                BankAccount bankAccount = new BankAccount(resultSet.getInt("bankaccountid"), resultSet.getString("iban"), resultSet.getFloat("balance"), resultSet.getDate("openingdate").toLocalDate());
                bankAccounts.add(bankAccount);
            }
        }
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
        return savingAccounts;
    }

    public ArrayList<BankAccount> getAllBankAccounts(){
        ArrayList<BankAccount> allBankAccounts = new ArrayList<>();
        try {
            allBankAccounts.addAll(getBankAccounts());
            allBankAccounts.addAll(getSavingAccounts());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allBankAccounts;
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

    public ArrayList<Card> getCards() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from card");
        ArrayList<Card> cards = new ArrayList<>();
        return cards;

    }

    public ArrayList<BankAccount> getClientsBankAccounts(int clientId) throws SQLException {
        Controller controller = new Controller();
        ArrayList<BankAccount> allBankAccounts = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        allBankAccounts = getAllBankAccounts();
        ArrayList<BankAccount> clientsBankAccounts = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from hasbankaccount where clientid = " + clientId);
        while (resultSet.next()){
            for(BankAccount bankAccount : allBankAccounts){
                if(bankAccount.getBankAccountId() == resultSet.getInt("bankaccountid")){
                    BankAccount clientsBankAccount = controller.findBankAccountById(bankAccount.getBankAccountId());
                    clientsBankAccounts.add(bankAccount);
                }
            }
        }
        return clientsBankAccounts;

    }

    public ArrayList<CreditCard> getCreditCards() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        Statement statement = connection.createStatement();
        ArrayList<CreditCard> creditCards = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("select * from card join creditcard where cardid = creditcardid");
        while (resultSet.next()){
            CreditCard creditCard = new CreditCard(resultSet.getInt("creditcardid"),resultSet.getInt("bankaccountid"),resultSet.getLong("cardnumber"),resultSet.getInt("cvv"),resultSet.getDate("expirationdate").toLocalDate(),resultSet.getFloat("availablebalance"),resultSet.getDate("duedate").toLocalDate());
            creditCards.add(creditCard);
        }
        return  creditCards;
    }


    public ArrayList<DebitCard> getDebitCards() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();
        Statement statement = connection.createStatement();
        ArrayList<DebitCard> debitCards = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("select * from card join debitcard where cardid = debitcardid");
        while (resultSet.next()){
            DebitCard debitCard = new DebitCard(resultSet.getInt("debitcardid"),resultSet.getInt("bankaccountid"),resultSet.getLong("cardnumber"),resultSet.getInt("cvv"),resultSet.getDate("expirationdate").toLocalDate(),resultSet.getFloat("overdraftlimit"),resultSet.getFloat("transactionCommission"));
            debitCards.add(debitCard);
        }
        return  debitCards;
    }

    public ArrayList<Card> getAllCards(){
        ArrayList<Card> allCards = new ArrayList<>();
        try {
            allCards.addAll(getCreditCards());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            allCards.addAll(getDebitCards());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCards;
    }




}

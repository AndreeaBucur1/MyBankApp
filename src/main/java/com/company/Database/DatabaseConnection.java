package com.company.Database;

import com.company.BankAccounts.BankAccount;
import com.company.BankAccounts.SavingAccount;
import com.company.Cards.CreditCard;
import com.company.Cards.DebitCard;
import com.company.Client.AppAccount;
import com.company.Client.Client;
import com.company.Transactions.MoneyTransfer;
import com.company.Transactions.Transaction;

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


    public void addClient(Client client) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO client (firstName, lastName, phoneNumber, email,pnc) values (?,?,?,?,?)");
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getPhoneNumber());
            statement.setString(4, client.getEmail());
            statement.setLong(5, client.getPNC());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addAppAccount(AppAccount appAccount, int clientId) {
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO appaccount (accesstoken,password) values (?,?)");
            statement.setInt(1, 100000 + clientId);
            statement.setString(2, appAccount.getPassword());
            statement.execute();

            ArrayList<AppAccount> appAccounts = getFromDatabase.getAppAccounts();
            appAccounts.sort(Comparator.comparing(AppAccount::getAppAccountId));
            int lastId = appAccounts.get(appAccounts.size() - 1).getAppAccountId();
            PreparedStatement preparedStatement = connection.prepareStatement("update client set appaccountid = ? where clientid = ?");
            preparedStatement.setInt(1, lastId);
            preparedStatement.setInt(2, clientId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addBankAccount(BankAccount bankAccount, int clientId) throws SQLException {
        PreparedStatement preparedStatement = null;
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from id");
        int last_id = 0;
        while (resultSet.next()){
            last_id = resultSet.getInt("iban_nr");
        }
        PreparedStatement preparedStatement1 = connection.prepareStatement("update id set iban_nr = ? where iban_nr = ?");
        preparedStatement1.setLong(1,last_id + 1);
        preparedStatement1.setLong(2,last_id);
        preparedStatement1.execute();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO bankaccount (iban,balance,openingdate) values (?,?,?)");
            preparedStatement.setString(1, "ROMBA" + last_id);
            preparedStatement.setFloat(2, bankAccount.getBalance());
            preparedStatement.setDate(3, Date.valueOf(bankAccount.getOpeningDate()));
            preparedStatement.execute();
            ArrayList<BankAccount> bankAccounts = getFromDatabase.getBankAccounts();
            bankAccounts.sort(Comparator.comparing(BankAccount::getBankAccountId));
            int lastId = bankAccounts.get(bankAccounts.size() - 1).getBankAccountId();
            System.out.println(lastId);
            addBankAccountToClient(lastId, clientId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addSavingAccount(SavingAccount savingAccount, int clientId) throws SQLException {
        PreparedStatement preparedStatement = null;
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from id");
        int last_id = 0;
        while (resultSet.next()){
            last_id = resultSet.getInt("iban_nr");
        }
        PreparedStatement preparedStatement1 = connection.prepareStatement("update id set iban_nr = ? where iban_nr = ?");
        preparedStatement1.setLong(1,last_id + 1);
        preparedStatement1.setLong(2,last_id);
        preparedStatement1.execute();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO bankaccount (iban,balance,openingdate) values (?,?,?)");
            preparedStatement.setString(1, "ROMBA" + last_id);
            preparedStatement.setFloat(2, savingAccount.getBalance());
            preparedStatement.setDate(3, Date.valueOf(savingAccount.getOpeningDate()));
            preparedStatement.execute();
            ArrayList<BankAccount> bankAccounts = getFromDatabase.getBankAccounts();
            bankAccounts.sort(Comparator.comparing(BankAccount::getBankAccountId));
            int lastId = bankAccounts.get(bankAccounts.size() - 1).getBankAccountId();
            addBankAccountToClient(lastId, clientId);
            preparedStatement = null;
            preparedStatement = connection.prepareStatement("insert into savingaccount values (?,?)");
            preparedStatement.setInt(1, lastId);
            preparedStatement.setFloat(2, savingAccount.getCommissionPct());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addBankAccountToClient(int bankAccountId, int clientId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into hasbankaccount values (?,?)");
            System.out.println(bankAccountId);
            preparedStatement.setInt(1, bankAccountId);
            preparedStatement.setInt(2, clientId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addCreditCard(CreditCard creditCard, int bankAccountId) throws SQLException {
        PreparedStatement preparedStatement = null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from id");
        Long card_nr = null;
        int cvv = 0;
        while (resultSet.next()){
            card_nr = resultSet.getLong("card_nr");
            cvv = resultSet.getInt("cvv");
        }
        PreparedStatement preparedStatement1 = connection.prepareStatement("update id set card_nr = ? where card_nr = ?");
        preparedStatement1.setLong(1,card_nr + 1);
        preparedStatement1.setLong(2,card_nr);
        preparedStatement1.execute();
        PreparedStatement preparedStatement2 = connection.prepareStatement("update id set cvv = ? where cvv = ?");
        preparedStatement2.setLong(1,cvv+1);
        preparedStatement2.setInt(2,cvv);
        preparedStatement2.execute();
        try {
            preparedStatement = connection.prepareStatement("insert into card(cardnumber,cvv,expirationdate,bankaccountid) values (?,?,?,?)");
            preparedStatement.setLong(1, card_nr);
            preparedStatement.setInt(2, cvv);
            preparedStatement.setDate(3, Date.valueOf(creditCard.getExpirationDate()));
            preparedStatement.setInt(4, bankAccountId);

            preparedStatement.execute();
            preparedStatement = connection.prepareStatement("insert into creditcard values (?,?,?)");
            int creditCardId;
            Statement statement2;
            statement2 = connection.createStatement();
            resultSet = statement2.executeQuery("select MAX(cardid) as cardidd from card");

            if (resultSet.next() != false) {
                creditCardId = resultSet.getInt("cardidd");
                preparedStatement.setInt(1, creditCardId);
                preparedStatement.setFloat(2, creditCard.getAvailableBalance());
                preparedStatement.setDate(3, Date.valueOf(creditCard.getDueDate()));
                preparedStatement.execute();
            } else {
                System.out.println("No bank account has this id");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addDebitCard(DebitCard debitCard, int bankAccountId) throws SQLException {
        PreparedStatement preparedStatement = null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from id");
        Long card_nr = null;
        int cvv = 0;
        while (resultSet.next()){
            card_nr = resultSet.getLong("card_nr");
            cvv = resultSet.getInt("cvv");
        }
        PreparedStatement preparedStatement1 = connection.prepareStatement("update id set card_nr = ? where card_nr = ?");
        preparedStatement1.setLong(1,card_nr + 1);
        preparedStatement1.setLong(2,card_nr);
        preparedStatement1.execute();
        PreparedStatement preparedStatement2 = connection.prepareStatement("update id set cvv = ? where cvv = ?");
        preparedStatement2.setLong(1,cvv+1);
        preparedStatement2.setInt(2,cvv);
        preparedStatement2.execute();
        try {
            preparedStatement = connection.prepareStatement("insert into card(cardnumber,cvv,expirationdate,bankaccountid) values (?,?,?,?)");
            preparedStatement.setLong(1, card_nr);
            preparedStatement.setInt(2, cvv);
            preparedStatement.setDate(3, Date.valueOf(debitCard.getExpirationDate()));
            preparedStatement.setInt(4, bankAccountId);

            preparedStatement.execute();
            preparedStatement = connection.prepareStatement("insert into debitcard values (?,?,?)");
            int debitCardId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select MAX(cardid) as cardidd from card");
            while (resultSet.next()) {
                debitCardId = resultSet.getInt("cardidd");
                preparedStatement.setInt(1, debitCardId);
                preparedStatement.setFloat(2, debitCard.getOverDraftLimit());
                preparedStatement.setFloat(3, debitCard.getTransactionCommission());
                preparedStatement.execute();

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void addTransaction(Transaction transaction) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into transaction (transactionname,bankaccountid,transactiondate,sold) values  (?,?,?,?)");
        preparedStatement.setString(1,transaction.getTransactionName());
        preparedStatement.setInt(2,transaction.getBankAccountId());
        preparedStatement.setDate(3,Date.valueOf(transaction.getDate()));
        preparedStatement.setFloat(4,transaction.getSold());
        preparedStatement.execute();

    }

    public void addMoneyTransfer(MoneyTransfer moneyTransfer){
        try {
            addTransaction(((Transaction) moneyTransfer));
            int lastId = 0;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select MAX(transactionid) as transactionId from transaction");
            while (resultSet.next()){
                lastId = resultSet.getInt("transactionid");
            }
            PreparedStatement preparedStatement = connection.prepareStatement("insert into moneytransfer values (?,?)");
            preparedStatement.setInt(1,lastId);
            preparedStatement.setInt(2,moneyTransfer.getTransferToBankAccountId());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




}



















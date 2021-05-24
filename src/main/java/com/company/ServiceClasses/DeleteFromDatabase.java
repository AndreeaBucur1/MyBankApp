package com.company.ServiceClasses;

import com.company.BankAccounts.BankAccount;
import com.company.BankAccounts.SavingAccount;
import com.company.Cards.Card;
import com.company.Cards.CreditCard;
import com.company.Cards.DebitCard;
import com.company.Client.AppAccount;
import com.company.Client.Client;
import com.company.Database.DatabaseConnection;
import com.company.Database.GetFromDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteFromDatabase {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.Connection();

    public void deleteCreditCard(CreditCard creditCard) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from creditcard where creditcardid = ?");
        preparedStatement.setInt(1, creditCard.getCardId());
        preparedStatement.execute();

        PreparedStatement preparedStatement1 = connection.prepareStatement("delete from card where cardid = ?");
        preparedStatement1.setInt(1, creditCard.getCardId());
        preparedStatement1.execute();
    }

    public void deleteDebitCard(DebitCard debitCard) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from debitcard where debitcardid = ?");
        preparedStatement.setInt(1, debitCard.getCardId());
        preparedStatement.execute();

        PreparedStatement preparedStatement1 = connection.prepareStatement("delete from card where cardid = ?");
        preparedStatement1.setInt(1, debitCard.getCardId());
        preparedStatement1.execute();
    }

    public void deleteAppAccount(AppAccount appAccount) throws SQLException {
        Controller controller = new Controller();
        Client client = controller.findClientByAppAccountId(appAccount.getAppAccountId());
        PreparedStatement preparedStatement = connection.prepareStatement("update client set appaccountid = null where clientid = ?");
        preparedStatement.setInt(1,client.getClientId());
        preparedStatement.execute();
        PreparedStatement preparedStatement1 = connection.prepareStatement("delete from appaccount where appaccountid = ?");
        preparedStatement1.setInt(1,appAccount.getAppAccountId());
        preparedStatement1.execute();


    }

    public void deleteBankAccount(BankAccount bankAccount) throws SQLException {
        Controller controller = new Controller();
        GetFromDatabase getFromDatabase = new GetFromDatabase();
        ArrayList<Card> cards = getFromDatabase.getCardsOfBankAccount(bankAccount);
        for(Card card : cards){
            if(card instanceof CreditCard){
                try {
                    deleteCreditCard((CreditCard) card);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if(card instanceof DebitCard){
                try {
                    deleteDebitCard((DebitCard) card);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        PreparedStatement preparedStatement = connection.prepareStatement("delete from hasbankaccount where bankaccountid = ?");
        preparedStatement.setInt(1,bankAccount.getBankAccountId());
        preparedStatement.execute();

        preparedStatement = null;
        preparedStatement = connection.prepareStatement("delete from bankaccount where bankaccountid = " + bankAccount.getBankAccountId());
        preparedStatement.execute();

        if(bankAccount instanceof SavingAccount){
            preparedStatement = null;
            preparedStatement = connection.prepareStatement("delete from savingaccount where savingaccountid = " + bankAccount.getBankAccountId());
            preparedStatement.execute();
        }

    }

}


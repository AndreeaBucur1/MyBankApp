package com.company.ServiceClasses;

import com.company.Cards.Card;
import com.company.Cards.CreditCard;
import com.company.Cards.DebitCard;
import com.company.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}


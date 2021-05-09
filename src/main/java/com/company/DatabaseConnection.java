package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            statement = connection.prepareStatement("INSERT INTO client (firstName, lastName, phoneNumber, email) values (?,?,?,?)");
            statement.setString(1,client.getFirstName());
            statement.setString(2,client.getLastName());
            statement.setString(3,client.getPhoneNumber());
            statement.setString(4,client.getEmail());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addAppAccount(AppAccount appAccount){
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement("INSERT INTO appaccount (accesstoken,password) values (?,?)");
            statement.setInt(1,appAccount.getAccessToken());
            statement.setString(2, appAccount.getPassword());
            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

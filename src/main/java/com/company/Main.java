package com.company;

import com.company.Database.DatabaseConnection;
import com.company.Database.GetFromDatabase;
import com.company.ServiceClasses.AppAccountServices;
import com.company.ServiceClasses.CardServices;
import com.company.ServiceClasses.Controller;
import com.company.ServiceClasses.ManagerServices;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Controller controller = Controller.Singleton();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.Connection();


        int option;
        do {

            System.out.println();
            System.out.println("Option 1: Connect to your account");
            System.out.println("Option 2: Enter your card");
            System.out.println("Option 3: Connect as manager");
            System.out.println("Option 4: Exit");
            System.out.println();
            System.out.print("Choose an option: ");

            Scanner scan = new Scanner(System.in);
            option = scan.nextInt();
            if (option == 1) {
                AppAccountServices appAccountServices = new AppAccountServices();
                appAccountServices.firstOption(controller);
            } else if (option == 2) {
                CardServices cardServices = new CardServices();
                cardServices.secondOption(controller);
            }
            else if(option == 3){
                ManagerServices managerServices = new ManagerServices();
                managerServices.thirdOption(controller);
            }


        } while (option != 4);



    }


}

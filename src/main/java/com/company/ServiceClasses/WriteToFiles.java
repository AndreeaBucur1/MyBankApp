package com.company.ServiceClasses;
import com.company.BankAccounts.BankAccount;
import com.company.BankAccounts.SavingAccount;
import com.company.Cards.CreditCard;
import com.company.Cards.DebitCard;
import com.company.Client.AppAccount;
import com.company.Client.Client;
import com.company.Transactions.MoneyTransfer;
import com.company.Transactions.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WriteToFiles {
    public void writeToAudit(String actionName){
        try {
            FileWriter writer = new FileWriter("src/main/java/com/company/audit.csv",true);
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

}

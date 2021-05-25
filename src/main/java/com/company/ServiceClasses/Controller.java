package com.company.ServiceClasses;

import com.company.*;
import com.company.BankAccounts.BankAccount;
import com.company.BankAccounts.SavingAccount;
import com.company.Cards.Card;
import com.company.Cards.CreditCard;
import com.company.Cards.DebitCard;
import com.company.Client.AppAccount;
import com.company.Client.Client;
import com.company.Database.DatabaseConnection;
import com.company.Database.GetFromDatabase;
import com.company.Transactions.AccountStatement;
import com.company.Transactions.MoneyTransfer;
import com.company.Transactions.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    private static Controller single_instance = null;
    public static Controller Singleton(){
        if(single_instance == null){
            single_instance = new Controller();
        }
        return single_instance;
    }

    DatabaseConnection databaseConnection = new DatabaseConnection();
    Connection connection = databaseConnection.Connection();
    GetFromDatabase getFromDatabase = new GetFromDatabase();
    Scanner scanner = new Scanner(System.in);
    WriteToFiles writeToFiles = new WriteToFiles();


    ArrayList<Transaction> transactions = new ArrayList<>();
    ArrayList<AccountStatement> accountStatements = new ArrayList<>();
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
//    public void setTransactions(ArrayList<Transaction> transactions) {
//        this.transactions = transactions;
//    }
//    public ArrayList<AccountStatement> getAccountStatements() {
//        return accountStatements;
//    }
//    public void setAccountStatements(ArrayList<AccountStatement> accountStatements) {
//        this.accountStatements = accountStatements;
//    }





    //Display functions
    public void displayClients(){
        System.out.println("Clients: ");
        ArrayList<Client> allClients = new ArrayList<>();
        try {
            allClients = getFromDatabase.getClients();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(Client client : allClients){
            System.out.println(client);
        }
        System.out.println();
    }

    public void displayBankAccounts(){
        System.out.println("Bank accounts: ");
        ArrayList<BankAccount> allBankAccounts = new ArrayList<>();
        allBankAccounts = getFromDatabase.getAllBankAccounts();
        for(BankAccount bankAccount : allBankAccounts){
            System.out.println(bankAccount);
        }
        System.out.println();
    }

    public void displaySavingAccounts() throws SQLException {
        System.out.println("Saving accounts: ");
        ArrayList<SavingAccount> savingAccounts = getFromDatabase.getSavingAccounts();
        for(SavingAccount savingAccount : savingAccounts){
            System.out.println(savingAccount);
        }
        System.out.println();
    }

    public void displayTransactions(ArrayList<Transaction> transactions){
        System.out.println("Transactions");
        for(Transaction transaction : transactions){
            System.out.println(transaction);
        }
        System.out.println();
    }

    public void displayAppAccounts(){
        System.out.println("App accounts: ");
        ArrayList<AppAccount> appAccounts = new ArrayList<>();
        try {
            appAccounts = getFromDatabase.getAppAccounts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(AppAccount appAccount : appAccounts){
            System.out.println(appAccount);
        }
        System.out.println();
    }


    public void displayCards(){
        System.out.println("All cards: ");
        ArrayList<Card> allCards = getFromDatabase.getAllCards();
        for(Card card : allCards){
            System.out.println(card);
        }
        System.out.println();
    }

    public void displayCreditCards(){
        System.out.println("Credit cards: ");
        ArrayList<CreditCard> creditCards = null;
        try {
            creditCards = getFromDatabase.getCreditCards();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(CreditCard creditCard : creditCards){
            System.out.println(creditCard);
        }
        System.out.println();
    }
    public void displayDebitCards(){
        System.out.println("Debit cards: ");
        ArrayList<DebitCard> debitCards = null;
        try {
            debitCards = getFromDatabase.getDebitCards();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(DebitCard debitCard : debitCards)
            System.out.println(debitCard);
        System.out.println();
    }

    public void displayAccountStatements(ArrayList<AccountStatement>accountStatements){
        System.out.println("Account statements:");
        for(AccountStatement accountStatement : accountStatements){
            System.out.println(accountStatement);
        }
        System.out.println();
    }





    //"Find" functions

    public AppAccount findAppAccountByAccessToken(int accessToken){
        ArrayList<AppAccount> allAppAccounts;
        try {
            allAppAccounts = getFromDatabase.getAppAccounts();
            for(AppAccount appAccount : allAppAccounts){
                if(appAccount.getAccessToken() == accessToken) {
                    return appAccount;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return null;
    }

    public Client findClientByAppAccountId(int appAccountId){

        try {
            ArrayList<Client> allClients = getFromDatabase.getClients();
            for(Client client : allClients){
                if(client.getAppAccountId() == appAccountId){
                    return client;
                }
            }
        } catch (SQLException throwables) {

        }
        return null;

    }


    public Client findClientById(int clientId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from client where clientid =" + clientId);
        System.out.println(clientId);
        while(resultSet.next()){
            Client client = new Client(resultSet.getInt("clientId"),resultSet.getString("firstname"),resultSet.getString("lastname"),resultSet.getString("phonenumber"),resultSet.getString("email"),resultSet.getInt("appaccountid"),resultSet.getLong("pnc"));
            return client;
        }

        return null;
    }

    public BankAccount findBankAccountById(int id){
        ArrayList<BankAccount> allBankAccounts = new ArrayList<>();
        allBankAccounts = getFromDatabase.getAllBankAccounts();
        for(BankAccount bankAccount : allBankAccounts){
            if (bankAccount.getBankAccountId() == id)
                return bankAccount;
        }

        return null;
    }

    public Client findClientByBankAccountId(int bankAccountId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from hasbankaccount where bankaccountid = " + bankAccountId);
        while (resultSet.next()){
            Client client = findClientById(resultSet.getInt("clientid"));
            return client;
        }
        return null;
    }

    public BankAccount findBankAccountByIban(String iban) throws SQLException {
        ArrayList <BankAccount> allBankAccounts = new ArrayList<>();
        allBankAccounts = getFromDatabase.getAllBankAccounts();
        for(BankAccount bankAccount : allBankAccounts){
            if(bankAccount.getIBAN().compareTo(iban) == 0){
                return bankAccount;
            }
        }
        return null;
    }


    public Card findCardById(int cardId){
        ArrayList<Card> allCards = new ArrayList<>();
        allCards = getFromDatabase.getAllCards();
        for(Card card : allCards){
            if(card.getCardId() == cardId)
                return card;

        }
        return null;
    }


    public Card findCardByNumber(long cardNumber){
        ArrayList<Card> allCards = new ArrayList<>();
        allCards = getFromDatabase.getAllCards();
        for(Card card : allCards)
            if(card.getCardNumber() == cardNumber)
                return card;
        return null;
    }


    //"Add" functions


    public void addClient(){
        System.out.print("First name: ");
        String firstName = scanner.next();
        System.out.print("Last name: ");
        String lastName = scanner.next();
        System.out.print("Phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("PNC: ");
        Long pnc = scanner.nextLong();
        Client client = new Client(firstName, lastName, phoneNumber, email, pnc);
        databaseConnection.addClient(client);
        writeToFiles.writeToAudit("Add client");

    }


    public void addBankAccount(int clientId) throws MyException {
        try {
            if (findClientById(clientId) != null) {
                LocalDate openingDate = LocalDate.now();
                BankAccount bankAccount = new BankAccount( openingDate);
                databaseConnection.addBankAccount(bankAccount,clientId);
            }
            else{
                System.out.println("Client does not exist!");
                throw new MyException("Client does not exist");
            }
        } catch (SQLException throwables) {
        }
    }


    public void addAppAccount(int clientId) throws MyException {
        try {
            if(findClientById(clientId) != null) { ;
                Client client = findClientById(clientId);
                String password = "";
                System.out.println((Integer) client.getAppAccountId());
                if (client.getAppAccountId() == 0) {
                    char lastName = client.getLastName().charAt(0);
                    char firstName = client.getFirstName().charAt(0);
                    long lastDigits = client.getPNC();
                    lastDigits = lastDigits % 10000;
                    if (lastDigits < 1000) {
                        lastDigits = 1000 + lastDigits;
                    }
                    password = password + lastName + firstName;
                    password = password + lastDigits;
                    AppAccount appAccount = new AppAccount(password);
                    databaseConnection.addAppAccount(appAccount,clientId);

                } else {
                    System.out.println("This client already has an account");
                    throw new MyException("Client already has an account");
                }
            }
            else{
                System.out.println("Client does not exist");
                throw new MyException("Client does not exist");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void addCreditCard(int bankAccountId) throws MyException {
        if(findBankAccountById(bankAccountId) != null){
            LocalDate expirationDate = LocalDate.now().plusYears(4);
            CreditCard creditCard = new CreditCard(bankAccountId,expirationDate,expirationDate);
            try {
                databaseConnection.addCreditCard(creditCard,bankAccountId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            System.out.println("Cannot add credit card. This bank account does not exist");
            throw new MyException("Bank account does not exist");
        }
    }

    public void addDebitCard(int bankAccountId) throws MyException {
        if(findBankAccountById(bankAccountId) != null){
            LocalDate expirationDate = LocalDate.now().plusYears(4);
            DebitCard debitCard = new DebitCard(bankAccountId,expirationDate);
            try {
                databaseConnection.addDebitCard(debitCard,bankAccountId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            System.out.println("Cannot add debit card. This bank account does not exist.");
            throw new MyException("Bank account does not exist");
        }
    }

    public void addSavingAccount(int clientId) throws MyException {
        try {
            if(findClientById(clientId) != null){
                Client client = findClientById(clientId);
                LocalDate openingDate = LocalDate.now();
                SavingAccount savingAccount = new SavingAccount(openingDate);
                databaseConnection.addSavingAccount(savingAccount,clientId);
            }
            else {
                System.out.println("Cannot add this saving account. Client does not exist");
                throw new MyException("Client does not exist");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }




    //Actions

    public void addFunds(int bankAccountId,int sum) throws SQLException {
        BankAccount bankAccount = findBankAccountById(bankAccountId);
        if(bankAccount != null){
            String transactionName = "Add funds";
            Transaction transaction = new Transaction(bankAccountId,transactionName,LocalDate.now(), ((float) sum));
            databaseConnection.addTransaction(transaction);

            PreparedStatement preparedStatement = connection.prepareStatement("update bankaccount set balance = ? where bankaccountid = ?");
            preparedStatement.setFloat(1,bankAccount.getBalance() + sum);
            preparedStatement.setInt(2,bankAccount.getBankAccountId());
            preparedStatement.execute();
            bankAccount.setBalance(bankAccount.getBalance() + sum);

        }
        else{
            System.out.println("Cannot add funds. This bank account does not exist");
        }
    }

    public void payment(int bankAccountId,float sum) throws MyException, SQLException {
        BankAccount bankAccount = findBankAccountById(bankAccountId);

        if(sum > bankAccount.getBalance()) {
            System.out.println("Insufficient funds");
            throw new MyException("Insufficient funds");
        }
        else{
            PreparedStatement preparedStatement = connection.prepareStatement("update bankaccount set balance = ? where bankaccountid = ?");
            preparedStatement.setFloat(1,bankAccount.getBalance() - sum);
            preparedStatement.setInt(2,bankAccount.getBankAccountId());
            preparedStatement.execute();
            bankAccount.setBalance(bankAccount.getBalance() - sum);

            String transactionName = "Payment";
            Transaction transaction = new Transaction(bankAccountId,transactionName,LocalDate.now(), sum);
            databaseConnection.addTransaction(transaction);

        }
    }

    public void payment(CreditCard creditCard,float sum) throws MyException, SQLException {
        if(sum > creditCard.getAvailableBalance()){
            System.out.println("Insufficient funds");
            throw new MyException("Insufficient funds");
        }
        else{
            PreparedStatement preparedStatement = connection.prepareStatement("update creditcard set availablebalance = ? where creditcardid = ?");
            preparedStatement.setFloat(1,creditCard.getAvailableBalance() - sum);
            preparedStatement.setInt(2,creditCard.getCardId());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement("update creditcard set duedate = ? where creditcardid = ?");
            preparedStatement.setDate(1,Date.valueOf(LocalDate.now().plusMonths(3)));
            preparedStatement.setInt(2,creditCard.getCardId());
            preparedStatement.execute();
            creditCard.setAvailableBalance(creditCard.getAvailableBalance() - sum);
            String transactionName = "Payment";
            Transaction transaction = new Transaction(creditCard.getBankAccountId(),transactionName,LocalDate.now(), sum);
            databaseConnection.addTransaction(transaction);
        }
    }


    public void withdraw(int cardId,int sum) throws MyException, SQLException {
        Card card = findCardById(cardId);
        BankAccount bankAccount = findBankAccountById(card.getBankAccountId());
        if (bankAccount != null) {
            if (card instanceof CreditCard) {
                System.out.println("You can only make payments with a credit card");
            } else {

                if (sum > bankAccount.getBalance()) {
                    String transactionName = "Failed attempt to withdraw money";
                    Transaction transaction = new Transaction(bankAccount.getBankAccountId(), transactionName, LocalDate.now(), 0);
                    databaseConnection.addTransaction(transaction);
                    throw new MyException("Insufficient funds");

                } else {
                    String transactionName = "Money withdrawal";
                    PreparedStatement preparedStatement = connection.prepareStatement("update bankaccount set balance = ? where bankaccountid = ?");
                    preparedStatement.setFloat(1,bankAccount.getBalance() - sum);
                    preparedStatement.setInt(2,bankAccount.getBankAccountId());
                    preparedStatement.execute();
                    bankAccount.setBalance(bankAccount.getBalance() - sum);
                    Transaction transaction = new Transaction(bankAccount.getBankAccountId(), transactionName, LocalDate.now(), sum);
                    databaseConnection.addTransaction(transaction);
                }
            }
        }
    }




    public float checkBalance(int bankAccountId){
        ArrayList<BankAccount> allBankAccounts = new ArrayList<>();
        try {
            allBankAccounts = getFromDatabase.getBankAccounts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(BankAccount bankAccount : allBankAccounts){
            if(bankAccount.getBankAccountId() == bankAccountId)
                return bankAccount.getBalance();
        }
        return 0;
    }



    public void transfer(int transferFromBankAccountId,int transferToBankAccountId,float sum) throws MyException {
        BankAccount transferFrom = findBankAccountById(transferFromBankAccountId);
        BankAccount transferTo = findBankAccountById(transferToBankAccountId);
        if(transferFrom != null && transferTo != null){
            if(transferFrom.getBalance() < sum) {
                String transactionName = "Failed attempt to transfer money";
                Transaction transaction = new Transaction(transferFromBankAccountId,transactionName, LocalDate.now(),0);
                try {
                    databaseConnection.addTransaction(transaction);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                throw new MyException("Not enough money to transfer.");

            }
            else{
                String transactionName = "Money transfer";
                MoneyTransfer transfer = new MoneyTransfer(transferFromBankAccountId,transactionName, LocalDate.now(),sum,transferToBankAccountId);
                databaseConnection.addMoneyTransfer(transfer);
                try {
                    updateSold(transferFromBankAccountId,transferFrom.getBalance() - sum);
                    updateSold(transferToBankAccountId,transferTo.getBalance() + sum);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        else{
            System.out.println("Bank account does not exist");
        }
    }

    public void updateSold(int bankAccountId,float sold) throws SQLException {
        BankAccount bankAccount = findBankAccountById(bankAccountId);
        PreparedStatement preparedStatement = connection.prepareStatement("update bankaccount set balance = ? where bankaccountid = ?");
        preparedStatement.setFloat(1,sold);
        preparedStatement.setInt(2,bankAccountId);
        preparedStatement.execute();

    }

    public AccountStatement accountStatement(int bankAccountId,int month) {
        ArrayList<Transaction> accountTransactions = new ArrayList<>();
        try {
            accountTransactions = getFromDatabase.getTransactionsOfBankAccount(bankAccountId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Transaction transaction : transactions)
            if (transaction.getBankAccountId() == bankAccountId) {
                int date = transaction.getDate().getMonthValue();
                if (date == month)
                    accountTransactions.add(transaction);

            }
        AccountStatement accountStatement = new AccountStatement(bankAccountId, LocalDate.now(), accountTransactions);
        accountStatements.add(accountStatement);
        return accountStatement;
    }

    public void changePassword(int appAccountId,String newPassword) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update appaccount set password = ? where appaccountid = ?");
        preparedStatement.setString(1,newPassword);
        preparedStatement.setInt(2,appAccountId);
        preparedStatement.execute();
    }



}

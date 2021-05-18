package com.company;

import com.company.Database.DatabaseConnection;
import com.company.Database.GetFromDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

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


    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();
    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<AccountStatement> accountStatements = new ArrayList<>();
    ArrayList<AppAccount> appAccounts = new ArrayList<>();
    ArrayList<CreditCard> creditCards = new ArrayList<>();
    ArrayList<DebitCard> debitCards = new ArrayList<>();
    ArrayList<SavingAccount> savingAccounts = new ArrayList<>();
    ArrayList<BankAccount> allBankAccounts = new ArrayList<>();
    ArrayList<Card> allCards = new ArrayList<>();


    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<DebitCard> getDebitCards() {
        return debitCards;
    }

    public void setDebitCards(ArrayList<DebitCard> debitCards) {
        this.debitCards = debitCards;
    }

    public ArrayList<SavingAccount> getSavingAccounts() {
        return savingAccounts;
    }

    public void setSavingAccounts(ArrayList<SavingAccount> savingAccounts) {
        this.savingAccounts = savingAccounts;
    }

    public ArrayList<AccountStatement> getAccountStatements() {
        return accountStatements;
    }

    public void setAccountStatements(ArrayList<AccountStatement> accountStatements) {
        this.accountStatements = accountStatements;
    }
    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<AppAccount> getAppAccounts() {
        return appAccounts;
    }

    public void setAppAccounts(ArrayList<AppAccount> appAccounts) {
        this.appAccounts = appAccounts;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(ArrayList<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }


    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public void setAllCards(ArrayList<Card> allCards) {
        this.allCards = allCards;
    }
    public ArrayList<BankAccount> getAllBankAccounts() {
        return allBankAccounts;
    }

    public void setAllBankAccounts(ArrayList<BankAccount> allBankAccounts) {
        this.allBankAccounts = allBankAccounts;
    }




    //Display functions
    public void displayClients(ArrayList<Client> clients){
        System.out.println("Clients: ");
        for(Client client : clients){
            System.out.println(client);
        }
        System.out.println();
    }

    public void displayBankAccounts(ArrayList<BankAccount> bankAccounts){
        System.out.println("Bank accounts: ");
        for(BankAccount bankAccount : bankAccounts){
            System.out.println(bankAccount);
        }
        System.out.println();
    }

    public void displaySavingAccounts(ArrayList<SavingAccount> savingAccounts){
        System.out.println("Saving accounts: ");
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

    public void displayAppAccounts(ArrayList<AppAccount> appAccounts){
        System.out.println("App accounts: ");
        for(AppAccount appAccount : appAccounts){
            System.out.println(appAccount);
        }
        System.out.println();
    }


    public void displayCards(){
        System.out.println("All cards: ");
        for(Card card : allCards){
            System.out.println(card);
        }
        System.out.println();
    }

    public void displayCreditCards(ArrayList<CreditCard>creditCards){
        System.out.println("Credit cards: ");
        for(CreditCard creditCard : creditCards){
            System.out.println(creditCard);
        }
        System.out.println();
    }
    public void displayDebitCards(ArrayList<DebitCard> debitCards){
        System.out.println("Debit cards: ");
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


    //Sort clients list by last name
    public static void sort(ArrayList<Client> list)
    {

        list.sort(Comparator.comparing(Client::getLastName));

    }




    //"Find" functions

    public AppAccount findAppAccountByAccessToken(int accessToken){
        ArrayList<AppAccount> allAppAccounts = new ArrayList<>();
        try {
            allAppAccounts = getFromDatabase.getAppAccounts();
            for(AppAccount appAccount : allAppAccounts){
                if(appAccount.getAccessToken() == accessToken)
                    System.out.println(appAccount);
                    return appAccount;
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
            System.out.println(client);
            return client;
        }

        return null;
    }

    public BankAccount findBankAccount(int id){
        ArrayList<BankAccount> allBankAccounts = new ArrayList<>();
        try {
            System.out.println(allBankAccounts);
            allBankAccounts = getFromDatabase.getBankAccounts();
            for(BankAccount bankAccount : allBankAccounts){
                if (bankAccount.getBankAccountId() == id)
                    return bankAccount;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public Client findClientByBankAccountId(int bankAccountId){
        for(Client client : clients){
            for(BankAccount bankAccount : client.getBankAccounts()){
                if(bankAccount.getBankAccountId() == bankAccountId)
                    return client;
            }
        }
        return null;
    }

    public BankAccount findBankAccountByIban(String iban){
        for(BankAccount bankAccount : allBankAccounts){
            if(bankAccount.getIBAN().compareTo(iban) == 0)
                return bankAccount;
        }
        return null;
    }


    public Card findCardById(int cardId){
        for(Card card : allCards){
            if(card.getCardId() == cardId)
                return card;

        }
        return null;
    }


    public Card findCardByNumber(long cardNumber){
        for(Card card : allCards)
            if(card.getCardNumber() == cardNumber)
                return card;
        return null;
    }


    //"Add" functions


    public void addClient( String firstName, String lastName, String phoneNumber, String email, long PNC){
        Client client = new Client(firstName, lastName, phoneNumber, email,PNC);
        clients.add(client);

    }


    public void addBankAccount(int clientId) throws MyException {
        try {
            if (findClientById(clientId) != null) {
                LocalDate openingDate = LocalDate.now();
                BankAccount bankAccount = new BankAccount( openingDate);
                bankAccounts.add(bankAccount);
                Client client = findClientById(clientId);
                client.getBankAccounts().add(bankAccount);
                databaseConnection.addBankAccount(bankAccount,clientId);


            }
            else{
                System.out.println("Client does not exist!");
                throw new MyException("Client does not exist");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void addAppAccount(int clientId) throws MyException {
        try {
            if(findClientById(clientId) != null) { ;
                Client client = findClientById(clientId);
                System.out.println(client);
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
        if(findBankAccount(bankAccountId) != null){
            LocalDate expirationDate = LocalDate.now().plusYears(4);
            CreditCard creditCard = new CreditCard(bankAccountId,expirationDate,expirationDate);
            creditCards.add(creditCard);
            BankAccount bankAccount = findBankAccount(bankAccountId);
            bankAccount.addCard(creditCard);
            allCards.add(creditCard);
        }
        else{
            System.out.println("Cannot add credit card. This bank account does not exist");
            throw new MyException("Bank account does not exist");
        }
    }

    public void addDebitCard(int bankAccountId) throws MyException {
        if(findBankAccount(bankAccountId) != null){
            LocalDate expirationDate = LocalDate.now().plusYears(4);
            DebitCard debitCard = new DebitCard(bankAccountId,expirationDate);
            debitCards.add(debitCard);
            BankAccount bankAccount = findBankAccount(bankAccountId);
            bankAccount.addCard(debitCard);
            allCards.add(debitCard);
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

    public void addFunds(int bankAccountId,int sum){
        BankAccount bankAccount = findBankAccount(bankAccountId);
        if(bankAccount != null){
            String transactionName = "Add funds";
            Transaction transaction = new Transaction(bankAccountId,transactionName,LocalDate.now(), ((float) sum));
            bankAccount.setBalance(bankAccount.getBalance() + sum);
            transactions.add(transaction);
        }
        else{
            System.out.println("Cannot add funds. This bank account does not exist");
        }
    }

    public void payment(int bankAccountId,float sum) throws MyException {
        BankAccount bankAccount = findBankAccount(bankAccountId);
        if(sum > bankAccount.getBalance()) {
            System.out.println("Insufficient funds");
            throw new MyException("Insufficient funds");
        }
        else{
            bankAccount.setBalance(bankAccount.getBalance() - sum);
            String transactionName = "Payment";
            Transaction transaction = new Transaction(bankAccountId,transactionName,LocalDate.now(), sum);
            transactions.add(transaction);

        }
    }


    public void withdraw(int cardId,int sum) throws MyException {
        Card card = findCardById(cardId);
        BankAccount bankAccount = findBankAccount(card.getBankAccountId());
        if (bankAccount != null) {
            if (card instanceof CreditCard) {
                System.out.println("You can only make payments with a credit card");
            } else {

                if (sum > bankAccount.getBalance()) {
                    String transactionName = "Failed attempt to withdraw money";
                    Transaction transaction = new Transaction(bankAccount.getBankAccountId(), transactionName, LocalDate.now(), 0);
                    transactions.add(transaction);
                    throw new MyException("Insufficient funds");

                } else {
                    String transactionName = "Money withdrawal";
                    bankAccount.setBalance(bankAccount.getBalance() - sum);
                    Transaction transaction = new Transaction(bankAccount.getBankAccountId(), transactionName, LocalDate.now(), sum);
                    transactions.add(transaction);
                }
            }
        }
    }




    public float checkBalance(int bankAccountId){
        for(BankAccount bankAccount : allBankAccounts){
            if(bankAccount.getBankAccountId() == bankAccountId)
                return bankAccount.getBalance();
        }
        return 0;
    }



    public void transfer(int transferFromBankAccountId,int transferToBankAccountId,float sum) throws MyException {
        BankAccount transferFrom = findBankAccount(transferFromBankAccountId);
        BankAccount transferTo = findBankAccount(transferToBankAccountId);
        if(transferFrom != null && transferTo != null){
            if(transferFrom.getBalance() < sum) {
                String transactionName = "Failed attempt to transfer money";
                Transaction transaction = new Transaction(transferFromBankAccountId,transactionName, LocalDate.now(),0);
                transactions.add(transaction);
                throw new MyException("Not enough money to transfer.");
            }
            else{
                String transactionName = "Money transfer";
                MoneyTransfer transfer = new MoneyTransfer(transferFromBankAccountId,transactionName, LocalDate.now(),sum,transferToBankAccountId);
                transactions.add(transfer);
                transferFrom.setBalance(transferFrom.getBalance() - sum);
                transferTo.setBalance(transferTo.getBalance() + sum);
            }
        }
        else{
            System.out.println("Bank account does not exist");
        }
    }

    public AccountStatement accountStatement(int bankAccountId,int month) {
        ArrayList<Transaction> accountTransactions = new ArrayList<>();
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



}

package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

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

    //Sort clients list by last name
    public static void sort(ArrayList<Client> list)
    {

        list.sort((o1, o2)
                -> o1.getLastName().compareTo(
                o2.getLastName()));
    }

    public void displayCards(ArrayList<Card> cards){
        System.out.println("Cards: ");
        for(Card card : cards){
            System.out.println(card);
        }
        System.out.println();
    }

    public AppAccount findAppAccountByAccessToken(int accessToken){
        for(AppAccount appAccount : appAccounts){
            if(appAccount.getAccessToken() == accessToken)
                    return appAccount;
        }
        return null;
    }

    public Client findClientByAppAccountId(int appAccountId){
        for(Client client : clients){
            if(client.getAppAccountId() == appAccountId){
                return client;
            }
        }
        return null;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

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

    public Client findClient(int id){
        for(Client client : clients){
            if (client.getClientId() == id)
                return client;
        }
        return null;
    }

    public BankAccount findBankAccount(int id){
        for(BankAccount bankAccount : allBankAccounts){
            if (bankAccount.getBankAccountId() == id)
                return bankAccount;
        }
        return null;
    }


    public void addClient( String firstName, String lastName, String phoneNumber, String email, long PNC){
        Client client = new Client(null, firstName, lastName, phoneNumber, email, 0,PNC);
        clients.add(client);
    }


    public void addBankAccount(int clientId) {
        if (findClient(clientId) != null) {
            LocalDate openingDate = LocalDate.now();
            BankAccount bankAccount = new BankAccount( openingDate);
            bankAccounts.add(bankAccount);
            Client client = findClient(clientId);
            client.addBankAccount(bankAccount);
            allBankAccounts.add(bankAccount);

        }
        else{
            System.out.println("Client does not exist!");
        }
    }

    public ArrayList<AppAccount> getAppAccounts() {
        return appAccounts;
    }

    public void setAppAccounts(ArrayList<AppAccount> appAccounts) {
        this.appAccounts = appAccounts;
    }

    public void addCard(int bankAccountId){
        if(findBankAccount(bankAccountId) != null){
            LocalDate expirationDate = LocalDate.now();
            expirationDate = expirationDate.plusYears(4);
            Card card = new Card(bankAccountId,expirationDate);
            cards.add(card);
            BankAccount bankAccount = findBankAccount(bankAccountId);
            bankAccount.addCard(card);
            allCards.add(card);
        }
        else{
            System.out.println("Bank Account does not exist");
        }
    }

    public void addAppAccount(int clientId){
        if(findClient(clientId) != null) {
            Client client = findClient(clientId);
            String password = "";
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
                appAccounts.add(appAccount);
                client.setAppAccountId(appAccount.getAppAccountId());
            } else {
                System.out.println("Clientul are deja un cont");
            }
        }
        else{
            System.out.println("Clientul nu exista");
        }
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(ArrayList<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public void addCreditCard(int bankAccountId){
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
        }
    }

    public void addDebitCard(int bankAccountId){
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
        }
    }

    public void addSavingAccount(int clientId){
        if(findClient(clientId) != null){
            Client client = findClient(clientId);
            LocalDate openingDate = LocalDate.now();
            SavingAccount savingAccount = new SavingAccount(openingDate);
            savingAccounts.add(savingAccount);
            client.addBankAccount(savingAccount);
            allBankAccounts.add(savingAccount);
        }
        else {
            System.out.println("Cannot add this saving account. Client does not exist");
        }
    }

    public BankAccount findBankAccountByIban(String iban){
        for(BankAccount bankAccount : allBankAccounts){
            if(bankAccount.getIBAN().compareTo(iban) == 0)
                return bankAccount;
        }
        return null;
    }

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

    public void withdraw(int bankAccountId,int sum){
        BankAccount bankAccount = findBankAccount(bankAccountId);
        if (bankAccount != null){
            if(sum > bankAccount.getBalance()){
                System.out.println("Insufficient funds");
                String transactionName = "Failed attempt to withdraw money";
                Transaction transaction = new Transaction(bankAccountId,transactionName, LocalDate.now(),0);
                transactions.add(transaction);
            }
            else{
                String transactionName = "Money withdrawal";
                bankAccount.setBalance(bankAccount.getBalance() - sum);
                Transaction transaction = new Transaction(bankAccountId,transactionName, LocalDate.now(),sum);
                transactions.add(transaction);
            }
        }
        else{
            System.out.println("This account does not exist");
        }
    }

    public ArrayList<BankAccount> getAllBankAccounts() {
        return allBankAccounts;
    }

    public void setAllBankAccounts(ArrayList<BankAccount> allBankAccounts) {
        this.allBankAccounts = allBankAccounts;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public void setAllCards(ArrayList<Card> allCards) {
        this.allCards = allCards;
    }

    public Card findCardByNumber(long cardNumber){
        for(Card card : allCards)
            if(card.getCardNumber() == cardNumber)
                return card;
        return null;
    }

    public float checkBalance(int bankAccountId){
        for(BankAccount bankAccount : allBankAccounts){
            if(bankAccount.getBankAccountId() == bankAccountId)
                return bankAccount.getBalance();
        }
        return 0;
    }

//    public BankAccount findBankAccountByCardId(int cardId){
//        for()
//    }

    public void transfer(int transferFromBankAccountId,int transferToBankAccountId,float sum){
        BankAccount transferFrom = findBankAccount(transferFromBankAccountId);
        BankAccount transferTo = findBankAccount(transferToBankAccountId);
        if(transferFrom != null && transferTo != null){
            if(transferFrom.getBalance() < sum) {
                String transactionName = "Insufficient funds";
                System.out.println("Not enough money to transfer.");
                Transaction transaction = new Transaction(transferFromBankAccountId,transactionName, LocalDate.now(),0);
                transactions.add(transaction);

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



}

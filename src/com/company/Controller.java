package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();
    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<AccountStatement> accountStatements = new ArrayList<>();


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
        for(BankAccount bankAccount : bankAccounts){
            if (bankAccount.getBankAccountId() == id)
                return bankAccount;
        }
        return null;
    }

    public void addClient( String firstName, String lastName, String phoneNumber, String email){
        Client client = new Client(null, firstName, lastName, phoneNumber, email, 0);
        clients.add(client);
    }


    public void addBankAccount(String IBAN,int clientId) {
        if (findClient(clientId) != null) {
            LocalDate openingDate = LocalDate.now();
            BankAccount bankAccount = new BankAccount(null, IBAN, 0, openingDate);
            bankAccounts.add(bankAccount);
            Client client = findClient(clientId);
            client.addBankAccount(bankAccount);

        }
        else{
            System.out.println("Client does not exist!");
        }
    }

    public void addCard(int bankAccountId){
        if(findBankAccount(bankAccountId) != null){
            LocalDate expirationDate = LocalDate.now();
            expirationDate = expirationDate.plusYears(4);
            Card card = new Card(bankAccountId,expirationDate);
            cards.add(card);
            BankAccount bankAccount = findBankAccount(bankAccountId);
            bankAccount.addCard(card);
        }
        else{
            System.out.println("Bank Account does not exist");
        }
    }


}

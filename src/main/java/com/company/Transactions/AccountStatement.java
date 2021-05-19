package com.company.Transactions;


import java.time.LocalDate;
import java.util.ArrayList;

public class AccountStatement {
    private int AccountStatementId;
    private static int id = 0;
    private int bankAccountId;
    private LocalDate date;
    private ArrayList<Transaction> transactions;

    public AccountStatement(int accountStatementId, int bankAccountId, LocalDate date, ArrayList<Transaction> transactions) {
        AccountStatementId = accountStatementId;
        this.bankAccountId = bankAccountId;
        this.date = date;
        this.transactions = transactions;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }



    public AccountStatement(int bankAccountId, LocalDate date,ArrayList<Transaction> transactions) {
        AccountStatementId = ++id;
        this.bankAccountId = bankAccountId;
        this.date = date;
        this.transactions = transactions;
    }

    public int getAccountStatementId() {
        return AccountStatementId;
    }

    public void setAccountStatementId(int accountStatementId) {
        AccountStatementId = accountStatementId;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AccountStatement.id = id;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AccountStatement{" +
                "AccountStatementId=" + AccountStatementId +
                ", bankAccountId=" + bankAccountId +
                ", date=" + date +
                ",transactions: " + transactions +
                '}';
    }
}

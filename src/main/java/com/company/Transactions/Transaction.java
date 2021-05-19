package com.company.Transactions;

import java.time.LocalDate;


public class Transaction {
    private int transactionId;
    private static int id = 0;
    private String transactionName;
    private int bankAccountId;
    private LocalDate date;
    private float sold;

    public Transaction( int  bankAccountId,String transactionName, LocalDate date, float sold) {
        this.transactionId = ++id;
        this.transactionName = transactionName;
        this.bankAccountId = bankAccountId;
        this.date = date;
        this.sold = sold;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
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

    public Transaction(int transactionId, String transactionName, int bankAccountId, LocalDate date, float sold) {
        this.transactionId = transactionId;
        this.transactionName = transactionName;
        this.bankAccountId = bankAccountId;
        this.date = date;
        this.sold = sold;
    }

    public float getSold() {
        return sold;
    }

    public void setSold(float sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionName='" + transactionName + '\'' +
                ", bankAccountId=" + bankAccountId +
                ", date=" + date +
                ", sold=" + sold +
                '}';
    }
}

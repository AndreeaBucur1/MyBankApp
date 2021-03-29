package com.company;

import java.time.LocalDate;


public class Transaction {
    public int transactionId;
    private Account bankAccountId;
    private LocalDate date;
    private float sold;

    public Transaction(int transactionId, Account bankAccountId, LocalDate date, float sold) {
        this.transactionId = transactionId;
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

    public Account getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Account bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
                ", bankAccountId=" + bankAccountId +
                ", date=" + date +
                ", sold=" + sold +
                '}';
    }
}

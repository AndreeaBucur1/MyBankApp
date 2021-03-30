package com.company;

import java.time.LocalDate;


public class Transaction {
    private int transactionId;
    private static int id = 0;
    private String transactionName;
    private AppAccount bankAccountId;
    private LocalDate date;
    private float sold;

    public Transaction( BankAccount bankAccountId, LocalDate date, float sold) {
        this.transactionId = ++id;
        this.bankAccountId = bankAccountId;
        this.date = date;
        this.sold = sold;
    }


    public BankAccount getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(BankAccount bankAccountId) {
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

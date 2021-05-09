package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class BankAccount {
    protected int bankAccountId;
    protected static int id = 0;
    protected ArrayList<Card> cards;
    protected String IBAN;
    protected static long nrIban = 10000000;
    protected float balance = 0;
    protected LocalDate openingDate;

    public BankAccount(int bankAccountId, String IBAN, float balance, LocalDate openingDate) {
        this.bankAccountId = bankAccountId;
        cards = new ArrayList<>();
        this.cards = cards;
        this.IBAN = IBAN;
        this.balance = balance;
        this.openingDate = openingDate;
        ++id;
        ++nrIban;
    }

    public BankAccount( LocalDate openingDate) {
        cards = new ArrayList<>();
        this.bankAccountId = ++id;
        this.cards = cards;
        this.IBAN = "ROMBA" + (nrIban++);
        this.balance = balance;
        this.openingDate = openingDate;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "bankAccountId=" + bankAccountId +
                ", cards=" + cards +
                ", IBAN='" + IBAN + '\'' +
                ", balance=" + balance +
                ", openingDate=" + openingDate +
                '}';
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        BankAccount.id = id;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }


    public void addCard(Card card) {
        cards.add(card);
    }


}

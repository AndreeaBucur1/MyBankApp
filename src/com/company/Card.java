package com.company;

import java.time.LocalDate;

public class Card {
    protected int cardId;
    private static int id = 0;
    protected int bankAccountId;
    protected int cardNumber;
    protected int CVV;
    protected LocalDate expirationDate;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Card(int cardId, int bankAccountId, int cardNumber, int CVV, LocalDate expirationDate) {
        this.cardId = ++id;
        this.bankAccountId = bankAccountId;
        this.cardNumber = cardNumber;
        this.CVV = CVV;
        this.expirationDate = expirationDate;
    }
}

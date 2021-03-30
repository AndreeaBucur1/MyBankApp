package com.company;

import java.time.LocalDate;

public class Card {
    protected int cardId;
    private static int id = 0;
    private static int cvv = 1000;
    private static long number = 1000000000;
    protected int bankAccountId;
    protected long cardNumber;
    protected int CVV;
    protected LocalDate expirationDate;

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", bankAccountId=" + bankAccountId +
                ", cardNumber=" + cardNumber +
                ", CVV=" + CVV +
                ", expirationDate=" + expirationDate +
                '}';
    }

    public int getCardId() {
        return cardId;
    }


    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public long getCardNumber() {
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

    public Card( int bankAccountId,LocalDate expirationDate) {
        this.cardId = ++id;
        this.bankAccountId = bankAccountId;
        this.cardNumber = number++;
        this.CVV = ++cvv;
        this.expirationDate = expirationDate;
    }
}

package com.company;

import java.time.LocalDate;

public class CreditCard extends Card{
    private float availableBalance;
    private LocalDate dueDate;

    public CreditCard(int cardId, int bankAccountId, int cardNumber, int CVV, LocalDate expirationDate, float availableBalance, LocalDate dueDate) {
        super(cardId, bankAccountId, cardNumber, CVV, expirationDate);
        this.availableBalance = availableBalance;
        this.dueDate = dueDate;
    }

    public float getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(float availableBalance) {
        this.availableBalance = availableBalance;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardId=" + cardId +
                ", bankAccountId=" + bankAccountId +
                ", cardNumber=" + cardNumber +
                ", CVV=" + CVV +
                ", expirationDate=" + expirationDate +
                ", availableBalance=" + availableBalance +
                ", dueDate=" + dueDate +
                '}';
    }
}

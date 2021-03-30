package com.company;

import java.time.LocalDate;

public class CreditCard extends Card{
    private float availableBalance = 10000;
    private LocalDate dueDate;

    public CreditCard(int bankAccountId, LocalDate expirationDate, LocalDate dueDate) {
        super(bankAccountId,expirationDate);
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

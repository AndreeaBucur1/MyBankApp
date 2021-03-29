package com.company;

import java.time.LocalDate;

public class DebitCard extends Card{
    public float overDraftLimit;
    public float transactionCommission;

    public DebitCard(int cardId, int bankAccountId, int cardNumber, int CVV, LocalDate expirationDate, float overDraftLimit, float transactionCommission) {
        super(cardId, bankAccountId, cardNumber, CVV, expirationDate);
        this.overDraftLimit = overDraftLimit;
        this.transactionCommission = transactionCommission;
    }

    public float getOverDraftLimit() {
        return overDraftLimit;
    }

    public void setOverDraftLimit(float overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }

    public float getTransactionCommission() {
        return transactionCommission;
    }

    public void setTransactionCommission(float transactionCommission) {
        this.transactionCommission = transactionCommission;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "cardId=" + cardId +
                ", bankAccountId=" + bankAccountId +
                ", cardNumber=" + cardNumber +
                ", CVV=" + CVV +
                ", expirationDate=" + expirationDate +
                ", overDraftLimit=" + overDraftLimit +
                ", transactionCommission=" + transactionCommission +
                '}';
    }
}

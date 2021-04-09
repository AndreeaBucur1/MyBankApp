package com.company;

import java.time.LocalDate;

public class DebitCard extends Card{
    private final float overDraftLimit = 5000;
    private final float transactionCommission = 0.5f;


    public DebitCard(int bankAccountId, LocalDate expirationDate) {
        super( bankAccountId, expirationDate);
    }
    public float getOverDraftLimit() {
        return overDraftLimit;
    }
    public float getTransactionCommission() {
        return transactionCommission;
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

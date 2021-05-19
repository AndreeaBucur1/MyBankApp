package com.company.Cards;

import com.company.Cards.Card;

import java.time.LocalDate;

public class DebitCard extends Card {
    private float overDraftLimit = 5000;
    private float transactionCommission = 0.0f;

    public DebitCard(int cardId, int bankAccountId, long cardNumberr, int CVV, LocalDate expirationDate) {
        super(cardId, bankAccountId, cardNumberr, CVV, expirationDate);

    }

    public DebitCard(int cardId, int bankAccountId, long cardNumberr, int CVV, LocalDate expirationDate,float overDraftLimit,float transactionCommission) {
        super(cardId, bankAccountId, cardNumberr, CVV, expirationDate);
        this.overDraftLimit = overDraftLimit;
        this.transactionCommission = transactionCommission;
    }

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

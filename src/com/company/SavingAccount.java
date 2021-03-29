package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class SavingAccount extends BankAccount {
    private float commissionPct;

    public SavingAccount(int bankAccountId, ArrayList<Card> cards, String IBAN, float balance, LocalDate openingDate) {
        super( cards, IBAN, balance, openingDate);
    }
}

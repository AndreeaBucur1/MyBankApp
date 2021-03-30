package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class SavingAccount extends BankAccount {
    private float commissionPct = 1.5f;

    public float getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(float commissionPct) {
        this.commissionPct = commissionPct;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "bankAccountId=" + bankAccountId +
                ", cards=" + cards +
                ", IBAN='" + IBAN + '\'' +
                ", balance=" + balance +
                ", openingDate=" + openingDate +
                ", commissionPct=" + commissionPct +
                '}';
    }

    public SavingAccount(LocalDate openingDate) {
        super(openingDate);
        this.commissionPct = commissionPct;
    }
}

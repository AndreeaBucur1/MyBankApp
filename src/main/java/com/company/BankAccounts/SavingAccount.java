package com.company.BankAccounts;

import com.company.BankAccounts.BankAccount;

import java.time.LocalDate;

public class SavingAccount extends BankAccount {
    private float commissionPct = 1.5f;

    public float getCommissionPct() {
        return commissionPct;
    }

    public SavingAccount(int bankAccountId, String IBAN, float balance, LocalDate openingDate, float commissionPct) {
        super(bankAccountId, IBAN, balance, openingDate);
        this.commissionPct = commissionPct;
    }

    public SavingAccount(LocalDate openingDate, float commissionPct) {
        super(openingDate);
        this.commissionPct = commissionPct;
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

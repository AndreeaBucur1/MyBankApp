package com.company;


import java.time.LocalDate;

public class AccountStatement {
    private int AccountStatementId;
    private static int id = 0;
    private int bankAccountId;
    private LocalDate date;

    public AccountStatement(int accountStatementId, int bankAccountId, LocalDate date) {
        AccountStatementId = ++id;
        this.bankAccountId = bankAccountId;
        this.date = date;
    }

    public int getAccountStatementId() {
        return AccountStatementId;
    }

    public void setAccountStatementId(int accountStatementId) {
        AccountStatementId = accountStatementId;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        AccountStatement.id = id;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AccountStatement{" +
                "AccountStatementId=" + AccountStatementId +
                ", bankAccountId=" + bankAccountId +
                ", date=" + date +
                '}';
    }
}

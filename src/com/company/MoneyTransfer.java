package com.company;

import java.time.LocalDate;

public class MoneyTransfer extends Transaction{
    private int transferToBankAccountId;

    public int getTransferToBankAccountId() {
        return transferToBankAccountId;
    }

    public void setTransferToBankAccountId(int transferToBankAccountId) {
        this.transferToBankAccountId = transferToBankAccountId;
    }

    public MoneyTransfer(int bankAccountId, String transactionName, LocalDate date, float sold, int transferToBankAccountId) {
        super(bankAccountId, transactionName, date, sold);
        this.transferToBankAccountId = transferToBankAccountId;
    }
}

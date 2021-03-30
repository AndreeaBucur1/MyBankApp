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

    @Override
    public String toString() {
        return "MoneyTransfer{" +
                " transferFromBankAccountId = " + super.getBankAccountId() +
                " ,transferToBankAccountId = " + transferToBankAccountId +
                " ,transactionId = " + super.getTransactionId() +
                " , transactionName = " + super.getTransactionName() + '\'' +
                " , bankAccountId = " + super.getBankAccountId() +
                " , date = " + super.getDate() +
                " , sold = " + getSold() +
                '}';


    }
}

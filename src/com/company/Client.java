package com.company;

import java.util.ArrayList;

public class Client {

    private int clientId ;
    private static int  id = 0;
    private ArrayList<BankAccount> bankAccounts;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int appAccountId;


    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", bankAccounts=" + bankAccounts +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", appAccountId=" + appAccountId +
                '}';
    }

    public Client( ArrayList<BankAccount> bankAccounts, String firstName, String lastName, String phoneNumber, String email, int appAccountId) {
        bankAccounts = new ArrayList<>();
        this.clientId = ++id;
        this.bankAccounts = bankAccounts;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.appAccountId = appAccountId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAppAccountId() {
        return appAccountId;
    }

    public void setAppAccountId(int appAccountId) {
        this.appAccountId = appAccountId;
    }

    public void addBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }
}

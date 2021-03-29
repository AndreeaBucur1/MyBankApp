package com.company;

import java.util.Date;

public class Account {
    private int appAccountId;
    private static int id = 0;
    private int accessToken;
    private String password;

    public Account(int appAccountId, int accessToken, String password) {
        this.appAccountId = ++id;
        this.accessToken = accessToken;
        this.password = password;
    }

    public int getAppAccountId() {
        return appAccountId;
    }

    public void setAppAccountId(int appAccountId) {
        this.appAccountId = appAccountId;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Account.id = id;
    }

    public int getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(int accessToken) {
        this.accessToken = accessToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "appAccountId=" + appAccountId +
                ", accessToken=" + accessToken +
                ", password='" + password + '\'' +
                '}';
    }
}

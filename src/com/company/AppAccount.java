package com.company;

public class AppAccount {
    private int appAccountId;
    private static int id = -1;
    private int accessToken;
    private static int token = 100000;

    public AppAccount(int appAccountId, int accessToken, String password) {
        this.appAccountId = appAccountId;
        this.accessToken = accessToken;
        this.password = password;
        ++id;
    }

    private String password;

    public AppAccount(String password) {
        this.appAccountId = ++id;
        this.accessToken = token++;
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
        AppAccount.id = id;
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

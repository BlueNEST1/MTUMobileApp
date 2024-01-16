package com.example.mobileproject2;

public class Broker { // a Model class for brokers

    private String Email;
    private String UserName;
    private String Password;

    public Broker() {
    }

    public Broker(String email, String userName, String password) {
        Email = email;
        UserName = userName;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

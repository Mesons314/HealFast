package com.HealQueue.Auth.DTO;

public class AuthRequest {
    private String userName;
    private String password;

    public AuthRequest(String userName,String password) {
        this.password = password;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

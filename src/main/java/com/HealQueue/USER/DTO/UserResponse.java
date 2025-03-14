package com.HealQueue.USER.DTO;

import com.HealQueue.Auth.Entity.UserInfo;

public class UserResponse {

    private String userName;
    private String address;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;

    public UserResponse(UserInfo userInfo) {
        this.gender = userInfo.getGender();
        this.address = userInfo.getAddress();
        this.dob = userInfo.getDob();
        this.firstName = userInfo.getFirstName();
        this.lastName = userInfo.getLastName();
        this.userName = userInfo.getUserName();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGender(String gender){ this.gender = gender;}

    public String getGender(){return gender;}


}

package com.HealQueue.USER.DTO;

import com.HealQueue.USER.Entity.UserInfo;

public class UserResponseDTO{
    private Long profileId;       // ClinicInfo id
    private String userName;      // from UserAccountData
    private String email;         // from UserAccountData
    private String phoneNo;       // from UserAccountData
    private String address;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;

    public UserResponseDTO(UserInfo userInfo) {
    }

    public UserResponseDTO(){

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
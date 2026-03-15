package com.HealQueue.CLINIC.DTO;

import com.HealQueue.CLINIC.Entity.ClinicInfo;

public class ClinicResponseDTO {

    private Long profileId;       // ClinicInfo id
    private String userName;      // from UserAccountData
    private String email;         // from UserAccountData
    private String phoneNo;       // from UserAccountData

    private String clinicName;
    private String address;
    private String speciality;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;

    public ClinicResponseDTO(ClinicInfo clinicInfo) {
        this.profileId = clinicInfo.getId();

        // 🔥 Pull from UserAccountData
        this.userName = clinicInfo.getUserAccountData().getUserName();
        this.email = clinicInfo.getUserAccountData().getEmail();
        this.phoneNo = clinicInfo.getUserAccountData().getPhoneNo();

        // Profile data
        this.clinicName = clinicInfo.getClinicName();
        this.address = clinicInfo.getAddress();
        this.speciality = clinicInfo.getSpeciality();
        this.firstName = clinicInfo.getFirstName();
        this.lastName = clinicInfo.getLastName();
        this.gender = clinicInfo.getGender();
        this.dob = clinicInfo.getDob();
    }

    public ClinicResponseDTO() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getDob() {
        return dob;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

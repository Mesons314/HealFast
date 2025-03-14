package com.HealQueue.Auth.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Yash")
public class ClinicInfo {

    @Id
    private long id;
    private String userName;
    private String password;
    private String address;
    private String graduationDegree;
    private String firstName;
    private String lastName;
    private String speciality;
    private String gender;
    private String dob;
    private String clinicName;
    private String phoneNo;
    private String clinicPhoneNo;

    public ClinicInfo() {
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getClinicPhoneNo() {
        return clinicPhoneNo;
    }

    public void setClinicPhoneNo(String clinicPhoneNo) {
        this.clinicPhoneNo = clinicPhoneNo;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGraduationDegree() {
        return graduationDegree;
    }

    public void setGraduationDegree(String graduationDegree) {
        this.graduationDegree = graduationDegree;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.HealQueue.Auth.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Clinics")
public class ClinicInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String graduationDegree;
    @Column(nullable = false)
    private String firstName;

    private String lastName;
    @Column(nullable = false)
    private String speciality;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String dob;
    @Column(nullable = false)
    private String clinicName;
    @Column(nullable = false, unique = true)
    private String phoneNo;
    @Column(nullable = false)
    private String clinicPhoneNo;

    @Column(nullable = false)
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

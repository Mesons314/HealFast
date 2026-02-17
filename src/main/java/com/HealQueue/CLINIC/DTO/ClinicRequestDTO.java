package com.HealQueue.CLINIC.DTO;

import com.HealQueue.Auth.Entity.UserAccountData;
import jakarta.persistence.Column;

public class ClinicRequestDTO {

    private String address;
    private String graduationDegree;
    private String firstName;
    private String lastName;
    private String speciality;
    private String gender;
    private String dob;
    private String clinicName;
    private String clinicPhoneNo;

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

    public String getClinicPhoneNo() {
        return clinicPhoneNo;
    }

    public void setClinicPhoneNo(String clinicPhoneNo) {
        this.clinicPhoneNo = clinicPhoneNo;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGraduationDegree() {
        return graduationDegree;
    }

    public void setGraduationDegree(String graduationDegree) {
        this.graduationDegree = graduationDegree;
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
}

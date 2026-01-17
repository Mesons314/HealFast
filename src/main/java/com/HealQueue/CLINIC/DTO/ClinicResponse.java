package com.HealQueue.CLINIC.DTO;

import com.HealQueue.Auth.Entity.ClinicInfo;

public class ClinicResponse {

    private long id;
    private final String userName;
    private final String gender;
    private final String dob;
    private final String graduationDegree;
    private final String firstName;
    private final String lastName;
    private final String speciality;
    private final String clinicName;
    private final String address;
    private final String clinicPhoneNo;
    private final String phoneNo;
//    private double latitude;
//    private double longitude;

    public ClinicResponse(ClinicInfo clinicInfo,double latitude, double longitude){
        this.id = clinicInfo.getId();
        this.clinicPhoneNo = clinicInfo.getClinicPhoneNo();
        this.dob = clinicInfo.getDob();
        this.gender = clinicInfo.getGender();
        this.clinicName = clinicInfo.getClinicName();
        this.userName =  clinicInfo.getUserName();
         this.address = clinicInfo.getAddress();
         this.graduationDegree = clinicInfo.getGraduationDegree();
         this.firstName = clinicInfo.getFirstName();
         this.lastName = clinicInfo.getLastName();
         this.speciality = clinicInfo.getSpeciality();
         this.phoneNo = clinicInfo.getPhoneNo();
//         this.latitude = latitude;
//         this.longitude = longitude;
    }

    public ClinicResponse(ClinicInfo clinicInfo) {
        this.id = clinicInfo.getId();
        this.clinicPhoneNo = clinicInfo.getClinicPhoneNo();
        this.dob = clinicInfo.getDob();
        this.gender = clinicInfo.getGender();
        this.clinicName = clinicInfo.getClinicName();
        this.userName =  clinicInfo.getUserName();
        this.address = clinicInfo.getAddress();
        this.graduationDegree = clinicInfo.getGraduationDegree();
        this.firstName = clinicInfo.getFirstName();
        this.lastName = clinicInfo.getLastName();
        this.speciality = clinicInfo.getSpeciality();
        this.phoneNo = clinicInfo.getPhoneNo();
    }

    public String getAddress() {
        return address;
    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }

//    public void setAddress(String address) {
//        this.address = address;
//    }

    public String getClinicName() {
        return clinicName;
    }

//    public void setClinicName(String clinicName) {
//        this.clinicName = clinicName;
//    }

    public String getClinicPhoneNo() {
        return clinicPhoneNo;
    }

//    public void setClinicPhoneNo(String clinicPhoneNo) {
//        this.clinicPhoneNo = clinicPhoneNo;
//    }

    public String getDob() {
        return dob;
    }
//
//    public void setDob(String dob) {
//        this.dob = dob;
//    }

    public String getFirstName() {
        return firstName;
    }

//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }

    public String getGender() {
        return gender;
    }

//    public void setGender(String gender) {
//        this.gender = gender;
//    }

    public String getGraduationDegree() {
        return graduationDegree;
    }

//    public void setGraduationDegree(String graduationDegree) {
//        this.graduationDegree = graduationDegree;
//    }

    public String getPhoneNo() {
        return phoneNo;
    }

//    public void setPhoneNo(String phoneNo) {
//        this.phoneNo = phoneNo;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    public String getSpeciality() {
        return speciality;
    }

//    public void setSpeciality(String speciality) {
//        this.speciality = speciality;
//    }

    public String getUserName() {
        return userName;
    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
}

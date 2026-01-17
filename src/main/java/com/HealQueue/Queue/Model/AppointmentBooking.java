package com.HealQueue.Queue.Model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointments")
public class AppointmentBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private long id;

    private String patientName;
    private String patientAge;
    private String patientGender;
    private String mobileNo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredAt;

    private Integer patientNo;
    private Long duration;
    private Long clinicId;
    private String clinicName;
    private String clinicAddress;
    private double latitude;
    private double longitude;
//
//    public AppointmentBooking() {
//        // Automatically set registeredAt when the object is created
//        this.registeredAt = LocalDateTime.now();
//    }

    public AppointmentBooking() {
    }

    public AppointmentBooking(
            String clinicAddress,
            Long clinicId,
            String clinicName,
            Long duration,
            String mobileNo,
            String patientAge,
            String patientGender,
            String patientName,
            Integer patientNo,
            LocalDateTime registeredAt) {
        this.clinicAddress = clinicAddress;
        this.clinicId = clinicId;
        this.clinicName = clinicName;
        this.duration = duration;
        this.mobileNo = mobileNo;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.patientName = patientName;
        this.patientNo = patientNo;
        this.registeredAt = LocalDateTime.now();
    }

    //    public AppointmentBooking(Integer patientNo, String mobileNo, String patientAge, String patientGender,
//                              String patientName, Long duration, Long clinicId) {
//        this.mobileNo = mobileNo;
//        this.patientAge = patientAge;
//        this.patientGender = patientGender;
//        this.patientName = patientName;
//        this.registeredAt = LocalDateTime.now(); // Automatically set
//        this.patientNo = patientNo;
//        this.duration = duration;
//    }

    // Getters and setters...

    public Integer getPatientNo() {
        return patientNo;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPatientNo(Integer patientNo) {
        this.patientNo = patientNo;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public long getId() {
        return id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}

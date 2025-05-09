package com.HealQueue.Queue.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "Appointments")
public class AppointmentBooking {

    @Id
    @Column(nullable = false,unique = true)
    private long id;

    private String patientName;
    private String patientAge;
    private String patientGender;
    private String mobileNo;
    private LocalDateTime registeredAt;
    private Integer patientNo;
    public AppointmentBooking() {

    }


    public AppointmentBooking(Integer patientNo,String mobileNo, Integer id, String patientAge, String patientGender, String patientName, LocalDateTime registeredAt) {
        this.mobileNo = mobileNo;
        this.id = id;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.patientName = patientName;
        this.registeredAt = registeredAt;
        this.patientNo = patientNo;
    }


    public Integer getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(Integer patientNo) {
        this.patientNo = patientNo;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

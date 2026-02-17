package com.HealQueue.Queue.Model;

import com.HealQueue.CLINIC.Entity.ClinicInfo;
import com.HealQueue.USER.Entity.UserInfo;
   // assuming you have Clinic entity
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class AppointmentBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* -------------------- RELATIONS -------------------- */

    // Who booked the appointment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    // Which clinic appointment belongs to
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private ClinicInfo clinic;

    /* -------------------- PATIENT DETAILS -------------------- */

    private String patientName;
    private String patientAge;
    private String patientGender;
    private String mobileNo;

    private Integer patientNo;
    private Long duration;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredAt;

    /* -------------------- AUTO TIMESTAMP -------------------- */

    @PrePersist
    protected void onCreate() {
        this.registeredAt = LocalDateTime.now();
    }

    /* -------------------- CONSTRUCTORS -------------------- */

    public AppointmentBooking() {}

    /* -------------------- GETTERS & SETTERS -------------------- */

    public Long getId() {
        return id;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public ClinicInfo getClinic() {
        return clinic;
    }

    public void setClinic(ClinicInfo clinic) {
        this.clinic = clinic;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(Integer patientNo) {
        this.patientNo = patientNo;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}

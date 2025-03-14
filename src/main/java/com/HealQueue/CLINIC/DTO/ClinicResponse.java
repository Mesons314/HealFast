package com.HealQueue.CLINIC.DTO;

import com.HealQueue.Auth.Entity.ClinicInfo;

public class ClinicResponse {
    private ClinicInfo clinicInfo;

    private String userName;
    private String password;

    private String gender;
    private String dob;

    private String graduationDegree;
    private String firstName;
    private String lastName;
    private String speciality;
    private String clinicName;
    private String address;
    private String clinicPhoneNo;

    public ClinicResponse(ClinicInfo clinicInfo){
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
    }
}

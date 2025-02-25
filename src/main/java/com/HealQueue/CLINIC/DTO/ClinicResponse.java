package com.HealQueue.CLINIC.DTO;

import com.HealQueue.Auth.Entity.ClinicInfo;

public class ClinicResponse {
    private ClinicInfo clinicInfo;

    private String userName;
    private String password;
    private String address;
    private String graduationDegree;
    private String firstName;
    private String lastName;
    private String speciality;
    public ClinicResponse(ClinicInfo clinicInfo){
        this.userName =  clinicInfo.getUserName();
         this.address = clinicInfo.getAddress();
         this.graduationDegree = clinicInfo.getGraduationDegree();
         this.firstName = clinicInfo.getFirstName();
         this.lastName = clinicInfo.getLastName();
         this.speciality = clinicInfo.getSpeciality();
    }
}

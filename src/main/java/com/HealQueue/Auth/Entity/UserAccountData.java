package com.HealQueue.Auth.Entity;

import com.HealQueue.CLINIC.Entity.ClinicInfo;
import com.HealQueue.USER.Entity.UserInfo;
import jakarta.persistence.*;

@Entity
public class UserAccountData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String phoneNo;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;

    @OneToOne(mappedBy = "userAccountData", cascade = CascadeType.ALL)
    private ClinicInfo clinicInfo;

    @OneToOne(mappedBy = "userAccountData", cascade = CascadeType.ALL)
    private UserInfo userInfo;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClinicInfo getClinicInfo() {
        return clinicInfo;
    }

    public void setClinicInfo(ClinicInfo clinicInfo) {
        this.clinicInfo = clinicInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

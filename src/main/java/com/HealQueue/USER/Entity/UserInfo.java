package com.HealQueue.USER.Entity;

import com.HealQueue.Auth.Entity.UserAccountData;
import jakarta.persistence.*;

@Entity
@Table(name = "UserTable")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private UserAccountData userAccountData;

    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String dob;


    public UserAccountData getUserAccountData() {
        return userAccountData;
    }

    public void setUserAccountData(UserAccountData userAccountData) {
        this.userAccountData = userAccountData;
    }

    public UserInfo() {
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

}

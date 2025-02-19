package com.HealQueue.Auth.Entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "healData")
public class UserInfo {

    @Id
    @Column(name = "user_id")
    private long id;

    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserInfo() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

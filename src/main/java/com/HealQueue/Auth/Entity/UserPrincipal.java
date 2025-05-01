package com.HealQueue.Auth.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    // Constructor for UserInfo (User)
    public UserPrincipal(UserInfo userInfo) {
        this.username = userInfo.getUserName();
        this.password = userInfo.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_"+userInfo.getRole())); // Assign role
    }

    // Constructor for ClinicInfo (Doctor)
    public UserPrincipal(ClinicInfo clinicInfo) {
        this.username = clinicInfo.getUserName();
        this.password = clinicInfo.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_"+clinicInfo.getRole())); // Assign role
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

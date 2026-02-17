package com.HealQueue.Auth.Service;

import com.HealQueue.Auth.Entity.UserAccountData;
import com.HealQueue.Auth.Repository.AuthRepo;
import com.HealQueue.Auth.Entity.UserPrincipal;
import com.HealQueue.CLINIC.Repository.ClinicRepo;
import com.HealQueue.USER.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService  implements UserDetailsService {

    @Autowired
    private AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountData userAccountData = authRepo.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("No Username found"));

        return new UserPrincipal(userAccountData);
    }
}

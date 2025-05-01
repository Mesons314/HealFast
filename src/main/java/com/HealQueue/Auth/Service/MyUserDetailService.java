package com.HealQueue.Auth.Service;

import com.HealQueue.Auth.Entity.ClinicInfo;
import com.HealQueue.Auth.Entity.UserInfo;
import com.HealQueue.Auth.Entity.UserPrincipal;
import com.HealQueue.Auth.Repository.ClinicRepo;
import com.HealQueue.Auth.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService  implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ClinicRepo clinicRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepo.findByUserName(username);
        if(userInfo!=null){
            return new UserPrincipal(userInfo);
        }
        ClinicInfo clinicInfo = clinicRepo.findByUserName(username);
        if(clinicInfo!=null){
            return new UserPrincipal(clinicInfo);
        }

        throw new RuntimeException("No Username found");
    }
}

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

import java.util.Optional;

@Service
public class MyUserDetailService  implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ClinicRepo clinicRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> optionalUser = userRepo.findByUserName(username);

        if(optionalUser.isPresent()){
            UserInfo userInfo = optionalUser.get();
            return new UserPrincipal(userInfo);
        }
        Optional<ClinicInfo> optionalClinicInfo = clinicRepo.findByUserName(username);
        if(optionalClinicInfo.isPresent()){
            ClinicInfo clinicInfo = optionalClinicInfo.get();
            return new UserPrincipal(clinicInfo);
        }

        throw new RuntimeException("No Username found");
    }
}

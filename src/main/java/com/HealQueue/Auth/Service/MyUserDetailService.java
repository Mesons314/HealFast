package com.HealQueue.Auth.Service;

import com.HealQueue.Auth.Entity.UserInfo;
import com.HealQueue.Auth.Entity.UserPrinciple;
import com.HealQueue.Auth.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userDetail")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = repo.findByUserName(username);
        if(user == null){
            System.out.println("User Not Found");
            throw  new UsernameNotFoundException("User not found");
        }
        return new UserPrinciple(user);
    }
}


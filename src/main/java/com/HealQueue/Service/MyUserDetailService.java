package com.HealQueue.Service;

import com.HealQueue.Entity.UserInfo;
import com.HealQueue.Entity.UserPrinciple;
import com.HealQueue.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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

package com.HealQueue.Service;

import com.HealQueue.DTO.AuthRequest;
import com.HealQueue.Entity.UserInfo;
import com.HealQueue.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicing {

    @Autowired
    private UserRepo repo;

    @Autowired
    private jwtService JwtService;

    @Autowired
    AuthenticationManager authManager;

    public UserInfo register(UserInfo user) {
        return repo.save(user);
    }


    public String verify(AuthRequest request) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));

        if(authentication.isAuthenticated()){
            return JwtService.generateToken(request.getUserName());
        }

        return "Fail";
    }

    public List<UserInfo> getUser() {
        return repo.findAll();
    }
}

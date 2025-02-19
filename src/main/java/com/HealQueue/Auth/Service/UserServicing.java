package com.HealQueue.Auth.Service;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.Role;
import com.HealQueue.Auth.Entity.UserInfo;
import com.HealQueue.Auth.Entity.UserPrinciple;
import com.HealQueue.Auth.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServicing {

    @Autowired
    private final UserRepo repo;

    @Autowired
    private final PasswordEncoder encoder;
    @Autowired
    private final jwtService JwtService;

    @Autowired
    AuthenticationManager authManager;

    public UserServicing(PasswordEncoder encoder, jwtService jwtService, UserRepo repo, AuthenticationManager authManager) {
        this.encoder = encoder;
        this.JwtService = jwtService;
        this.repo = repo;
        this.authManager = authManager;
    }

    public UserInfo register(UserInfo user, Role role) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(role);
        return repo.save(user);
    }


    public String verify(AuthRequest request) {
        UserInfo user = repo.findByUserName(request.getUserName());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(authentication.isAuthenticated()){
            return JwtService.generateToken(request.getUserName());
        }

        return "Fail";
    }

    public List<UserInfo> getUser() {
        return repo.findAll();
    }
}

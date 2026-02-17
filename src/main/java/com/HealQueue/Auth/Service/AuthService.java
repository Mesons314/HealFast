package com.HealQueue.Auth.Service;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.UserAccountData;
import com.HealQueue.Auth.Entity.UserPrincipal;
import com.HealQueue.Auth.Repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    @Autowired
    AuthRepo authRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JWTService jwtService;

    @Autowired
    private final AuthenticationManager authManager;

    public AuthService(PasswordEncoder passwordEncoder,
                       JWTService jwtService,
                       AuthenticationManager authManager){
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public UserAccountData registerUser(UserAccountData userAccountData) {
        if(authRepo.existsByUserName(userAccountData.getUserName())){
            throw new RuntimeException("UserName already exists");
        }
        userAccountData.setPassword(passwordEncoder.encode(userAccountData.getPassword()));
        authRepo.save(userAccountData);
        return userAccountData;
    }

    public Map<String, String> login(AuthRequest authRequest) {
        //In this we need to use try and catch
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(authentication.isAuthenticated()){
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            return jwtService.generateTokens(userPrincipal);
        }
        throw new RuntimeException("Authentication failed");
    }

}

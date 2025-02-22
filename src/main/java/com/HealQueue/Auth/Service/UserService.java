package com.HealQueue.Auth.Service;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.UserInfo;
import com.HealQueue.Auth.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final JWTService jwtService;

    @Autowired
    private final AuthenticationManager authManager;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepo repo;

    public UserService(AuthenticationManager authManager, JWTService jwtService, PasswordEncoder passwordEncoder, UserRepo repo) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    public UserInfo registerUser(UserInfo user) {
        if(repo.existsByUserName(user.getUserName())){
            throw new RuntimeException("User Already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String login(AuthRequest request) {
        UserInfo userInfo = repo.findByUserName(request.getUserName());
        if(userInfo == null){
            throw new RuntimeException("User Does Not Exist");
        }
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(request.getUserName());
        }
        return "Fail";
    }

    public List<UserInfo> getAllUser() {
        return repo.findAll();
    }

    public String getOnlyUserName(long id) {
        return repo.findUserNameById(id);
    }

    public UserInfo findUserData(long id) {
        return repo.findById(id).orElse(null);
    }
}

package com.HealQueue.Auth.Service;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.UserInfo;
import com.HealQueue.Auth.Entity.UserPrincipal;
import com.HealQueue.Auth.Repository.UserRepo;
import com.google.maps.errors.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private final JWTService jwtService;

    @Autowired
    private final AuthenticationManager authManager;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepo repo;
//
//    @Autowired
//    private GoogleMapService googleMapService;

    public UserService(AuthenticationManager authManager, JWTService jwtService, PasswordEncoder passwordEncoder, UserRepo repo) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    public UserInfo registerUser(UserInfo user) throws IOException, InterruptedException, ApiException {
        logger.info("");
        if(repo.existsByUserName(user.getUserName())){
            throw new RuntimeException("User Already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public Map<String, String> login(AuthRequest request) {
        Optional<UserInfo> optionalUser = repo.findByUserName(request.getUserName());
//        UserInfo userInfo = repo.findByUserName(request.getUserName());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User Does Not Exist");
        }

        UserInfo userInfo = optionalUser.get();
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated()) {
            UserPrincipal userPrincipal = new UserPrincipal(userInfo);
            return jwtService.generateTokens(userPrincipal);
        }
        throw new RuntimeException("Authentication failed");
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

    public Optional<UserInfo> findByUserName(String username) {
        return repo.findByUserName(username);
    }
}

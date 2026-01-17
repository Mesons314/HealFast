package com.HealQueue.Auth.Controller;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.DTO.RefreshRequest;
import com.HealQueue.Auth.Entity.ClinicInfo;
import com.HealQueue.Auth.Entity.UserInfo;
import com.HealQueue.Auth.Repository.UserRepo;
import com.HealQueue.Auth.Service.ClinicService;
import com.HealQueue.Auth.Service.JWTService;
import com.HealQueue.Auth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody UserInfo user) {
        try {
            user = userService.registerUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/register/clinic")
    public ResponseEntity<?> registerClinic(@RequestBody ClinicInfo clinicInfo) {
        try {
            clinicInfo = clinicService.register(clinicInfo);
            return new ResponseEntity<>(clinicInfo, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //Used for user login
    @PostMapping("/login/user")
    public Map<String, String> loginUser(@RequestBody AuthRequest request) {
        try {
            return userService.login(request);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    //Used for logging in clinic
    @PostMapping("/login/clinic")
    public Map<String, String> loginClinic(@RequestBody AuthRequest request) {
        try {
            return clinicService.login(request);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    //In this I am passing both types but, I need to pass only refresh so
    //after completing this make it correct
    //Pass only refresh token from flutter or make some logical changes here
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody RefreshRequest request) {
        try {
            String username = jwtService.extractUserName(request.getRefreshToken());
            var userDetails = userDetailsService.loadUserByUsername(username);
            String newAccessToken = jwtService.refreshAccessToken(request.getRefreshToken(), userDetails);

            return ResponseEntity.ok(Map.of(
                    "accessToken", newAccessToken,
                    "refreshToken", request.getRefreshToken()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid refresh token"));
        }
    }
}

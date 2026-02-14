package com.HealQueue.Auth.Service;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.ClinicInfo;
import com.HealQueue.Auth.Entity.UserPrincipal;
import com.HealQueue.Auth.Repository.ClinicRepo;
import com.HealQueue.googleMap.Service.GoogleMapService;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
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
public class ClinicService {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JWTService jwtService;

    @Autowired
    private final AuthenticationManager authManager;
    @Autowired
    private final ClinicRepo repo;
//
//    @Autowired
//    private GoogleMapService googleMapService;

    public ClinicService(AuthenticationManager authManager, JWTService jwtService, PasswordEncoder passwordEncoder, ClinicRepo repo) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    public ClinicInfo register(ClinicInfo clinicInfo) throws IOException, InterruptedException, ApiException {
        if(repo.existsByUserName(clinicInfo.getFirstName())){
            throw new RuntimeException("User Already Exist");
        }
        clinicInfo.setPassword(passwordEncoder.encode(clinicInfo.getPassword()));
        return repo.save(clinicInfo);
    }

    public Map<String, String> login(AuthRequest request) {
        // Get Optional from repository
        Optional<ClinicInfo> optionalClinic = repo.findByUserName(request.getUserName());
        System.out.println("Attempting login for user: " + request.getUserName());

        // Check if user exists
        if(optionalClinic.isEmpty()){
            throw new RuntimeException("User Does Not Exist");
        }

        // Unwrap the ClinicInfo object
        ClinicInfo clinicInfo = optionalClinic.get();

        // Authenticate using Spring Security
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // If authentication is successful, generate JWT tokens
        if(authentication.isAuthenticated()){
            UserPrincipal userPrincipal = new UserPrincipal(clinicInfo); // pass actual ClinicInfo
            return jwtService.generateTokens(userPrincipal);
        }
        throw new RuntimeException("Authentication failed");
    }

    public List<ClinicInfo> getClinic() {
        return repo.findAll();
    }

    public ClinicInfo findById(long id) {
        return repo.findById(id).orElse(null);
    }

    public ClinicInfo findByData(long id) {
        return repo.findById(id).orElse(null);
    }
    public Optional<ClinicInfo> findByUserName(String userName) {
        return repo.findByUserName(userName);
    }
}

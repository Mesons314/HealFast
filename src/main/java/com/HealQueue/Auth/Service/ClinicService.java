package com.HealQueue.Auth.Service;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.ClinicInfo;
import com.HealQueue.Auth.Entity.UserPrincipal;
import com.HealQueue.Auth.Repository.ClinicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ClinicService(AuthenticationManager authManager, JWTService jwtService, PasswordEncoder passwordEncoder, ClinicRepo repo) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.repo = repo;
    }

    public ClinicInfo register(ClinicInfo clinicInfo) {
        if(repo.existsByUserName(clinicInfo.getFirstName())){
            throw new RuntimeException("User Already Exist");
        }
        clinicInfo.setPassword(passwordEncoder.encode(clinicInfo.getPassword()));
        return repo.save(clinicInfo);
    }

    public String login(AuthRequest request) {
        ClinicInfo clinicInfo = repo.findByUserName(request.getUserName());
        if(clinicInfo == null){
            throw new RuntimeException("User Does Not Exist");
        }
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(authentication.isAuthenticated()){
//            return jwtService.generateToken(request.getUserName());
            UserPrincipal userPrincipal = new UserPrincipal(clinicInfo);
            return jwtService.generateToken(userPrincipal);
        }
        return "Fail";
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
}

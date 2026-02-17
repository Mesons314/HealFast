package com.HealQueue.CLINIC.Service;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.UserAccountData;
import com.HealQueue.Auth.Repository.AuthRepo;
import com.HealQueue.Auth.Service.JWTService;
import com.HealQueue.CLINIC.DTO.ClinicRequestDTO;
import com.HealQueue.CLINIC.DTO.ClinicResponseDTO;
import com.HealQueue.CLINIC.Entity.ClinicInfo;
import com.HealQueue.Auth.Entity.UserPrincipal;
import com.HealQueue.CLINIC.Repository.ClinicRepo;
import com.google.maps.errors.ApiException;
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

//    @Autowired
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private final JWTService jwtService;
//
//    @Autowired
//    private final AuthenticationManager authManager;
    @Autowired
    private final ClinicRepo repo;

    @Autowired
    private final AuthRepo authRepo;

    public ClinicService(ClinicRepo repo, AuthRepo authRepo) {
        this.repo = repo;
        this.authRepo = authRepo;
    }
//    @Autowired
//    private GoogleMapService googleMapService;

    public List<ClinicInfo> getClinic() {
        return repo.findAll();
    }

    public ClinicResponseDTO findById(long id) {
        ClinicInfo clinicInfo = repo.findById(id).orElseThrow(()->new RuntimeException("No clinic exists"));
        return mapToDTO(clinicInfo);
    }

    public ClinicInfo findByData(long id) {
        return repo.findById(id).orElse(null);
    }
    public ClinicResponseDTO findByUserName(String userName) {
        ClinicInfo clinicInfo = repo.findByUserAccountData_UserName(userName).orElseThrow(()->new RuntimeException("User Does not exists"));
        return mapToDTO(clinicInfo);
    }


    public ClinicInfo addClinicData(ClinicRequestDTO dto, String userName) {
        UserAccountData accountData = authRepo.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (accountData.getClinicInfo() != null) {
            throw new RuntimeException("Clinic profile already exists");
        }
        ClinicInfo clinic = new ClinicInfo();
        clinic.setAddress(dto.getAddress());
        clinic.setGraduationDegree(dto.getGraduationDegree());
        clinic.setFirstName(dto.getFirstName());
        clinic.setLastName(dto.getLastName());
        clinic.setSpeciality(dto.getSpeciality());
        clinic.setGender(dto.getGender());
        clinic.setDob(dto.getDob());
        clinic.setClinicName(dto.getClinicName());
        clinic.setClinicPhoneNo(dto.getClinicPhoneNo());
        clinic.setUserAccountData(accountData);
        accountData.setClinicInfo(clinic);
        return repo.save(clinic);
    }

    private ClinicResponseDTO mapToDTO(ClinicInfo clinicInfo){
        return new ClinicResponseDTO(clinicInfo);
    }
}

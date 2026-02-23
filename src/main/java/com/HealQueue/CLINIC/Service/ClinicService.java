package com.HealQueue.CLINIC.Service;

import com.HealQueue.Auth.Entity.UserAccountData;
import com.HealQueue.Auth.Repository.AuthRepo;
import com.HealQueue.CLINIC.DTO.ClinicRequestDTO;
import com.HealQueue.CLINIC.DTO.ClinicResponseDTO;
import com.HealQueue.CLINIC.Entity.ClinicInfo;
import com.HealQueue.CLINIC.Repository.ClinicRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.*;

@Service
public class ClinicService {

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

    //need to correct this
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
        //need to do the same as updateClinic
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

    public ClinicResponseDTO updateClinic(ClinicRequestDTO dto, String username) {
        UserAccountData userAccountData = authRepo.findByUserName(username)
                .orElseThrow(()->new RuntimeException("No user found"));
        ClinicInfo clinic = userAccountData.getClinicInfo();
        /**
        instead of doing this for every method we will use
        beansUtil which is a wrapper class which internally do the same thing
        of mapping like this
        if(dto.getClinicName() != null){
         clinic.setClinicName(dto.getClinicName());
        }
        if(dto.getClinicPhoneNo() != null){
         clinic.setClinicPhoneNo(dto.getClinicPhoneNo());
        }
        **/
         BeanUtils.copyProperties(dto,clinic,getNullProperties(dto));
        ClinicInfo updatedClinic = repo.save(clinic);
        return new ClinicResponseDTO(updatedClinic);
    }

    private String[] getNullProperties(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd: pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[0]);
    }
}

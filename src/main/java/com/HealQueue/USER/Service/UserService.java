package com.HealQueue.USER.Service;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.UserAccountData;
import com.HealQueue.Auth.Repository.AuthRepo;
import com.HealQueue.Exceptions.UserNameAlreadyExistsException;
import com.HealQueue.USER.DTO.UserRequestDTO;
import com.HealQueue.USER.DTO.UserResponseDTO;
import com.HealQueue.USER.Entity.UserInfo;
import com.HealQueue.Auth.Entity.UserPrincipal;
import com.HealQueue.USER.Repository.UserRepo;
import com.HealQueue.Auth.Service.JWTService;
import com.google.maps.errors.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.*;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

//
//    @Autowired
//    private final JWTService jwtService;
//
//    @Autowired
//    private final AuthenticationManager authManager;
//
//    @Autowired
//    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepo repo;

    @Autowired
    private final AuthRepo authRepo;

    public UserService(UserRepo repo, AuthRepo authRepo) {
        this.repo = repo;
        this.authRepo = authRepo;
    }

    public List<UserInfo> getAllUser() {
        return repo.findAll();
    }

    public String getOnlyUserName(long id) {
        return repo.findUserAccountData_UserNameById(id);
    }

    public UserInfo findUserData(long id) {
        return repo.findById(id).orElse(null);
    }

    public UserResponseDTO findByUserName(String username) {
        UserInfo userInfo = repo.findByUserAccountData_UserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return mapToDTO(userInfo);
    }

    private UserResponseDTO mapToDTO(UserInfo user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setProfileId(user.getId());
        dto.setUserName(user.getUserAccountData().getUserName());
        dto.setEmail(user.getUserAccountData().getEmail());
        dto.setPhoneNo(user.getUserAccountData().getPhoneNo());
        dto.setAddress(user.getAddress());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setGender(user.getGender());
        dto.setDob(user.getDob());
        return dto;
    }

    @Transactional
    public UserInfo addUserData(UserRequestDTO dto, String username) {
        UserAccountData accountData = authRepo.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("No username exists by this name"));
        if (accountData.getUserInfo() != null) {
            throw new UserNameAlreadyExistsException("User profile already exists");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setAddress(dto.getAddress());
        userInfo.setFirstName(dto.getFirstName());
        userInfo.setLastName(dto.getLastName());
        userInfo.setGender(dto.getGender());
        userInfo.setDob(dto.getDob());
        userInfo.setUserAccountData(accountData);
        accountData.setUserInfo(userInfo);
        return repo.save(userInfo);
    }

    public UserResponseDTO updateUser(UserRequestDTO dto, String username){
        UserAccountData userAccountData = authRepo.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        UserInfo info = userAccountData.getUserInfo();

        BeanUtils.copyProperties(dto,info,getNullProperties(dto));
        UserInfo updateUser = repo.save(info);
        return new UserResponseDTO(updateUser);
    }

    private String[] getNullProperties(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> empty = new HashSet<>();
        for(PropertyDescriptor p: pds){
            Object srcValue = src.getPropertyValue(p.getName());
            if(srcValue == null){
                empty.add(p.getName());
            }
        }
        return empty.toArray(new String[0]);
    }

    public boolean userExists(String username) {
        return authRepo.existsByUserName(username);
    }
}

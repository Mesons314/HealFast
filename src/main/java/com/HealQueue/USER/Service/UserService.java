package com.HealQueue.USER.Service;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.UserAccountData;
import com.HealQueue.Auth.Repository.AuthRepo;
import com.HealQueue.USER.DTO.UserRequestDTO;
import com.HealQueue.USER.DTO.UserResponseDTO;
import com.HealQueue.USER.Entity.UserInfo;
import com.HealQueue.Auth.Entity.UserPrincipal;
import com.HealQueue.USER.Repository.UserRepo;
import com.HealQueue.Auth.Service.JWTService;
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
//
//    @Autowired
//    private GoogleMapService googleMapService;

//    public UserService(AuthenticationManager authManager, JWTService jwtService, PasswordEncoder passwordEncoder, UserRepo repo) {
//        this.authManager = authManager;
//        this.jwtService = jwtService;
//        this.passwordEncoder = passwordEncoder;
//        this.repo = repo;
//    }

//    public UserInfo registerUser(UserInfo user) throws IOException, InterruptedException, ApiException {
//        logger.info("");
//        if(repo.existsByUserName(user.getUserName())){
//            throw new RuntimeException("User Already exist");
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return repo.save(user);
//    }
//
//    public Map<String, String> login(AuthRequest request) {
//        Optional<UserInfo> optionalUser = repo.findByUserName(request.getUserName());
////        UserInfo userInfo = repo.findByUserName(request.getUserName());
//        if (optionalUser.isEmpty()) {
//            throw new RuntimeException("User Does Not Exist");
//        }
//
//        UserInfo userInfo = optionalUser.get();
//        Authentication authentication = authManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        if (authentication.isAuthenticated()) {
//            UserPrincipal userPrincipal = new UserPrincipal(userInfo);
//            return jwtService.generateTokens(userPrincipal);
//        }
//        throw new RuntimeException("Authentication failed");
//    }


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
                .orElseThrow(()-> new RuntimeException("User not found"));
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

    public UserInfo addUserData(UserRequestDTO dto, String username) {
        UserAccountData accountData = authRepo.findByUserName(username)
                .orElseThrow(()->new RuntimeException("No username exists by this name"));
        if (accountData.getUserInfo() != null) {
            throw new RuntimeException("User profile already exists");
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

    public boolean userExists(String username) {
        return authRepo.existsByUserName(username);
    }
}

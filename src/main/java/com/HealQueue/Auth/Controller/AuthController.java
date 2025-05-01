package com.HealQueue.Auth.Controller;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.ClinicInfo;
import com.HealQueue.Auth.Entity.UserInfo;
import com.HealQueue.Auth.Service.ClinicService;
import com.HealQueue.Auth.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private ClinicService clinicService;

    @PostMapping("/register/user")
    private ResponseEntity<?> registerUser(@RequestBody UserInfo user){
        try {
            user = userService.registerUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/register/clinic")
    private ResponseEntity<?> registerClinic(@RequestBody ClinicInfo clinicInfo){
        try {
            clinicInfo = clinicService.register(clinicInfo);
            return new ResponseEntity<>(clinicInfo,HttpStatus.OK);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/login/user")
    private String loginUser(@RequestBody AuthRequest request){
        try {
            return userService.login(request);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/login/clinic")
    private String login(@RequestBody AuthRequest request){
        try {
            return clinicService.login(request);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/user/get")
    public ResponseEntity<List<UserInfo>> getAllData(){
        List<UserInfo> user =  userService.getAllUser();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/clinic/get")
    public ResponseEntity<List<ClinicInfo>> getAllClinic(){
        List<ClinicInfo> clinicInfos = clinicService.getClinic();
        return new ResponseEntity<>(clinicInfos,HttpStatus.OK);
    }
}

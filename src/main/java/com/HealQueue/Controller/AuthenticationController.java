package com.HealQueue.Controller;

import com.HealQueue.DTO.AuthRequest;
import com.HealQueue.DTO.AuthResponse;
import com.HealQueue.Entity.UserInfo;
import com.HealQueue.Repository.UserRepo;
import com.HealQueue.Service.MyUserDetailService;
import com.HealQueue.Service.UserServicing;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private UserServicing service;

    @Autowired
    private UserRepo repo;



    @PostMapping("/register")
    public UserInfo registerUser(@RequestBody UserInfo user){
        return service.register(user);
    }

    @PostMapping("/login")
    public String Login(@RequestBody AuthRequest request){
        return service.verify(request);
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserInfo>> info(){
        List<UserInfo> users = service.getUser();
        return ResponseEntity.ok(users);
    }
}

package com.HealQueue.Auth.Controller;

import com.HealQueue.Auth.DTO.AuthRequest;
import com.HealQueue.Auth.Entity.Role;
import com.HealQueue.Auth.Entity.UserInfo;
import com.HealQueue.Auth.Service.UserServicing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServicing service;

    @PostMapping("/user/register")
    public UserInfo registerUser(@RequestBody UserInfo user){
        return service.register(user, Role.USER);
    }
    @PostMapping("/clinic/register")
    public UserInfo registerClinic(@RequestBody UserInfo user){
        return service.register(user, Role.DOCTOR);
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

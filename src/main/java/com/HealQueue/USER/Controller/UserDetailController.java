package com.HealQueue.USER.Controller;

import com.HealQueue.Auth.Entity.UserInfo;
import com.HealQueue.Auth.Service.UserService;
import com.HealQueue.USER.DTO.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserDetailController {
    @Autowired
    private UserService userService;

    @GetMapping("/userName/{id}")
    public String getName(@PathVariable long id){
        try {
            return userService.getOnlyUserName(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/userName/data/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long id){
        UserInfo userInfo = userService.findUserData(id);
        try {
            if(userInfo!=null){
                return ResponseEntity.ok(new UserResponse(userInfo));
            }
            throw new RuntimeException("User not found");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

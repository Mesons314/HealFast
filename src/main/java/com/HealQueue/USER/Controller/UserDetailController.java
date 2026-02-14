package com.HealQueue.USER.Controller;

import com.HealQueue.Auth.Entity.UserInfo;
import com.HealQueue.Auth.Service.UserService;
import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Service.QueueService;
import com.HealQueue.USER.DTO.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserDetailController {
    @Autowired
    private UserService userService;

    @Autowired
    private QueueService queueService;

    private UserInfo userInfo;

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
    //This url should be automatically directed by the frontend once the user logged in
    //In this i havent removed password from the dto
    @GetMapping("/user/me")
    public ResponseEntity<UserInfo> getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //here i have used optional instead of userinfo so the changes are done in
        //user repo as well as user service may be some error will occur in future
        Optional<UserInfo> optionalUser = userService.findByUserName(username);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        UserInfo userInfo = optionalUser.get();
        return ResponseEntity.ok(userInfo);
    }

    //In this i need to set the clinic id from the backend only it should not come from the frontend
    @PostMapping("/queue/add/{clinicId}")
    public ResponseEntity<?> addQueue(@RequestBody AppointmentBooking appointmentBooking, @PathVariable long clinicId){
        try {
            appointmentBooking = queueService.add(appointmentBooking,clinicId);
            return new ResponseEntity<>(appointmentBooking, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable long id){
        //In this i want to differentiate between the clinics so that user should add
        //or delete the queue only from the clinic from where he entered the data
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = userService.findByUserName(username)
                .orElseThrow(
                        ()-> new RuntimeException("User Not Found")
                );
        Long userId = userInfo.getId();
        AppointmentBooking appointmentBooking = queueService.getQueueById(id,userId)
                .orElseThrow(
                        ()->new RuntimeException("User Not Found"));
        queueService.deleteQueueByUser(id, userId);
        return ResponseEntity.ok("Successfully Deleted");
    }
}



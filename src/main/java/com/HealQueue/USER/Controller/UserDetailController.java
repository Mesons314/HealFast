package com.HealQueue.USER.Controller;

import com.HealQueue.Queue.DTO.QueueResponseDTO;
import com.HealQueue.USER.DTO.UserRequestDTO;
import com.HealQueue.USER.DTO.UserResponseDTO;
import com.HealQueue.USER.Entity.UserInfo;
import com.HealQueue.USER.Service.UserService;
import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserDetailController {
    @Autowired
    private UserService userService;

    @Autowired
    private QueueService queueService;

    //This url should be automatically directed by the frontend once the user logged in
    //In this i havent removed password from the dto
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //here i have used optional instead of userinfo so the changes are done in
        //user repo as well as user service may be some error will occur in future
        UserResponseDTO userResponse = userService.findByUserName(username);
        if(userResponse == null){
            throw new RuntimeException("No user found");
        }
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }

    //In this i need to set the clinic id from the backend only it should not come from the frontend
    @PostMapping("/queue/add/{clinicId}")
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentBooking appointmentBooking, @PathVariable long clinicId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isExists = userService.userExists(username);
        Long userId = userService.findByUserName(username).getProfileId();
        if(!isExists){
            throw new RuntimeException("User Does Not exists");
        }
        appointmentBooking = queueService.addAppointment(appointmentBooking,clinicId,userId);
        return new ResponseEntity<>(appointmentBooking, HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfo userInfo = userService.addUserData(userRequestDTO,username);
        System.out.println(userInfo.getUserAccountData());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new UserResponseDTO(userInfo));
    }

    @GetMapping("/appointment/{id}")
    public ResponseEntity<QueueResponseDTO> getAppointment(@PathVariable long id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean isExists = userService.userExists(username);
        if(!isExists){
            throw new RuntimeException("User Does Not exists");
        }
        Long userId = userService.findByUserName(username).getProfileId();
        AppointmentBooking ap = queueService.getQueueByUserId(id,userId);
        QueueResponseDTO dto = queueService.getTimeAndStatus(ap);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //In this when I am deleting appointment of different
    //user it showing successfully deleted instead of showing error
    //but the appointment is never deleted
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteAppointment(@PathVariable long id){
//        //In this i want to differentiate between the clinics so that user should add
//        //or delete the queue only from the clinic from where he entered the data
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        UserResponseDTO userResponse = userService.findByUserName(username);
//        Long userId = userResponse.getProfileId();
//        queueService.deleteQueueByUser(id, userId);
//        return ResponseEntity.ok("Successfully Deleted");
//    }
}



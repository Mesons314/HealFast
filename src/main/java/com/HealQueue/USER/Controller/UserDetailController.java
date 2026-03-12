package com.HealQueue.USER.Controller;

import com.HealQueue.CLINIC.DTO.ClinicResponseDTO;
import com.HealQueue.CLINIC.Service.ClinicService;
import com.HealQueue.Queue.DTO.QueueResponseDTO;
import com.HealQueue.USER.DTO.UserRequestDTO;
import com.HealQueue.USER.DTO.UserResponseDTO;
import com.HealQueue.USER.Entity.UserInfo;
import com.HealQueue.USER.Service.UserService;
import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Service.QueueService;
import com.HealQueue.googleMap.Service.GoogleMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserDetailController {
    @Autowired
    private UserService userService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private QueueService queueService;

    @Autowired
    private GoogleMapService googleMapService;

    @GetMapping("/getClinic")
    public ResponseEntity<List<ClinicResponseDTO>> getClinicList(){
        return new ResponseEntity<>(clinicService.getClinic(), HttpStatus.OK);
    }

    @GetMapping("/getClinic/{id}")
    public ResponseEntity<ClinicResponseDTO> clinicInfo(@PathVariable Long id){
        ClinicResponseDTO clinicResponseDTO = clinicService.findById(id);
//        try{
//            GeocodingResult[] geocodingResults = googleMapService.geocodeAddress(clinic.getAddress());
//            double lat = 0;
//            double lon = 0;
//
//            if(geocodingResults.length>0){
//                lat = geocodingResults[0].geometry.location.lat;
//                lon = geocodingResults[0].geometry.location.lng;
//            }
//            return ResponseEntity.ok(new ClinicResponse(clinic, lat, lon));
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
        try {
            return new ResponseEntity<>(clinicResponseDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

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

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentBooking>> getMyAppointments(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserResponseDTO dto = userService.findByUserName(username);
        Long userId = dto.getProfileId();
        List<AppointmentBooking> myAppointment = queueService.getMyAppointments(userId);
        return new ResponseEntity<>(myAppointment,HttpStatus.OK);
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

    @GetMapping("/search/clinic")
    public ResponseEntity<List<ClinicResponseDTO>> clinicSearch(@RequestParam("query") String query){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserResponseDTO dto = userService.findByUserName(username);
        if(dto == null){
            throw new RuntimeException("No user found");
        }
        List<ClinicResponseDTO> clinicDtoList = clinicService.searchClinic(query.trim());
        return new ResponseEntity<>(clinicDtoList,HttpStatus.OK);
    }

    @PatchMapping("/appointments/cancel/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable Long id){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UserResponseDTO dto = userService.findByUserName(userName);
        if(dto == null){
            throw new RuntimeException("No user found");
        }
        AppointmentBooking ap = queueService.getMyAppointment(id,dto.getProfileId());
        queueService.cancelAppointment(ap);
        return ResponseEntity.ok("Appointment canceled");
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



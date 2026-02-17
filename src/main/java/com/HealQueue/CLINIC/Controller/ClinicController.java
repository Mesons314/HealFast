package com.HealQueue.CLINIC.Controller;

import com.HealQueue.CLINIC.DTO.ClinicRequestDTO;
import com.HealQueue.CLINIC.DTO.ClinicResponseDTO;
import com.HealQueue.CLINIC.Entity.ClinicInfo;
import com.HealQueue.CLINIC.Service.ClinicService;
import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private QueueService queueService;

//    @Autowired
//    private GoogleMapService googleMapService;

//    @GetMapping("/clinicData/{id}")
//    public ResponseEntity<ClinicResponse> getDataById(@PathVariable long id){
//        ClinicInfo clinicInfo = clinicService.findByData(id);
//        if(clinicInfo == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        try{
//            GeocodingResult[] geocodingResults = googleMapService.geocodeAddress(clinicInfo.getAddress());
//            double lat = 0;
//            double lon = 0;
//
//            if(geocodingResults.length>0){
//                lat = geocodingResults[0].geometry.location.lat;
//                lon = geocodingResults[0].geometry.location.lng;
//            }
//            return ResponseEntity.ok(new ClinicResponse(clinicInfo, lat, lon));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


//    @GetMapping("/clinicData/{id}")
//    public ResponseEntity<ClinicResponse> getDataById(@PathVariable long id){
//        clinicInfo = clinicService.findById(id);
//        if(clinicInfo == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.ok(new ClinicResponse(clinicInfo));
//    }

    //Add the data to show logged in clinic because the above method will show by id
    //which is not safe

    @GetMapping("/me")
    public ResponseEntity<ClinicResponseDTO> loggedInClinic(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("User Name is "+userName);
        ClinicResponseDTO clinicResponse = clinicService.findByUserName(userName);

        return new ResponseEntity<>(clinicResponse, HttpStatus.OK);
    }

    @PostMapping("/postData")
    public ResponseEntity<ClinicResponseDTO> addData(@RequestBody ClinicRequestDTO clinicRequestDTO){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ClinicInfo clinicInfo = clinicService.addClinicData(clinicRequestDTO,userName);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ClinicResponseDTO(clinicInfo));
    }

    @GetMapping("/queue/get")
    public ResponseEntity<List<AppointmentBooking>> getQueue(){
        //Instead of using this optional we should use the security context to get the
        //current logged in clinic so that only that clinic will get their queue
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        ClinicResponseDTO clinicResponseDTO = clinicService.findByUserName(userName);
        Long clinicId = clinicResponseDTO.getProfileId();
        List<AppointmentBooking> appointmentBookings = queueService.getAllQueue(clinicId);
        return new ResponseEntity<>(appointmentBookings,HttpStatus.OK);
    }

    @GetMapping("/queue/get/{appointmentId}")
    public ResponseEntity<AppointmentBooking> getQueueBYId(@PathVariable int appointmentId){
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        ClinicResponseDTO clinicResponseDTO = clinicService
                .findByUserName(username);
        Long clinicId = clinicResponseDTO.getProfileId();
        AppointmentBooking appointmentBooking = queueService.getQueueById(appointmentId, clinicId)
                .orElseThrow(()->new RuntimeException("No appointment exists"));
        return new ResponseEntity<>(appointmentBooking, HttpStatus.OK);
        
    }

    //Need to add the clinic id also as it cannot identify which clinic queue is this
    //it will show all the clinics queue
    @DeleteMapping("/delete/queue/{id}")
    public ResponseEntity<?> deleteQueue(@PathVariable long id) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        ClinicResponseDTO clinicResponseDTO = clinicService
                .findByUserName(username);
        Long clinicId = clinicResponseDTO.getProfileId();

        boolean deleted = queueService.deleteQueueByClinic(id, clinicId);

        if (!deleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Queue not found for your clinic");
        }

        return ResponseEntity.ok("Deleted");
    }

}

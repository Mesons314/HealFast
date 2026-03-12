package com.HealQueue.CLINIC.Controller;
import com.HealQueue.CLINIC.DTO.ClinicRequestDTO;
import com.HealQueue.CLINIC.DTO.ClinicResponseDTO;
import com.HealQueue.CLINIC.Entity.ClinicInfo;
import com.HealQueue.CLINIC.Service.ClinicService;
import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Model.StatusEnum;
import com.HealQueue.Queue.Service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clinic/queue")
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

    @PatchMapping("/updateClinic")
    public ResponseEntity<ClinicResponseDTO> updateClinic(@RequestBody ClinicRequestDTO dto){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ClinicResponseDTO clinicResponse = clinicService.updateClinic(dto,username);
        //need to added error handling
        return new ResponseEntity<>(clinicResponse,HttpStatus.OK);
    }

    //Add the data to show logged in clinic because the above method will show by id
    //which is not safe
    @GetMapping("/me")
    public ResponseEntity<ClinicResponseDTO> loggedInClinic(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
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

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentBooking>> getBookedStatus(@RequestParam(required = false)StatusEnum status){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ClinicResponseDTO clinicResponseDTO = clinicService
                .findByUserName(username);
        Long clinicId = clinicResponseDTO.getProfileId();
        List<AppointmentBooking> bookedAppointment = queueService.getBookedAppointment(status, clinicId);
        return new ResponseEntity<>(bookedAppointment, HttpStatus.OK);
    }

    @GetMapping("/appointments/today")
    public ResponseEntity<List<AppointmentBooking>> getTodayAppointment(){
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        ClinicResponseDTO clinicResponseDTO = clinicService
                .findByUserName(username);
        Long clinicId = clinicResponseDTO.getProfileId();
        List<AppointmentBooking> ap = queueService.getTodaysAppointment(clinicId);
        return new ResponseEntity<>(ap,HttpStatus.OK);
    }

    //First the appointment is called and then these two are inside that so that we get
    //the appointments and this will be divided in 2 parts in ui booked and completed
    @GetMapping("/appointments/{appointmentId}")
    public ResponseEntity<AppointmentBooking> getQueueBYId(@PathVariable Long appointmentId){
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        ClinicResponseDTO clinicResponseDTO = clinicService
                .findByUserName(username);
        Long clinicId = clinicResponseDTO.getProfileId();
        AppointmentBooking appointmentBooking = queueService.getQueueByClinicId(appointmentId, clinicId);
        return new ResponseEntity<>(appointmentBooking, HttpStatus.OK);
    }

    @PatchMapping("/appointments/no_show/{id}")
    public ResponseEntity<AppointmentBooking> updateAppointmentToNoShow(@PathVariable Long id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ClinicResponseDTO clinicResponseDTO = clinicService
                .findByUserName(username);
        Long clinicId = clinicResponseDTO.getProfileId();
        AppointmentBooking updated = queueService.noShowUpdate(id,clinicId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping("/appointments/complete/{id}")
    public ResponseEntity<AppointmentBooking> updateAppointmentToComplete(@PathVariable Long id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ClinicResponseDTO clinicResponseDTO = clinicService
                .findByUserName(username);
        Long clinicId = clinicResponseDTO.getProfileId();
        AppointmentBooking updated = queueService.appointmentCompleted(id,clinicId);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

}

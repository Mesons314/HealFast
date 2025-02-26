package com.HealQueue.CLINIC.Controller;

import com.HealQueue.Auth.Entity.ClinicInfo;
import com.HealQueue.Auth.Service.ClinicService;
import com.HealQueue.CLINIC.DTO.ClinicResponse;
import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private QueueService queueService;

    @GetMapping("/clinicData/{id}")
    public ResponseEntity<ClinicResponse> getDataById(@PathVariable long id){
        ClinicInfo clinicInfo = clinicService.findByData(id);
        try {
            if(clinicInfo != null){
                return ResponseEntity.ok(new ClinicResponse(clinicInfo));
            }
            throw new RuntimeException("Not Found");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/queue/get")
    public ResponseEntity<List<AppointmentBooking>> getQueue(){
        List<AppointmentBooking> apbook = queueService.getAllQueue();
        return new ResponseEntity<>(apbook,HttpStatus.OK);
    }

}

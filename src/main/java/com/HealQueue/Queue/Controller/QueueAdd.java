package com.HealQueue.Queue.Controller;

import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/queue")
public class QueueAdd {

    @Autowired
    private QueueService queueService;

    @PostMapping("/add")
    public ResponseEntity<?> addQueue(@RequestBody AppointmentBooking appointmentBooking){
        try {
            appointmentBooking = queueService.add(appointmentBooking);
            return new ResponseEntity<>(appointmentBooking, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

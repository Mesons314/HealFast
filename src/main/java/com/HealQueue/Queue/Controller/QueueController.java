package com.HealQueue.Queue.Controller;

import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queue")
public class QueueController {

    @Autowired
    private QueueService queueService;

    @GetMapping("/get")
    public ResponseEntity<List<AppointmentBooking>> getQueue(){
        List<AppointmentBooking> apbook = queueService.getAllQueue();
        return new ResponseEntity<>(apbook,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQueue(@RequestBody AppointmentBooking appointmentBooking){
        try {
            appointmentBooking = queueService.add(appointmentBooking);
            return new ResponseEntity<>(appointmentBooking, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQueue(@PathVariable long id){
        AppointmentBooking appointmentBooking = queueService.getQueueById(id);
        if(appointmentBooking!=null){
            queueService.deleteProduct((int) id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Queue Does Not Exist",HttpStatus.NOT_FOUND);
    }
}

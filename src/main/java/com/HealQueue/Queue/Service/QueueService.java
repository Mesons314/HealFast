package com.HealQueue.Queue.Service;

import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Repsitory.QueueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QueueService {

    @Autowired
    private QueueRepo repo;
    public AppointmentBooking add(AppointmentBooking appointmentBooking) {
        int currentSize = (int) repo.count();
        appointmentBooking.setId(currentSize+1);
        appointmentBooking.setRegisteredAt(LocalDateTime.now());
        return repo.save(appointmentBooking);
    }

    public AppointmentBooking getQueueById(long id) {
        return repo.findById((int) id).orElse(null);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }
}

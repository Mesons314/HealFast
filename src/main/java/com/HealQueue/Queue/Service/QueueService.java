package com.HealQueue.Queue.Service;

import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Repsitory.QueueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QueueService {

    @Autowired
    private QueueRepo repo;
    public AppointmentBooking add(AppointmentBooking appointmentBooking) {
        int currentSize = (int) repo.count();
        appointmentBooking.setPatientNo(currentSize+1);
        appointmentBooking.setRegisteredAt(LocalDateTime.now());
        return repo.save(appointmentBooking);
    }

    public AppointmentBooking getQueueById(long id) {
        return repo.findById((int) id).orElse(null);
    }

    @Transactional
    public void deleteProduct(int id) {
        repo.deleteById(id);
        repo.updateNo();
    }

    public List<AppointmentBooking> getAllQueue() {
        return repo.findAll();
    }
}

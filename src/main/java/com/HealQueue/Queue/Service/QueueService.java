package com.HealQueue.Queue.Service;

import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Repsitory.QueueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QueueService {

    @Autowired
    private QueueRepo repo;
    @Autowired
    private TimeService timeService;

    //Add the enum for In queue, is completed
    public AppointmentBooking add(AppointmentBooking appointmentBooking, long clinicId) {
        int currentSize = repo.countByClinicId(clinicId);
        appointmentBooking.setPatientNo(currentSize+1);
        appointmentBooking.setDuration(timeService.getQueueTime((long) currentSize));
        appointmentBooking.setRegisteredAt(LocalDateTime.now());
        return repo.save(appointmentBooking);
    }

    public Optional<AppointmentBooking> getQueueById(long id, Long clinicId) {
        return repo.findByIdAndClinicId(id , clinicId);
    }

    @Transactional
    public boolean deleteQueueByClinic(long id, Long clinicId) {
        Optional<AppointmentBooking> deletedOptional =  repo.findByIdAndClinicId(id,clinicId);
        if(deletedOptional.isEmpty()){
            return false;
        }
        AppointmentBooking deleted = deletedOptional.get();
        int deletedNo = deleted.getPatientNo();
        //Here i need to return boolean so think about that later on
        repo.delete(deleted);
        repo.updateNo(deletedNo, clinicId);
        repo.timeRemained(deletedNo, clinicId);
        return true;
    }

    @Transactional
    public void deleteQueueByUser(long id, Long userId){
        Optional<AppointmentBooking> deletedOptional = repo.findByIdAndUserId(id,userId);
        if(deletedOptional.isEmpty()){
            return;
        }
        AppointmentBooking deleted = deletedOptional.get();
        int deletedNo = deleted.getPatientNo();
        repo.delete(deleted);
        repo.updateNo(deletedNo,userId);
        repo.timeRemained(deletedNo,userId);
    }

    //I think all the appointment table are together, but I want appointment table for each clinic,
    //so I need to change that currently I am selecting the queue from a single appointment table
    //so this table consists all the queue of every clinic, so it searches for those specific clinic ID then
    //it answers which take too much time, so I need to improve that
    public List<AppointmentBooking> getAllQueue(Long clinicId) {
        return repo.findAllByClinicId(clinicId);
    }

    public int getQueueSize(Long clinicId){
        return (int) repo.countByClinicId(clinicId);
    }
}

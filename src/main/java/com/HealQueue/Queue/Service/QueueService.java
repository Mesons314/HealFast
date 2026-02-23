package com.HealQueue.Queue.Service;

import com.HealQueue.CLINIC.Entity.ClinicInfo;
import com.HealQueue.CLINIC.Repository.ClinicRepo;
import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Repsitory.QueueRepo;
import com.HealQueue.USER.Entity.UserInfo;
import com.HealQueue.USER.Repository.UserRepo;
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
    private ClinicRepo clinicRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TimeService timeService;

    @Transactional
    public AppointmentBooking add(AppointmentBooking appointmentBooking, long clinicId, Long userId) {
        ClinicInfo clinic = clinicRepo.findByClinicIdForUpdate(clinicId)
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        UserInfo user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        appointmentBooking.setClinic(clinic);
        appointmentBooking.setUser(user);
        int currentSize = repo.countByClinic_Id(clinicId);
        int position = currentSize + 1;
        appointmentBooking.setPatientNo(position);
        appointmentBooking.setDuration(timeService.getQueueTime((long) position));
        appointmentBooking.setRegisteredAt(LocalDateTime.now());
        return repo.save(appointmentBooking);
    }

    public Optional<AppointmentBooking> getQueueById(long id, Long clinicId) {
        return repo.findByIdAndClinic_Id(id , clinicId);
    }

    @Transactional
    public boolean deleteQueueByClinic(long id, Long clinicId) {
        Optional<AppointmentBooking> deletedOptional =  repo.findByIdAndClinic_Id(id,clinicId);
        if(deletedOptional.isEmpty()){
            return false;
        }
        AppointmentBooking deleted = deletedOptional.get();
        int deletedNo = deleted.getPatientNo();
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
        Long clinicId = deleted.getClinic().getId();
        repo.delete(deleted);
        repo.updateNo(deletedNo,clinicId);
        repo.timeRemained(deletedNo,clinicId);
    }

    public List<AppointmentBooking> getAllQueue(Long clinicId) {
        return repo.findAllByClinicId(clinicId);
    }

}

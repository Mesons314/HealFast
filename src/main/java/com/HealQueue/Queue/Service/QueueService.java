package com.HealQueue.Queue.Service;

import com.HealQueue.CLINIC.Entity.ClinicInfo;
import com.HealQueue.CLINIC.Repository.ClinicRepo;
import com.HealQueue.Exceptions.AppointmentDoesNotExistsException;
import com.HealQueue.Exceptions.ClinicNotFoundException;
import com.HealQueue.Exceptions.UserNotFoundException;
import com.HealQueue.Queue.DTO.QueueResponseDTO;
import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Model.StatusEnum;
import com.HealQueue.Queue.Repsitory.QueueRepo;
import com.HealQueue.USER.Entity.UserInfo;
import com.HealQueue.USER.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class QueueService {

    @Autowired
    private QueueRepo repo;

    @Autowired
    private ClinicRepo clinicRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public AppointmentBooking addAppointment(AppointmentBooking appointmentBooking, long clinicId, Long userId) {
        ClinicInfo clinic = clinicRepo.findByClinicIdForUpdate(clinicId)
                .orElseThrow(() -> new ClinicNotFoundException("Clinic not found"));
        UserInfo user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        appointmentBooking.setClinic(clinic);
        appointmentBooking.setUser(user);
        appointmentBooking.setStatus(StatusEnum.BOOKED);
        appointmentBooking.setRegisteredAt(LocalDateTime.now());
        return repo.save(appointmentBooking);
    }

    public AppointmentBooking getQueueByClinicId(Long id, Long clinicId) {
        return repo.findByIdAndClinic_Id(id , clinicId).orElseThrow(
                ()-> new AppointmentDoesNotExistsException("No appointment found for specific clinic")
        );
    }
    public AppointmentBooking getQueueByUserId(Long id, Long userId) {
        return repo.findByIdAndUser_Id(id ,userId).orElseThrow(
                ()-> new AppointmentDoesNotExistsException("No appointment found for specific clinic")
        );
    }

    public List<AppointmentBooking> getAllQueue(Long clinicId) {
        return repo.findAllByClinicId(clinicId);
    }

    public AppointmentBooking appointmentCompleted(Long id, Long clinicId) {
        AppointmentBooking getAppointment = getQueueByClinicId(id,clinicId);
        if(getAppointment == null){
            throw new AppointmentDoesNotExistsException("No appointment found");
        }
        getAppointment.setStatus(StatusEnum.COMPLETED);
        repo.save(getAppointment);
        return getAppointment;
    }

    public QueueResponseDTO getTimeAndStatus(AppointmentBooking ap){
        LocalDateTime localDateTime = ap.getRegisteredAt();
        long id = ap.getId();
        Long clinicId = ap.getClinic().getId();
        int count = repo.countByClinicAndTime(clinicId,StatusEnum.BOOKED,localDateTime);
        int position = count + 1;
        int waitingTime = count*10;
        return new QueueResponseDTO(id,waitingTime,count,position,StatusEnum.BOOKED);
    }

    public AppointmentBooking noShowUpdate(Long id, Long clinicId) {
        AppointmentBooking getAppointment = getQueueByClinicId(id,clinicId);
        if(getAppointment == null){
            throw new AppointmentDoesNotExistsException("No appointment found");
        }
        getAppointment.setStatus(StatusEnum.NO_SHOW);
        repo.save(getAppointment);
        return getAppointment;
    }

    public List<AppointmentBooking> getBookedAppointment(StatusEnum status, Long clinicId) {
        return repo.findByClinic_IdAndStatus(clinicId,status);
    }

    public List<AppointmentBooking> getTodaysAppointment(Long clinicId) {
        LocalDate today = LocalDate.now();
        LocalDateTime startDay = today.atStartOfDay();
        LocalDateTime endDay = today.atTime(LocalTime.MAX);
        List<AppointmentBooking> ap = repo.findByClinic_IdAndRegisteredAtBetween(clinicId,startDay,endDay);
        return ap;
    }

    public List<AppointmentBooking> getMyAppointments(Long userId) {
        return repo.findByUser_Id(userId);
    }

    public AppointmentBooking getMyAppointment(Long id, Long profileId) {
        AppointmentBooking ap = repo.findById(id).orElseThrow();
        if(!ap.getUser().getId().equals(profileId)){
            throw new AppointmentDoesNotExistsException("This appointment does not belong to the user");
        }
        return ap;
    }

    public void cancelAppointment(AppointmentBooking ap) {
        ap.setStatus(StatusEnum.CANCELED);
        repo.save(ap);
    }
}

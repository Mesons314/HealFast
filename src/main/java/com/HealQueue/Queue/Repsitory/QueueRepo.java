package com.HealQueue.Queue.Repsitory;

import com.HealQueue.Queue.Model.AppointmentBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface QueueRepo extends JpaRepository<AppointmentBooking , Long> {

    //in this since my appointment bookings is taking value from the Clinic Table i need
    //to use a.clinic.id
    @Modifying
    @Transactional
    @Query("UPDATE AppointmentBooking a " +
            "SET a.patientNo = a.patientNo - 1 " +
            "WHERE a.patientNo > :deletedNo " +
            "AND a.clinic.id = :clinicId")
    void updateNo(@Param("deletedNo")int deletedNo, @Param("clinicId") Long clinicId);

    @Modifying
    @Transactional
    @Query("UPDATE AppointmentBooking a " +
            "SET a.duration = a.duration - 10 " +
            "WHERE a.patientNo > :deletedNo " +
            "AND a.clinic.id = :clinicId")
    void timeRemained(@Param("deletedNo") int deletedNo ,@Param("clinicId") Long clinicId);

//    int countByClinicId(Long clinicId);

    List<AppointmentBooking> findAllByClinicId(Long clinicId);

//    Optional<AppointmentBooking> findByIdAndClinicId(Long id, Long clinicId);

    Optional<AppointmentBooking> findByIdAndUserId(long id, Long userId);

    int countByClinic_Id(long clinicId);

    Optional<AppointmentBooking> findByIdAndClinic_Id(long id, Long clinicId);

}
package com.HealQueue.Queue.Repsitory;

import com.HealQueue.Queue.Model.AppointmentBooking;
import com.HealQueue.Queue.Model.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface QueueRepo extends JpaRepository<AppointmentBooking , Long> {

    //in this since my appointment bookings is taking value from the Clinic Table i need
    //to use a.clinic.id
//    @Modifying
//    @Transactional
//    @Query("UPDATE AppointmentBooking a " +
//            "SET a.patientNo = a.patientNo - 1 " +
//            "WHERE a.patientNo > :deletedNo " +
//            "AND a.clinic.id = :clinicId")
//    void updateNo(@Param("deletedNo")int deletedNo, @Param("clinicId") Long clinicId);
//
//    @Modifying
//    @Transactional
//    @Query("UPDATE AppointmentBooking a " +
//            "SET a.duration = a.duration - 10 " +
//            "WHERE a.patientNo > :deletedNo " +
//            "AND a.clinic.id = :clinicId")
//    void timeRemained(@Param("deletedNo") int deletedNo ,@Param("clinicId") Long clinicId);

    List<AppointmentBooking> findAllByClinicId(Long clinicId);

    Optional<AppointmentBooking> findByIdAndClinic_Id(long id, Long clinicId);

    @Modifying
    @Transactional
    @Query("SELECT COUNT(a) FROM AppointmentBooking a" +
            " WHERE a.clinic.id = :clinicId" +
            " AND a.registeredAt < :localDateTime" +
            " AND a.status = :statusEnum ")
    int countByClinicAndTime(@Param("clinicId") long clinicId, @Param("statusEnum") StatusEnum statusEnum,@Param("localDateTime") LocalDateTime localDateTime);

    Optional<AppointmentBooking> findByIdAndUser_Id(long id, Long userId);
}
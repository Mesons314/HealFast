package com.HealQueue.Queue.Repsitory;

import com.HealQueue.Queue.Model.AppointmentBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QueueRepo extends JpaRepository<AppointmentBooking , Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE AppointmentBooking a SET a.patientNo = a.patientNo - 1")
    void updateNo();
}

package com.HealQueue.Queue.Repsitory;

import com.HealQueue.Queue.Model.AppointmentBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepo extends JpaRepository<AppointmentBooking , Integer> {
}

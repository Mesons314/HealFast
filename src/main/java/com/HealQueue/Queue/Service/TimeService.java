package com.HealQueue.Queue.Service;

import com.HealQueue.Queue.Model.AppointmentBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TimeService {
//currently the data is being added for the entire appointment table
    //not just for the clinic specific so i need to fix that
    public Long getQueueTime(Long size){
        if (size < 3) {
            return 0L;
        }
        return 10L * size - 30;
    }

}

package com.HealQueue.Queue.Service;

import com.HealQueue.CLINIC.DTO.ClinicResponseDTO;
import com.HealQueue.CLINIC.Entity.ClinicInfo;
import com.HealQueue.CLINIC.Service.ClinicService;
import com.HealQueue.Queue.DTO.QueueResponseDTO;
import com.HealQueue.Queue.Model.AppointmentBooking;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CallService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    @Autowired
    private QueueService queueService;

    @Autowired
    private ClinicService clinicService;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }
//    @Scheduled(fixedRate = 60000) // runs every 1 minute, need to add flag so that it calls only one time
//    The function should not be called multiple times instead the http method should be called multiple times
    public void checkAndCallPatients(Long id) {
        List<AppointmentBooking> queue = queueService.getAllQueue(id);
        for (AppointmentBooking booking : queue) {
            QueueResponseDTO dto  = queueService.getTimeAndStatus(booking);
            int waitingTime = dto.getEstimatedWaitTime();
            if (waitingTime > 0 && waitingTime <= 10) {
                makeCall(booking.getMobileNo(), booking.getPatientName());
            }
        }
    }

//add these things
//✅ callSent flag
//✅ Proper validation in controller
//✅ Update only after success
//✅ Optional transaction handling
    private void makeCall(String mobileNo, String patientName) {
        try {
            String message = "Hello " + patientName + ", please visit the Clinic now for your appointment.";
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);

            Call.creator(
                    new PhoneNumber("+91" + mobileNo),
                    new PhoneNumber(fromPhoneNumber),
                    URI.create("http://twimlets.com/message?Message[0]=" + encodedMessage)
            ).create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

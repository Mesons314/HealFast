//package com.HealQueue.Queue.Service;
//
//import com.HealQueue.Queue.Model.AppointmentBooking;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Call;
//import com.twilio.type.PhoneNumber;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.net.URI;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
//@Service
//public class CallService {
//
//    @Value("${twilio.account.sid}")
//    private String accountSid;
//
//    @Value("${twilio.auth.token}")
//    private String authToken;
//
//    @Value("${twilio.phone.number}")
//    private String fromPhoneNumber;
//
//    @Autowired
//    private QueueService queueService;
//
//    @PostConstruct
//    public void initTwilio() {
//        Twilio.init(accountSid, authToken);
//    }
//
//    @Scheduled(fixedRate = 60000) // runs every 1 minute, need to add flag so that it calls only one time
//    public void checkAndCallPatients() {
//        List<AppointmentBooking> queue = queueService.getAllQueue();
//        for (AppointmentBooking booking : queue) {
//            long duration = booking.getDuration();
//            if (duration > 0 && duration <= 10) {
//                makeCall(booking.getMobileNo(), booking.getPatientName());
//            }
//        }
//    }
//
//    private void makeCall(String mobileNo, String patientName) {
//        try {
//            String message = "Hello " + patientName + ", please visit the Clinic now for your appointment.";
//            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
//
//            Call.creator(
//                    new PhoneNumber("+91" + mobileNo),
//                    new PhoneNumber(fromPhoneNumber),
//                    URI.create("http://twimlets.com/message?Message[0]=" + encodedMessage)
//            ).create();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

package com.HealQueue.USER.Controller;

import com.HealQueue.CLINIC.DTO.ClinicResponseDTO;
import com.HealQueue.CLINIC.Entity.ClinicInfo;
import com.HealQueue.CLINIC.Service.ClinicService;
import com.HealQueue.googleMap.Service.GoogleMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class getClinicController {

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private GoogleMapService googleMapService;

    @GetMapping("/getClinic")
    public ResponseEntity<List<ClinicInfo>> getClinicList(){
        return new ResponseEntity<>(clinicService.getClinic(), HttpStatus.OK);
    }

    @GetMapping("/getClinic/{id}")
    public ResponseEntity<ClinicResponseDTO> clinicInfo(@PathVariable long id){
        ClinicResponseDTO clinicResponseDTO = clinicService.findById(id);
//        try{
//            GeocodingResult[] geocodingResults = googleMapService.geocodeAddress(clinic.getAddress());
//            double lat = 0;
//            double lon = 0;
//
//            if(geocodingResults.length>0){
//                lat = geocodingResults[0].geometry.location.lat;
//                lon = geocodingResults[0].geometry.location.lng;
//            }
//            return ResponseEntity.ok(new ClinicResponse(clinic, lat, lon));
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
        try {
            return new ResponseEntity<>(clinicResponseDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

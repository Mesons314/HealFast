package com.HealQueue.USER;

import com.HealQueue.Auth.Entity.ClinicInfo;
import com.HealQueue.Auth.Service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {



    @Autowired
    private ClinicService clinicService;

    @GetMapping("/getClinic")
    public ResponseEntity<List<ClinicInfo>> getClinicList(){
        return new ResponseEntity<>(clinicService.getClinic(), HttpStatus.OK);
    }

}

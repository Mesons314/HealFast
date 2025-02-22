package com.HealQueue.CLINIC.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clinic")
public class ClinicController {

    @GetMapping("/clinicData")
    public String getDataById(){
        return "";
    }

}

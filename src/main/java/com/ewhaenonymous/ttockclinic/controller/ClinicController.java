package com.ewhaenonymous.ttockclinic.controller;

import com.ewhaenonymous.ttockclinic.service.ClinicService;
import com.ewhaenonymous.ttockclinic.domain.Clinic;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/v1")
@RestController
@AllArgsConstructor

public class ClinicController {
    private ClinicService clinicService;

    @PostMapping("/papers")
    public ResponseEntity<?> create(@RequestBody @Valid Clinic clinic){
        clinicService.createNewClinic(clinic);
        return ResponseEntity<>()
    }

    @GetMapping("/clinics/waitings")
    int returnwatings(@RequestBody @Valid Clinic clinic){

        return clinicService.returnwaiting(clinic);
    }
}

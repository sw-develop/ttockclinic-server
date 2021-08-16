package com.ewhaenonymous.ttockclinic.clinics;

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
        clinicService.firstc(clinic);
        return ResponseEntity<>()
    }

    @GetMapping("/clinics/waitings")
    int returnwatings(@RequestBody @Valid Clinic clinic){
        return clinicService.returnwaiting(clinic);
    }
}

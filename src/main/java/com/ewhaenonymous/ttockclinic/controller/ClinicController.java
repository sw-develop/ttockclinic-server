package com.ewhaenonymous.ttockclinic.controller;

import com.ewhaenonymous.ttockclinic.payload.request.GetClinicRequest;
import com.ewhaenonymous.ttockclinic.payload.response.ClinicResponse;
import com.ewhaenonymous.ttockclinic.service.ClinicService;
import com.ewhaenonymous.ttockclinic.domain.Clinic;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/v1")
@RestController
@AllArgsConstructor

public class ClinicController {
    private ClinicService clinicService;

    @GetMapping("/clinics/waitings")
    public ResponseEntity<?> returnwaitings(@RequestBody @Valid GetClinicRequest clinicRequest){
        ClinicResponse clinicResponse = clinicService.returnwaiting(clinicRequest.getLongitude(), clinicRequest.getLatitude());
        return new ResponseEntity<>(clinicResponse, HttpStatus.OK);
    }
}

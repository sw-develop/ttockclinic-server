package com.ewhaenonymous.ttockclinic.controller;

import com.ewhaenonymous.ttockclinic.payload.request.CreateClinicRequest;
import com.ewhaenonymous.ttockclinic.payload.request.GetClinicRequest;
import com.ewhaenonymous.ttockclinic.payload.response.ClinicResponse;
import com.ewhaenonymous.ttockclinic.service.ClinicService;
import com.ewhaenonymous.ttockclinic.service.PaperService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin("http://localhost:19006")
@RequestMapping("/v1")
@RestController
@AllArgsConstructor

public class ClinicController {
    private ClinicService clinicService;
    private PaperService paperService;

    @PostMapping("/papers")
    public ResponseEntity<?> saveClinicPaper(@RequestBody @Valid CreateClinicRequest createRequest){
        clinicService.createNewClinic(createRequest);
        paperService.createNewPaper(createRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/clinics/waitings")
    public ResponseEntity<?> returnWaitings(@RequestBody @Valid GetClinicRequest clinicRequest){
        ClinicResponse clinicResponse = clinicService.returnWaiting(clinicRequest.getLatitude(), clinicRequest.getLongitude());
        return new ResponseEntity<>(clinicResponse, HttpStatus.OK);
    }
}

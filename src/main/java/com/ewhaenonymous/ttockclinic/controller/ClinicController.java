package com.ewhaenonymous.ttockclinic.controller;

import com.ewhaenonymous.ttockclinic.ClinicService;
import com.ewhaenonymous.ttockclinic.payload.ClinicListResponse;
import com.ewhaenonymous.ttockclinic.payload.ClinicResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:19006")
@RequestMapping("/v1")
@RestController
@AllArgsConstructor
public class ClinicController {
    private ClinicService clinicService;

    @GetMapping("/clinics")
    public ResponseEntity<?> listClinics(
            @RequestParam(value = "addr1") String siDo,
            @RequestParam(value = "addr2") String siGunGU
    ){
        List<ClinicListResponse> clinicListResponses = clinicService.findClinicsBySidoAndSiGunGu(siDo, siGunGU);
        return new ResponseEntity<>(clinicListResponses, HttpStatus.OK);
    } // Clinic List 반환

    @GetMapping("/clinic/{id}")
    public ResponseEntity<?> getClinic(
            @PathVariable Long id
    ){
        ClinicResponse clinicResponse = clinicService.findClinicById(id);
        return new ResponseEntity<>(clinicResponse, HttpStatus.OK);
    } // Clinic Object 반환
}

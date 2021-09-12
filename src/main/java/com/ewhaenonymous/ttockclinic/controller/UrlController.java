package com.ewhaenonymous.ttockclinic.controller;

import com.ewhaenonymous.ttockclinic.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor

public class UrlController {
    private UrlService urlService;

    @GetMapping("/covid-url")
    public ResponseEntity<?> covidUrl(){
        urlService.covidApi();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/clinic-url")
    public ResponseEntity<?> clinicUrl(){
        urlService.clinicApi();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

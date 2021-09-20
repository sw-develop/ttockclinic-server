package com.ewhaenonymous.ttockclinic.controller;

import com.ewhaenonymous.ttockclinic.service.UrlService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:19006")
@RestController
@RequestMapping("/v1")
@AllArgsConstructor

public class UrlController {
    private UrlService urlService;

    @GetMapping("/covid-url")
    public ResponseEntity<?> covidUrl() throws JsonProcessingException {
        Object json = urlService.covidApi();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/clinic-url")
    public ResponseEntity<?> clinicUrl() throws JsonProcessingException {
        Object json = urlService.clinicApi();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}

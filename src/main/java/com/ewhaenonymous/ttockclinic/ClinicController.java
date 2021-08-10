package com.ewhaenonymous.ttockclinic;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor

public class ClinicController {
    private ClinicService clinicService;

    @GetMapping("/v1/papers")
    public @ResponseBody
    void create(@RequestBody Clinic clinic){
        clinicService.firstc(clinic);
    }
}

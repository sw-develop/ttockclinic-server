package com.ewhaenonymous.ttockclinic;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class ClinicController {
    private ClinicService clinicService;

    @PostMapping("/v1/papers")
    public @ResponseBody
    void create(@RequestBody Clinic clinic){
        clinicService.firstc(clinic);
    }

    @PatchMapping("v1/papers/{paperid}/usages")
    public @ResponseBody
    void update(@RequestBody Clinic clinic, @RequestBody Paper paper){
        if(paper.usages == 1){
            clinicService.upwaiting(clinic);
        }
        else if(paper.usages == 2){
            clinicService.downwaiting(clinic);
        }
        else{
            throw new IllegalStateException("Error");
        }
    }

    @GetMapping("v1/clinics?cname={cname}")
    public @ResponseBody
    int returnwatings(@RequestBody Clinic clinic){
        return clinicService.returnwaiting(clinic);
    }
}

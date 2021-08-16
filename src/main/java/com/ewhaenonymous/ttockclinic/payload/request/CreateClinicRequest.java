package com.ewhaenonymous.ttockclinic.payload.request;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import com.ewhaenonymous.ttockclinic.domain.Paper;

import javax.validation.constraints.NotNull;

public class CreateClinicRequest {
    @NotNull
    private String latitude;
    @NotNull
    private String longitude;

    public Clinic toEntity(){
        return Clinic.builder()
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}

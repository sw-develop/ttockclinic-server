package com.ewhaenonymous.ttockclinic.payload.response;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicResponse {
    private long id;
    private String longitude;
    private String latitude;
    private int waitings;

    public ClinicResponse(Clinic clinic){
        this(clinic.getId(), clinic.getLongitude(), clinic.getLatitude(), clinic.getWaitings());
    }
}

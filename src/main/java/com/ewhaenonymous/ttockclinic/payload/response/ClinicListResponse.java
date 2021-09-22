package com.ewhaenonymous.ttockclinic.payload.response;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicListResponse {
    private Long id;
    private String siDo;
    private String siGunGu;
    private String name;
    private String address;
    private int waitings;

    public ClinicListResponse(Clinic clinic){
        this(clinic.getId(), clinic.getSiDo(), clinic.getSiGunGu(), clinic.getName(), clinic.getAddress(), clinic.getWaitings());
    }
}

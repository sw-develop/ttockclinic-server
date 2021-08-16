package com.ewhaenonymous.ttockclinic.payload.request;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateClinicRequest {
    @NotNull
    private String latitude;
    @NotNull
    private String longitude;
    @NotNull
    private String name;
    @NotNull
    private String phone;

    public Clinic toEntity(){
        return Clinic.builder()
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}

package com.ewhaenonymous.ttockclinic.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetClinicRequest {
    private String longitude;
    private String latitude;
}

package com.ewhaenonymous.ttockclinic.payload;

import com.ewhaenonymous.ttockclinic.domain.Clinic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicResponse {
    private Long id;
    private String name;
    private String address;
    private String weekdayOpeningHours;
    private String saturdayOpeningHours;
    private String holidayOpeningHours;
    private String phone;
    private int waitings;

    public ClinicResponse(Clinic clinic){
        this(
                clinic.getId(), clinic.getName(), clinic.getAddress(), clinic.getWeekdayOpeningHours(), clinic.getSaturdayOpeningHours(), clinic.getHolidayOpeningHours(), clinic.getPhone(), clinic.getWaitings()
        );
    }

}

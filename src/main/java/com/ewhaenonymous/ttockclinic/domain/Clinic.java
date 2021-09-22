package com.ewhaenonymous.ttockclinic.domain;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="clinic")

public class Clinic extends BaseTimeEntity{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_id")
    private Long id;

    @Column(name = "si_do")
    private String siDo;

    @Column(name = "si_gun_gu")
    private String siGunGu;

    private String name;

    private String address;

    @Column(name = "weekday_opening_hours")
    private String weekdayOpeningHours;

    @Column(name = "saturday_opening_hours")
    private String saturdayOpeningHours;

    @Column(name = "holiday_opening_hours")
    private String holidayOpeningHours;

    private String phone;

    @Builder.Default
    @Column(name = "waitings", nullable = false)
    private int waitings = 0;

}

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
@Table(name="clinics")

public class Clinic extends BaseTimeEntity{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_id")
    private Long id;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "waitings", nullable = false)
    private int waitings = 0;

}

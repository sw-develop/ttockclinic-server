package com.ewhaenonymous.ttockclinic.domain;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="clinics")

public class Clinic {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "clinic_id")
    private Long id;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "latitude", nullable = false) //위도
    private String latitutde;

    @Column(name = "waitings", nullable = false)
    private int waitings = 1;
}

package com.ewhaenonymous.ttockclinic.clinics;
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

    @Column(name = "clinic_name", nullable = false)
    private String cname;

    @Column(name = "loc", nullable = false)
    private String loc;

    @Column(name = "waitings", nullable = false)
    private int waitings = 0;
}

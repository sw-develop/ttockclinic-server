package com.ewhaenonymous.ttockclinic.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Paper extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id")
    private Long id;

    private String name;

    private String phone;

    @Column(name = "qr_usage_count")
    private int qrUsageCount;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id") //FK - 연관관계 주인
    private Clinic clinic;
}

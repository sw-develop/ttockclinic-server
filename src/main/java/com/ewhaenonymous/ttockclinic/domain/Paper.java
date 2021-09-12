package com.ewhaenonymous.ttockclinic.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class Paper extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id")
    private Long id;

    private String name;

    private String phone;

    @Column(name = "qr_usage_count")
    private int qrUsageCount;

    //이게 객체 생성 날짜랑 똑같은 거 아닌가?
    @CreatedDate
    private LocalDate date;

    @Builder.Default
    private String deleted = "N";

    @ManyToOne()
    @JoinColumn(name = "clinic_id") //FK - 연관관계 주인
    private Clinic clinic;
}

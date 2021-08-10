package com.ewhaenonymous.ttockclinic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j

public class ClinicRepositoryTest extends TtockclinicApplicationTests{
    @Autowired
    private ClinicRepository clinicRepository;
    @Test
    public void create(){
        Clinic c =
                Clinic.builder()
                .name("관악구선별진료소")
                .waitings(0)
                .build();

        Clinic newC = clinicRepository.save(c);
        log.info(newC.toString());
    }

    @Test
    public void read(){
        Optional<Clinic> c = clinicRepository.findById(2L);
        c.ifPresent(
                selectClinic -> {
                    log.info(selectClinic.toString());
                });
    }

    @Test
    public void update() {
        Optional<Clinic> c = clinicRepository.findById(2L);

        c.ifPresent(
                selectClinic -> {
                    Clinic newC =
                        Clinic.builder()
                            .id(selectClinic.getId())
                            .name(selectClinic.getName())
                            .waitings(selectClinic.getWaitings() + 1)
                            .build();
                    clinicRepository.save(newC);
                }
        );
    }
}

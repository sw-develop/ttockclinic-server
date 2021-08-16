package com.ewhaenonymous.ttockclinic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@Slf4j

public class ClinicRepositoryTest extends TtockclinicApplicationTests{
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private ClinicService clinicService;
  /*  @Test
    public void create(){
        Clinic c =
                Clinic.builder()
                        .cname("관악구선별진료소")
                        .loc("durl")
                        .waitings(0)
                        .build();

        Clinic newC = clinicRepository.save(c);
        log.info(newC.toString());
    }
*/
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
        Clinic c =
                Clinic.builder()
                        .cname("관악구선별진료소")
                        .loc("durl")
                        .waitings(0)
                        .build();

        clinicService.upwaiting(c);
    }

    @Test
    public void exce() throws Exception{

        Clinic c1 =
                Clinic.builder()
                        .cname("관악구선별진료소")
                        .loc("durl")
                        .waitings(0)
                        .build();

        clinicService.firstc(c1);

        Clinic c2 =
                Clinic.builder()
                        .cname("관악구선별진료소")
                        .loc("durl")
                        .waitings(0)
                        .build();

        IllegalStateException thrown = assertThrows(IllegalStateException.class, ()->clinicService.firstc(c2));
        assertEquals("이미 존재하는 선별진료소입니다.", thrown.getMessage());
    }
}

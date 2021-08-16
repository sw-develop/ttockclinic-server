package com.ewhaenonymous.ttockclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing 활성화를 위한 어노테이션
@SpringBootApplication
public class TtockclinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(TtockclinicApplication.class, args);
	}

}

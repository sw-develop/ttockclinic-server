package com.ewhaenonymous.ttockclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing //JPA Auditing 활성화를 위한 어노테이션
@SpringBootApplication
@EnableScheduling	//자정마다 데이터 삭제 처리
public class TtockclinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(TtockclinicApplication.class, args);
	}

}

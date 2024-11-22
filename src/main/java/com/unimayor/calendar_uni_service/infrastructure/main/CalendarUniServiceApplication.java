package com.unimayor.calendar_uni_service.infrastructure.main;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
		scanBasePackages = {
				"com.unimayor.calendar_uni_service.infrastructure",
				"com.unimayor.calendar_uni_service.modules",
				"com.unimayor.calendar_uni_service.core"
		}
)
@EnableJpaRepositories(
		basePackages = {"com.unimayor.calendar_uni_service.core.persistence.repository"}
)
@EntityScan(basePackages = "com.unimayor.calendar_uni_service.core.persistence.entity")
@EnableScheduling
public class CalendarUniServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarUniServiceApplication.class, args);
	}

}

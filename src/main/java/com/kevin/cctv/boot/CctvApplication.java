package com.kevin.cctv.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CctvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CctvApplication.class, args);
	}

}

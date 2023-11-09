package com.oracle.s202350101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class S202350101Application {

	public static void main(String[] args) {
		SpringApplication.run(S202350101Application.class, args);
	}

}

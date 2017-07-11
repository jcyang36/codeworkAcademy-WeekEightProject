package com.example.demo;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.expression.Lists;

@SpringBootApplication
@EnableEmailTools
public class WeekEightChallengeApplication {
		public static void main(String[] args) {
		SpringApplication.run(WeekEightChallengeApplication.class, args);
	}
}

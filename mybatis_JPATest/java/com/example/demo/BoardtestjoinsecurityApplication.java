package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BoardtestjoinsecurityApplication {

	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("passwordEncoder 만들어짐!");
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BoardtestjoinsecurityApplication.class, args);
	}

}

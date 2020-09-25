package com.doggers.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DoggerappApplication implements CommandLineRunner {

	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(DoggerappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String passwordString = "armando22";
		
		for(int i = 0; i < 4 ; i++) {
			String passwordBcrypt = passwordEncoder.encode(passwordString);
			System.out.println(passwordBcrypt);
		}
		
	}

}

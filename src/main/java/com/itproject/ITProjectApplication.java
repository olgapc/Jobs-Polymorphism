package com.itproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ITProjectApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ITProjectApplication.class, args);
	}
	
}

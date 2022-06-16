package com.spring.sleuthclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SleuthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SleuthClientApplication.class, args);
	}

}

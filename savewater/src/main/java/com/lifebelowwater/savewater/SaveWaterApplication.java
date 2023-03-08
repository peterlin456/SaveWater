package com.lifebelowwater.savewater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SaveWaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaveWaterApplication.class, args);
	}

}

package com.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.family.repository")
public class Family5Application {

	public static void main(String[] args) {
		SpringApplication.run(Family5Application.class, args);
	}

}

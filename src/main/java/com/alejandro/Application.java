package com.alejandro;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.alejandro.repository")
@EntityScan(basePackages = "com.alejandro.entidad")

@SpringBootApplication(scanBasePackages = {"com.alejandro"})


public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	

}

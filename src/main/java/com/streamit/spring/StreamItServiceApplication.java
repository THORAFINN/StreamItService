package com.streamit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient   
public class StreamItServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamItServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getEncoder() {
		return new  BCryptPasswordEncoder();
	}
}

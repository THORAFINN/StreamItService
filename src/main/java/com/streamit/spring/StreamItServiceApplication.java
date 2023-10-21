package com.streamit.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient 
public class StreamItServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamItServiceApplication.class, args);
	}

}

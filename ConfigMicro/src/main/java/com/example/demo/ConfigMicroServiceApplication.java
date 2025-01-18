package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigMicroServiceApplication.class, args);
	}

}

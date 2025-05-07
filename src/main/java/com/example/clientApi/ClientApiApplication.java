package com.example.clientApi;

import com.example.clientApi.config.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ClientApiApplication {

	public static void main(String[] args) {
		EnvLoader loader = new EnvLoader();
		SpringApplication.run(ClientApiApplication.class, args);
	}

}

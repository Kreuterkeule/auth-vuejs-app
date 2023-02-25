package com.kreuterkeule.authvuejsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class AuthVuejsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthVuejsAppApplication.class, args);
	}

}

package com.example.springwebtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringwebtaskApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =
			SpringApplication.run(SpringwebtaskApplication.class, args);
	}

}

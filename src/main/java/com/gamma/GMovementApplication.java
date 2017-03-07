package com.gamma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

//@SpringBootApplication(scanBasePackages = {"com.gamma.Controller"})
@SpringBootApplication
public class GMovementApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GMovementApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(GMovementApplication.class, args);
	}

}

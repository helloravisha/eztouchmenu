package com.ezmenutouch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = { "com.ezmenutouch.controller"})
@ComponentScan
@EnableWebMvc
public class OrderServicesApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OrderServicesApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(OrderServicesApplication.class, args); // it will start application
	}
}
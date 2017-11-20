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
public class MenuServicesApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MenuServicesApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MenuServicesApplication.class, args); // it will start application
	}
}
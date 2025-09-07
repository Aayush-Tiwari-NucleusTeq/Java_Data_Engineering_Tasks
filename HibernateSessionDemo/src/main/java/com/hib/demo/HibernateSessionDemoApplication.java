package com.hib.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.hib.demo.service.MyService;

@SpringBootApplication
public class HibernateSessionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateSessionDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(MyService service) {
		return args -> {
			service.testFlush();
		};
	}
}

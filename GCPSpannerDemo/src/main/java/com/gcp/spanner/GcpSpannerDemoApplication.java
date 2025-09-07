package com.gcp.spanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.cloud.spring.data.spanner.repository.config.EnableSpannerRepositories;

@SpringBootApplication
@EnableSpannerRepositories
public class GcpSpannerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcpSpannerDemoApplication.class, args);
	}

}

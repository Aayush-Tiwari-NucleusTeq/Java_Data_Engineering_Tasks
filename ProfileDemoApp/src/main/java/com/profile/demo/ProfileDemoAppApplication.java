package com.profile.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.profile.demo.service.NotificationService;

@SpringBootApplication
public class ProfileDemoAppApplication implements CommandLineRunner{
	
	@Autowired
	private NotificationService notificationService;

	public static void main(String[] args) {
		SpringApplication.run(ProfileDemoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		notificationService.sendNotification("Welcome to Profile Demo!");
	}
}

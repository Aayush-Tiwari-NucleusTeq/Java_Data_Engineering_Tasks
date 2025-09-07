package com.profile.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class EmailService implements NotificationService {

    @Value("${email.sender}")
    private String sender;

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Email from " + sender + ": " + message);
    }
}
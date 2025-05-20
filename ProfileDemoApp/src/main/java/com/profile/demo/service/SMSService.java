package com.profile.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class SMSService implements NotificationService {

    @Value("${sms.sender}")
    private String sender;

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS from " + sender + ": " + message);
    }
}

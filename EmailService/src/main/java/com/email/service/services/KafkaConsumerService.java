package com.email.service.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.email.service.entities.Employee;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "employee-topic", groupId = "email-group")
    public void consume(Employee employee) {
        System.out.println("Received Employee event: " + employee.getEmpId() + ", " + employee.getName());
    }
}


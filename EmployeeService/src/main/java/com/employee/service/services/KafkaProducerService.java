package com.employee.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.employee.service.entities.Employee;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    public void sendEmployeeEvent(Employee employee) {
        kafkaTemplate.send("employee-topic", employee);
    }
}

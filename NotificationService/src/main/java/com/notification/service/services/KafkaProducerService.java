package com.notification.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.notification.service.entities.Order;

@Service
public class KafkaProducerService {

	@Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrderEvent(Order order) {
        kafkaTemplate.send("order-topic", order);
    }
}

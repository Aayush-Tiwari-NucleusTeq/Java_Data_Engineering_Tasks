package com.order.service.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.order.service.entities.Order;

@Service
public class KafkaConsumerService {

	 @KafkaListener(topics = "order-topic", groupId = "order-group")
	    public void consume(Order order) {
	        System.out.println("Received Order event: " + order.getProduct() + ", " + order.getPrice() + ", " + order.getQuantity());
	    }
}

package com.notification.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.service.entities.Order;
import com.notification.service.services.KafkaProducerService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private KafkaProducerService kafkaProducerService;

	@PostMapping("/order")
	public String test(@RequestBody Order order) {
		kafkaProducerService.sendOrderEvent(order);
		System.out.println("Sucessfully tested notification service !!" + order);
		return null;
	}
}

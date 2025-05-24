package com.order.service.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.service.entities.Order;

@FeignClient(url = "http://localhost:8082", value = "notification-client")
public interface NotificationClient {

	@PostMapping("/notification/order")
	void sendOrderNotification(@RequestBody Order order);
}

package com.order.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.dto.in.OrderInDto;
import com.order.service.dto.out.OrderOutDto;
import com.order.service.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	// Create a new order
    @PostMapping
    public ResponseEntity<OrderOutDto> createOrder(@RequestBody OrderInDto orderInDto) {
        OrderOutDto createdOrder = orderService.createOrder(orderInDto);
        return ResponseEntity.ok(createdOrder);
    }

    // Get all orders
    @GetMapping
    public ResponseEntity<List<OrderOutDto>> getAllOrders() {
        List<OrderOutDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderOutDto> getOrderById(@PathVariable int id) {
        OrderOutDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // Update an existing order
    @PutMapping("/{id}")
    public ResponseEntity<OrderOutDto> updateOrder(@PathVariable int id, @RequestBody OrderInDto orderInDto) {
        OrderOutDto updatedOrder = orderService.updateOrder(id, orderInDto);
        return ResponseEntity.ok(updatedOrder);
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

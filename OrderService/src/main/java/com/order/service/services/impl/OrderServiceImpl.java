package com.order.service.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.service.dto.in.OrderInDto;
import com.order.service.dto.out.OrderOutDto;
import com.order.service.entities.Order;
import com.order.service.repository.OrderRepository;
import com.order.service.services.NotificationClient;
import com.order.service.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private NotificationClient notificationClient;
	
	
	@Override
    public OrderOutDto createOrder(OrderInDto orderInDto) {
        Order order = convertToEntity(orderInDto);
        Order savedOrder = orderRepository.save(order);
        OrderOutDto response = convertToOutDto(savedOrder);

        // Notify via Feign client
        notificationClient.sendOrderNotification(order);

        return response;
    }

    @Override
    public List<OrderOutDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToOutDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderOutDto getOrderById(int id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        return orderOpt.map(this::convertToOutDto)
                       .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }

    @Override
    public OrderOutDto updateOrder(int id, OrderInDto orderInDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        
        order.setProduct(orderInDto.getProduct());
        order.setQuantity(orderInDto.getQuantity());
        order.setPrice(orderInDto.getPrice());

        return convertToOutDto(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(int id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }

    private Order convertToEntity(OrderInDto dto) {
        Order order = new Order();
        order.setProduct(dto.getProduct());
        order.setQuantity(dto.getQuantity());
        order.setPrice(dto.getPrice());
        return order;
    }

    private OrderOutDto convertToOutDto(Order order) {
        OrderOutDto dto = new OrderOutDto();
        dto.setProduct(order.getProduct());
        dto.setQuantity(order.getQuantity());
        dto.setPrice(order.getPrice());
        return dto;
    }
}

package com.order.service.services;

import java.util.List;

import com.order.service.dto.in.OrderInDto;
import com.order.service.dto.out.OrderOutDto;

public interface OrderService {

	OrderOutDto createOrder(OrderInDto orderInDto);
    List<OrderOutDto> getAllOrders();
    OrderOutDto getOrderById(int id);
    OrderOutDto updateOrder(int id, OrderInDto orderInDto);
    void deleteOrder(int id);
}

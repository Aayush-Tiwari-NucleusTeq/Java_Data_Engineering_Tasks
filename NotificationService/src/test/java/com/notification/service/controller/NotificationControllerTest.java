package com.notification.service.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.service.entities.Order;
import com.notification.service.services.KafkaProducerService;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KafkaProducerService kafkaProducerService;

    private ObjectMapper objectMapper;
    private Order order;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        order = new Order();
        order.setProduct("Book");
        order.setQuantity(2);
        order.setPrice(500.0);
    }

    @Test
    public void testSendOrderToKafka() throws Exception {
        doNothing().when(kafkaProducerService).sendOrderEvent(any(Order.class));

        mockMvc.perform(post("/notification/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk());
    }
}
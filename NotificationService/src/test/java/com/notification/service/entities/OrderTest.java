package com.notification.service.entities;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void testOrderAllArgsConstructor() {
        Order order = new Order("Laptop", 2, 1500.0);

        assertEquals("Laptop", order.getProduct());
        assertEquals(2, order.getQuantity());
        assertEquals(1500.0, order.getPrice(), 0.01);
    }

    @Test
    public void testOrderSettersAndGetters() {
        Order order = new Order();
        order.setProduct("Phone");
        order.setQuantity(5);
        order.setPrice(999.99);

        assertEquals("Phone", order.getProduct());
        assertEquals(5, order.getQuantity());
        assertEquals(999.99, order.getPrice(), 0.01);
    }

    @Test
    public void testToString() {
        Order order = new Order("Tablet", 1, 500.0);
        String output = order.toString();

        assertTrue(output.contains("Tablet"));
        assertTrue(output.contains("1"));
        assertTrue(output.contains("500.0"));
    }
}

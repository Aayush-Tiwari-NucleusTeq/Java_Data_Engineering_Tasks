package com.product.catalog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.http.MediaType;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.catalog.dto.in.ProductInDto;
import com.product.catalog.dto.out.ProductOutDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static int createdProductId;

    @Test
    @Order(1)
    public void testCreateProduct() {
        ProductInDto product = new ProductInDto();
        product.setProductName("Phone");
        product.setDescription("iPhone 15");
        product.setAssignedTo("Mark");

        ResponseEntity<ProductOutDto> response = restTemplate.postForEntity("/product", product, ProductOutDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        createdProductId = response.getBody().getProductId();
    }

    @Test
    @Order(2)
    public void testReadProduct() {
        ResponseEntity<ProductOutDto> response = restTemplate.getForEntity("/product/" + createdProductId, ProductOutDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Phone", response.getBody().getProductName());
    }

    @Test
    @Order(3)
    public void testReadAllProducts() {
        ResponseEntity<ProductOutDto[]> response = restTemplate.getForEntity("/product", ProductOutDto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length >= 1);
    }

    @Test
    @Order(4)
    public void testUpdateProduct() {
        String url = "/product/" + createdProductId + "/Updated Description";
        ResponseEntity<ProductOutDto> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                ProductOutDto.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Description", response.getBody().getDescription());
    }

    @Test
    @Order(5)
    public void testDeleteProduct() {
        restTemplate.delete("/product/" + createdProductId);
        ResponseEntity<String> response = restTemplate.getForEntity("/product/" + createdProductId, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}


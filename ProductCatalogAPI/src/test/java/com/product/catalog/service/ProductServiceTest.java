package com.product.catalog.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.product.catalog.dto.in.ProductInDto;
import com.product.catalog.dto.out.ProductOutDto;
import com.product.catalog.entities.Product;
import com.product.catalog.repository.ProductRepository;
import com.product.catalog.services.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Test
    public void testCreateProduct() {
        ProductInDto dto = new ProductInDto();
        dto.setProductName("Laptop");
        dto.setDescription("Dell XPS");
        dto.setAssignedTo("John");

        Product product = new Product();
        product.setProductName("Laptop");
        product.setDescription("Dell XPS");
        product.setAssignedTo("John");

        Product savedProduct = new Product();
        savedProduct.setProductId(1);
        savedProduct.setProductName("Laptop");
        savedProduct.setDescription("Dell XPS");
        savedProduct.setAssignedTo("John");

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(savedProduct);

        ProductOutDto result = productService.createProduct(dto);

        assertEquals(1, result.getProductId());
        assertEquals("Laptop", result.getProductName());
    }

    @Test
    public void testReadProduct() {
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Monitor");
        product.setDescription("Samsung");
        product.setAssignedTo("Alice");

        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));

        ProductOutDto result = productService.readProduct(1);

        assertEquals("Monitor", result.getProductName());
        assertEquals("Alice", result.getAssignedTo());
    }

    @Test
    public void testReadAllProducts() {
        Product p1 = new Product();
        p1.setProductId(1);
        p1.setProductName("Mouse");
        p1.setDescription("Wireless");
        p1.setAssignedTo("Sam");

        Product p2 = new Product();
        p2.setProductId(2);
        p2.setProductName("Keyboard");
        p2.setDescription("Mechanical");
        p2.setAssignedTo("Dean");

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<ProductOutDto> result = productService.readAllProducts();

        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateProduct() {
        Product existing = new Product();
        existing.setProductId(1);
        existing.setProductName("Tablet");
        existing.setDescription("Old desc");
        existing.setAssignedTo("Bob");

        Product updated = new Product();
        updated.setProductId(1);
        updated.setProductName("Tablet");
        updated.setDescription("New desc");
        updated.setAssignedTo("Bob");

        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(existing));
        Mockito.when(productRepository.save(existing)).thenReturn(updated);

        ProductOutDto result = productService.updateProduct("New desc", 1);

        assertEquals("New desc", result.getDescription());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setProductId(1);

        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
        Mockito.doNothing().when(productRepository).deleteById(1);

        String result = productService.deleteProduct(1);

        assertEquals("Product is deleted successfully", result);
    }
}

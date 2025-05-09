package com.product.catalog.controller;

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

import com.product.catalog.dto.in.ProductInDto;
import com.product.catalog.dto.out.ProductOutDto;
import com.product.catalog.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductOutDto> createProduct(@RequestBody ProductInDto productInDto) {
        ProductOutDto createdProduct = productService.createProduct(productInDto);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOutDto> getProduct(@PathVariable int id) {
        ProductOutDto product = productService.readProduct(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductOutDto>> getAllProducts() {
        List<ProductOutDto> products = productService.readAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}/{description}")
    public ResponseEntity<ProductOutDto> updateProduct(
            @PathVariable("id") int id,
            @PathVariable("description") String description) {
        ProductOutDto updatedProduct = productService.updateProduct(description, id);
        return ResponseEntity.ok(updatedProduct);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        String response = productService.deleteProduct(id);
        return ResponseEntity.ok(response);
    }
}

package com.product.catalog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalog.dto.in.ProductInDto;
import com.product.catalog.dto.out.ProductOutDto;
import com.product.catalog.entities.Product;
import com.product.catalog.exception.ResourceNotFoundException;
import com.product.catalog.repository.ProductRepository;
import com.product.catalog.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductOutDto createProduct(ProductInDto productInDto) {
		Product product = this.productInDtoToProduct(productInDto);
		Product savedProduct = this.productRepository.save(product);
		ProductOutDto productOutDto = this.productToProductOutDto(savedProduct);
		return productOutDto;
	}

	@Override
	public ProductOutDto readProduct(int productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with id - " + productId));
		ProductOutDto productOutDto = this.productToProductOutDto(product);
		return productOutDto;
	}

	@Override
	public List<ProductOutDto> readAllProducts() {
		List<Product> products = productRepository.findAll();
	    return products.stream()
	                   .map(this::productToProductOutDto)
	                   .collect(Collectors.toList());
	}

	@Override
	public ProductOutDto updateProduct(String description, int productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with id - " + productId));
		product.setDescription(description);
		Product updatedProduct = this.productRepository.save(product);
		ProductOutDto productOutDto = this.productToProductOutDto(updatedProduct);
		return productOutDto;
	}

	@Override
	public String deleteProduct(int productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with id - " + productId));
		if(product != null) {
			this.productRepository.deleteById(productId);			
			return "Product is deleted successfully";
		}
		return "Something went wrong";
	}
	
    public Product productInDtoToProduct(ProductInDto dto) {
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setAssignedTo(dto.getAssignedTo());
        return product;
    }

    public ProductOutDto productToProductOutDto(Product product) {
    	ProductOutDto dto = new ProductOutDto();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setDescription(product.getDescription());
        dto.setAssignedTo(product.getAssignedTo());
        return dto;
    }

}

package com.product.catalog.services;

import java.util.List;

import com.product.catalog.dto.in.ProductInDto;
import com.product.catalog.dto.out.ProductOutDto;

public interface ProductService {

	ProductOutDto createProduct(ProductInDto product);
	ProductOutDto readProduct(int productId);
	List<ProductOutDto> readAllProducts();
	ProductOutDto updateProduct(String description, int productId);
	String deleteProduct(int productId);
}

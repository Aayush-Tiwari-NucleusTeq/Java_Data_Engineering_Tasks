package com.product.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.catalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}

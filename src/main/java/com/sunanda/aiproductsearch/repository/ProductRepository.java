package com.sunanda.aiproductsearch.repository;

import com.sunanda.aiproductsearch.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

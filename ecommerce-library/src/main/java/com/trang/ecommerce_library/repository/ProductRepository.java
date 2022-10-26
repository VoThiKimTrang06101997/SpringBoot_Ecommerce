package com.trang.ecommerce_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trang.ecommerce_library.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}

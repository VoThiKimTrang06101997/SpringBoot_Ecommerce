package com.trang.ecommerce_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trang.ecommerce_library.model.Order;

@Repository
public interface OrderRespository extends JpaRepository<Order, Long> {
	
}

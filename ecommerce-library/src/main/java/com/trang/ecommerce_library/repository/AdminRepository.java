package com.trang.ecommerce_library.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trang.ecommerce_library.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	 Admin findByUsername(String username);
}

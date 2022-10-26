package com.trang.ecommerce_library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trang.ecommerce_library.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("Select c from Category c where c.is_activated = true and c.is_deleted = false")
	List<Category> findAllByActivated();
}

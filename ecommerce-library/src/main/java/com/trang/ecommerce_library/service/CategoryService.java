package com.trang.ecommerce_library.service;

import java.util.List;

import com.trang.ecommerce_library.dto.CategoryDto;
import com.trang.ecommerce_library.model.Category;
import com.trang.ecommerce_library.model.Product;

public interface CategoryService {
	/* Admin */
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enabledById(Long id);
    List<Category> findAllByActivated();
    
    /* Customer */
    List<CategoryDto> getCategoryAndProduct();
    
   
}

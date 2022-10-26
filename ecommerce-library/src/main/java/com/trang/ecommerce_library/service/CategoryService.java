package com.trang.ecommerce_library.service;

import java.util.List;

import com.trang.ecommerce_library.model.Category;

public interface CategoryService {
	/*Admin*/
    List<Category> findAll();
    Category save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enabledById(Long id);
    List<Category> findAllByActivated();
}

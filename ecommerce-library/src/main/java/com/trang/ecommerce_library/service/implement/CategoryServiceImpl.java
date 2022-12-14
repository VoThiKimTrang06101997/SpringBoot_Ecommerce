package com.trang.ecommerce_library.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trang.ecommerce_library.dto.CategoryDto;
import com.trang.ecommerce_library.model.Category;
import com.trang.ecommerce_library.repository.CategoryRepository;
import com.trang.ecommerce_library.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category save(Category category) {
		Category categorySave = new Category(category.getName());
		return categoryRepository.save(categorySave);
	}

	@Override
	public Category findById(Long id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public Category update(Category category) {
		Category categoryUpdate = null;
		try {
			categoryUpdate = categoryRepository.findById(category.getId()).get();
			categoryUpdate.setName(category.getName());
			categoryUpdate.set_activated(category.is_activated());
			categoryUpdate.set_deleted(category.is_deleted());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return categoryRepository.save(categoryUpdate);
	}

	@Override
	public void deleteById(Long id) {
		Category category = categoryRepository.getById(id);
		category.set_activated(false);
		category.set_deleted(true);
		categoryRepository.save(category);
	}

	@Override
	public void enabledById(Long id) {
		Category category = categoryRepository.getById(id);
		category.set_activated(true);
		category.set_deleted(false);
		categoryRepository.save(category);
	}

	@Override
	public List<Category> findAllByActivated() {
		return categoryRepository.findAllByActivated();
	}
	
	/* Customer */
	@Override
	public List<CategoryDto> getCategoryAndProduct() {
		return categoryRepository.getCategoryAndProduct();
	}



}

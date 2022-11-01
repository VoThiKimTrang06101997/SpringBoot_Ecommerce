package com.trang.ecommerce_library.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trang.ecommerce_library.dto.ProductDto;
import com.trang.ecommerce_library.model.Product;

public interface ProductService {
	/* Admin */
	List<ProductDto> findAll();
	Product save(MultipartFile multipartFile, ProductDto productDto);
	Product update(MultipartFile multipartFile, ProductDto productDto);
	void deleteById(Long id);
	void enableById(Long id);
	ProductDto getById(Long id);
	
	
	Page<ProductDto> pageProducts(int pageNo);

	Page<ProductDto> searchProducts(int pageNo, String keyword);
	
	/* Customer */
	List<Product> getAllProducts();
	
	List<Product> listViewProducts();
	
	Product getProductById(Long id);
	
	List<Product> getRelatedProducts(Long categoryId);
	
	List<Product> getProductsInCategory(Long categoryId);
	
	List<Product> filterHighPrice();
	
	List<Product> filterLowPrice();
}

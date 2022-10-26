package com.trang.ecommerce_library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trang.ecommerce_library.dto.ProductDto;
import com.trang.ecommerce_library.model.Product;

public interface ProductService {
	List<ProductDto> findAll();
	Product save(MultipartFile multipartFile, ProductDto productDto);
	Product update(MultipartFile multipartFile, ProductDto productDto);
	void deleteById(Long id);
	void enableById(Long id);
	ProductDto getById(Long id);
}

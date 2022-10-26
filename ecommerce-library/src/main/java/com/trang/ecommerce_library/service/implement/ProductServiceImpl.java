package com.trang.ecommerce_library.service.implement;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trang.ecommerce_library.dto.ProductDto;
import com.trang.ecommerce_library.model.Product;
import com.trang.ecommerce_library.repository.ProductRepository;
import com.trang.ecommerce_library.service.ProductService;
import com.trang.ecommerce_library.utils.ImageUpload;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ImageUpload imageUpload;

	@Override
	public List<ProductDto> findAll() {
		List<ProductDto> productDtoList = new ArrayList<>();
		List<Product> products = productRepository.findAll();
		for (Product product : products) {
			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setDescription(product.getDescription());
			productDto.setCostPrice(product.getCostPrice());
			productDto.setSalePrice(product.getSalePrice());
			productDto.setCurrentQuantity(product.getCurrentQuantity());
			productDto.setCategory(product.getCategory());
			productDto.setImage(product.getImage());
			productDto.setActivated(product.is_activated());
			productDto.setDeleted(product.is_deleted());
			productDtoList.add(productDto);
		}
		return productDtoList;
	}

	@Override
	public Product save(MultipartFile multipartFile, ProductDto productDto) {
		try {
			Product product = new Product();
			if (multipartFile == null) {
				product.setImage(null);
			} else {
				if (imageUpload.uploadImage(multipartFile)) {
					System.out.println("Upload successfully");
				}
				product.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
			}
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setCategory(productDto.getCategory());
			product.setCostPrice(productDto.getCostPrice());
			product.setCurrentQuantity(productDto.getCurrentQuantity());
			product.set_activated(true);
			product.set_deleted(false);
			return productRepository.save(product);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product update(MultipartFile multipartFile, ProductDto productDto) {
		try {
			Product product = productRepository.getById(productDto.getId());
			
			if (multipartFile == null) {
				product.setImage(product.getImage());
			} else {
				if (imageUpload.checkExisted(multipartFile) == false) {
					imageUpload.uploadImage(multipartFile);
				}
				product.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
			}
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setSalePrice(productDto.getSalePrice());
			product.setCostPrice(productDto.getCostPrice());
			product.setCurrentQuantity(productDto.getCurrentQuantity());
			product.setCategory(productDto.getCategory());
			return productRepository.save(product);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteById(Long id) {
		Product product = productRepository.getById(id);
		product.set_deleted(true);
        product.set_activated(false);
        productRepository.save(product);
	}

	@Override
	public void enableById(Long id) {
		Product product = productRepository.getById(id);
		product.set_activated(true);
		product.set_deleted(false);
		 productRepository.save(product);
	}

	@Override
	public ProductDto getById(Long id) {
		Product product = productRepository.getById(id);
		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setName(product.getName());
		productDto.setDescription(product.getDescription());
		productDto.setCurrentQuantity(product.getCurrentQuantity());
		productDto.setCategory(product.getCategory());
		productDto.setSalePrice(product.getSalePrice());
		productDto.setCostPrice(product.getCostPrice());
		productDto.setImage(product.getImage());
		productDto.setDeleted(product.is_deleted());
		productDto.setActivated(product.is_activated());
		return productDto;
	}

	

}

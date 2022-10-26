package com.trang.ecommerce_library.dto;

import com.trang.ecommerce_library.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductDto {
	private Long id;
	private String name;
	private String description;
	private double costPrice;
	private double salePrice;
	private int currentQuantity;
	
	private Category category;
	private String image;
	private boolean activated;
	private boolean deleted;
	
	
}

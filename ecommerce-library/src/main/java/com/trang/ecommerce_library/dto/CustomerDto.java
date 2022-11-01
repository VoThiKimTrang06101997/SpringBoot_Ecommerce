package com.trang.ecommerce_library.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerDto {
	@Size(min=3, max=15, message = "First Name should have 3-15 characters")
	private String firstName;
	
	@Size(min=3, max=15, message = "Last Name should have 3-15 characters")
	private String lastName;
	
	private String username;
	
	@Size(min=5, max=20, message = "Password must have 5-20 characters")
	private String password;
	
	private String repeatPassword;
}

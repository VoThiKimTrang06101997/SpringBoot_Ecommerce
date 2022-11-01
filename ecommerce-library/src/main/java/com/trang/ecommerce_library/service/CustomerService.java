package com.trang.ecommerce_library.service;

import com.trang.ecommerce_library.dto.CustomerDto;
import com.trang.ecommerce_library.model.Customer;

public interface CustomerService {
	CustomerDto save(CustomerDto customerDto);

	Customer findByUsername(String username);

	Customer saveInfor(Customer customer);
}

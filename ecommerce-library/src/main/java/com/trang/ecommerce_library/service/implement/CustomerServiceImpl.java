package com.trang.ecommerce_library.service.implement;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.trang.ecommerce_library.dto.CustomerDto;
import com.trang.ecommerce_library.model.Customer;
import com.trang.ecommerce_library.repository.CustomerRepository;
import com.trang.ecommerce_library.repository.RoleRepository;
import com.trang.ecommerce_library.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerDto save(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setUsername(customerDto.getUsername());
		customer.setPassword(customerDto.getPassword());
		customer.setRoles(Arrays.asList(roleRepository.findByName("CUSTOMER")));

		Customer customerSave = customerRepository.save(customer);
		return mapperDTO(customerSave);
	}

	private CustomerDto mapperDTO(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setPassword(customer.getPassword());
		customerDto.setUsername(customer.getUsername());
		return customerDto;
	}

	@Override
	public Customer findByUsername(String username) {
		return customerRepository.findByUsername(username);
	}

	@Override
	public Customer saveInfor(Customer customer) {
		Customer customer1 = customerRepository.findByUsername(customer.getUsername());
		customer1.setAddress(customer.getAddress());
		customer1.setCity(customer.getCity());
		customer1.setCountry(customer.getCountry());
		customer1.setPhoneNumber(customer.getPhoneNumber());
		return customerRepository.save(customer1);
	}

}

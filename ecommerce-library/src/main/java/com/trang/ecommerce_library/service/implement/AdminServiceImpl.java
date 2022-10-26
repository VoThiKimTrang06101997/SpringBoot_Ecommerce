package com.trang.ecommerce_library.service.implement;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trang.ecommerce_library.dto.AdminDto;
import com.trang.ecommerce_library.model.Admin;
import com.trang.ecommerce_library.repository.AdminRepository;
import com.trang.ecommerce_library.repository.RoleRepository;
import com.trang.ecommerce_library.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Admin findByUserName(String username) {
		return adminRepository.findByUsername(username);
	}

	@Override
	public Admin save(AdminDto adminDto) {
		Admin admin = new Admin();
		admin.setFirstName(adminDto.getFirstName());
		admin.setLastName(adminDto.getLastName());
		admin.setUsername(adminDto.getUsername());
		admin.setPassword(adminDto.getPassword());
		admin.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
		
		return adminRepository.save(admin);
	}

}

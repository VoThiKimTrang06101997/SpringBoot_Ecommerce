package com.trang.ecommerce_library.service;

import com.trang.ecommerce_library.dto.AdminDto;
import com.trang.ecommerce_library.model.Admin;

public interface AdminService {
	Admin findByUserName(String username);
	Admin save(AdminDto adminDto);
}

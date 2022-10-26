package com.trang.ecommerce_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.trang.ecommerce_library.*", "com.trang.ecommerce_admin.*"})
@EnableJpaRepositories(value = "com.trang.ecommerce_library.repository")
@EntityScan(value = "com.trang.ecommerce_library.model")
public class EcommerceAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAdminApplication.class, args);
	}

}

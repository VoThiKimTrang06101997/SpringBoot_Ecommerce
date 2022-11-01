package com.trang.ecommerce_library.service;

import com.trang.ecommerce_library.model.ShoppingCart;

public interface OrderService {
	void saveOrder(ShoppingCart cart);
	
	void acceptOrder(Long id);
	
	void cancelOrder(Long id);
}

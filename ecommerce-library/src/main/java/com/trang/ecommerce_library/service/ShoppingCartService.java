package com.trang.ecommerce_library.service;

import com.trang.ecommerce_library.model.Customer;
import com.trang.ecommerce_library.model.Product;
import com.trang.ecommerce_library.model.ShoppingCart;

public interface ShoppingCartService {
	ShoppingCart addItemToCart(Product product, int quantity, Customer customer);
	
	ShoppingCart updateItemInCart(Product product, int quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product, Customer customer);
}

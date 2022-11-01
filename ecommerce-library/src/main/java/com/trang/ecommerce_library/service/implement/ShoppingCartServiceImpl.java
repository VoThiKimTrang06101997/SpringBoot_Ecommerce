package com.trang.ecommerce_library.service.implement;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trang.ecommerce_library.model.CartItem;
import com.trang.ecommerce_library.model.Customer;
import com.trang.ecommerce_library.model.Product;
import com.trang.ecommerce_library.model.ShoppingCart;
import com.trang.ecommerce_library.repository.CartItemRepository;
import com.trang.ecommerce_library.repository.ShoppingCartRepository;
import com.trang.ecommerce_library.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	private CartItemRepository itemRepository;

	@Autowired
	private ShoppingCartRepository cartRepository;

	@Override
	public ShoppingCart addItemToCart(Product product, int quantity, Customer customer) {
		ShoppingCart cart = customer.getShoppingCart();

		if (cart == null) {
			cart = new ShoppingCart();
		}
		Set<CartItem> cartItems = cart.getCartItem();
		CartItem cartItem = findCartItem(cartItems, product.getId());
		if (cartItems == null) {
			cartItems = new HashSet<>();
			if (cartItem == null) {
				cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setTotalPrice(quantity * product.getCostPrice());
				cartItem.setQuantity(quantity);
				cartItem.setShoppingCart(cart);
				cartItems.add(cartItem);
				itemRepository.save(cartItem);
			}
		} else {
			if (cartItem == null) {
				cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setTotalPrice(quantity * product.getCostPrice());
				cartItem.setQuantity(quantity);
				cartItem.setShoppingCart(cart);
				cartItems.add(cartItem);
				itemRepository.save(cartItem);
			} else {
				cartItem.setQuantity(cartItem.getQuantity() + quantity);
				cartItem.setTotalPrice(cartItem.getTotalPrice() + (quantity * product.getCostPrice()));
				itemRepository.save(cartItem);
			}
		}
		cart.setCartItem(cartItems);

		int totalItems = totalItems(cart.getCartItem());
		double totalPrice = totalPrice(cart.getCartItem());

		cart.setTotalPrices(totalPrice);
		cart.setTotalItems(totalItems);
		cart.setCustomer(customer);

		return cartRepository.save(cart);
	}

	private CartItem findCartItem(Set<CartItem> cartItems, Long productId) {
		if (cartItems == null) {
			return null;
		}
		CartItem cartItem = null;

		for (CartItem item : cartItems) {
			if (item.getProduct().getId() == productId) {
				cartItem = item;
			}
		}
		return cartItem;
	}

	private int totalItems(Set<CartItem> cartItems) {
		int totalItems = 0;
		for (CartItem item : cartItems) {
			totalItems += item.getQuantity();
		}
		return totalItems;
	}

	private double totalPrice(Set<CartItem> cartItems) {
		double totalPrice = 0.0;

		for (CartItem item : cartItems) {
			totalPrice += item.getTotalPrice();
		}

		return totalPrice;
	}

	@Override
	public ShoppingCart updateItemInCart(Product product, int quantity, Customer customer) {
		ShoppingCart cart = customer.getShoppingCart();

		Set<CartItem> cartItems = cart.getCartItem();

		CartItem item = findCartItem(cartItems, product.getId());

		item.setQuantity(quantity);
		item.setTotalPrice(quantity * product.getCostPrice());

		itemRepository.save(item);

		// Update TotalItem and TotalPrice in cartItems
		int totalItems = totalItems(cartItems);
		double totalPrice = totalPrice(cartItems);

		cart.setTotalItems(totalItems);
		cart.setTotalPrices(totalPrice);

		return cartRepository.save(cart);

	}

	@Override
	public ShoppingCart deleteItemFromCart(Product product, Customer customer) {
		ShoppingCart cart = customer.getShoppingCart();

		Set<CartItem> cartItems = cart.getCartItem();

		CartItem item = findCartItem(cartItems, product.getId());

		cartItems.remove(item);

		itemRepository.delete(item);

		double totalPrice = totalPrice(cartItems);
		int totalItems = totalItems(cartItems);

		cart.setCartItem(cartItems);
		cart.setTotalItems(totalItems);
		cart.setTotalPrices(totalPrice);

		return cartRepository.save(cart);
	}

}

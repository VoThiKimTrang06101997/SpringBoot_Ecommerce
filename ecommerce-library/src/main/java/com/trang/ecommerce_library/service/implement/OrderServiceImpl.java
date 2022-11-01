package com.trang.ecommerce_library.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trang.ecommerce_library.model.CartItem;
import com.trang.ecommerce_library.model.Order;
import com.trang.ecommerce_library.model.OrderDetail;
import com.trang.ecommerce_library.model.ShoppingCart;
import com.trang.ecommerce_library.repository.CartItemRepository;
import com.trang.ecommerce_library.repository.OrderDetailRepository;
import com.trang.ecommerce_library.repository.OrderRespository;
import com.trang.ecommerce_library.repository.ShoppingCartRepository;
import com.trang.ecommerce_library.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderRespository orderRespository;
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public void saveOrder(ShoppingCart cart) {
		Order order = new Order();
		order.setOrderStatus("PENDING");
		order.setOrderDate(new Date());
		order.setCustomer(cart.getCustomer());
		order.setTotalPrice(cart.getTotalPrices());
		
		List<OrderDetail> orderDetailList = new ArrayList<>();
		
		for (CartItem cartItem : cart.getCartItem()) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setQuantity(cartItem.getQuantity());
			orderDetail.setProduct(cartItem.getProduct());
			orderDetail.setUnitPrice(cartItem.getProduct().getCostPrice());
			orderDetailRepository.save(orderDetail);
			orderDetailList.add(orderDetail);
			cartItemRepository.delete(cartItem);
		}
		
		order.setOrderDetailList(orderDetailList);
		cart.setCartItem(new HashSet<>());
		cart.setTotalItems(0);
		cart.setTotalPrices(0);
		shoppingCartRepository.save(cart);
		orderRespository.save(order);
	}

	@Override
	public void acceptOrder(Long id) {
		Order order = orderRespository.getById(id);
		order.setDeliveryDate(new Date());
		order.setOrderStatus("SHIPPING");
		orderRespository.save(order);
		
	}

	@Override
	public void cancelOrder(Long id) {
		orderRespository.deleteById(id);	
	}

}

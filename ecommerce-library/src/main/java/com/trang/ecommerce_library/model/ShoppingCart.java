package com.trang.ecommerce_library.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="shopping_cart")
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="shopping_cart_id")
	private Long id;
	
	private int totalItems;
	
	private double totalPrices;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id", referencedColumnName = "customer_id")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart")
	private Set<CartItem> cartItem;
}

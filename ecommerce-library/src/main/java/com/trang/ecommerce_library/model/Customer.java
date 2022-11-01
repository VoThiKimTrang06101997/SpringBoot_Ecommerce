package com.trang.ecommerce_library.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="customers", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "image", "phone_number"}))
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long id;
	
	@Size(min=3, max=15, message = "First Name should have 3-15 characters")
	private String firstName;
	
	@Size(min=3, max=15, message = "Last Name should have 3-15 characters")
	private String lastName;
	private String username;
	private String country;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	private String address;
	private String password;
	
	@Lob
	@Column(name="image", columnDefinition = "MEDIUMBLOB")
	private String image;
	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name="city_id", referencedColumnName = "city_id")
//	private City city;
	
	@Column(name = "city")
	private String city;
	
	@OneToOne(mappedBy = "customer")
	private ShoppingCart shoppingCart;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name= "customers_roles" , joinColumns = @JoinColumn(name="customer_id", referencedColumnName = "customer_id"),
	inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "role_id"))
	private Collection<Role> roles;
	
	
}

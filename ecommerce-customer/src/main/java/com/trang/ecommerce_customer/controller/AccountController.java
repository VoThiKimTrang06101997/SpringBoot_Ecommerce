package com.trang.ecommerce_customer.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trang.ecommerce_library.model.Customer;
import com.trang.ecommerce_library.service.CustomerService;

@Controller
public class AccountController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("/account")
	public String accountHome(Model model, Principal principal) {
		if (principal == null) {
			return "redirect:/login";
		}
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		model.addAttribute("customer", customer);

		return "account";
	}

	@RequestMapping(value = "/update-infor", method = { RequestMethod.GET, RequestMethod.PUT })
	public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model,
			RedirectAttributes redirectAttributes, Principal principal) {
		if (principal == null) {
			return "redirect:/login";
		}
		Customer customerSaved = customerService.saveInfor(customer);

		redirectAttributes.addFlashAttribute("customer", customerSaved);

		return "redirect:/account";
	}

}
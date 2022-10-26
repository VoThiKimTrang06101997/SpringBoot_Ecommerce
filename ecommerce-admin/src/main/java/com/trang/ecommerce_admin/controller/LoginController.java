package com.trang.ecommerce_admin.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.trang.ecommerce_library.dto.AdminDto;
import com.trang.ecommerce_library.model.Admin;
import com.trang.ecommerce_library.service.implement.AdminServiceImpl;

@Controller
public class LoginController {
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("title", "login");
		return "login";
	}
	
	@RequestMapping("/index")
	public String home(Model model) {
		model.addAttribute("title", "Home Page");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "redirect:/login";
		}
		return "index";
	}
	
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("title", "Register");
		model.addAttribute("adminDto", new AdminDto());
		return "register";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPassword(Model model) {
		model.addAttribute("title", "Forgot Password");
		return "forgot-password";
	}
	
	@PostMapping("/register-new")
	   public String addNewAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto,
	                             BindingResult result,
	                             Model model){

	        try {
	            if(result.hasErrors()){
	                model.addAttribute("adminDto", adminDto);
	                result.toString();
	                return "register";
	            }
	            String username = adminDto.getUsername();
	            Admin admin = adminServiceImpl.findByUserName(username);
	            if(admin != null){
	                model.addAttribute("adminDto", adminDto);
	                System.out.println("Admin not null");
	                model.addAttribute("emailError", "Your email has been registered!");
	                return "register";
	            }
	            if(adminDto.getPassword().equals(adminDto.getRepeatPassword())){
	                adminDto.setPassword(bCryptPasswordEncoder.encode(adminDto.getPassword()));
	                adminServiceImpl.save(adminDto);
	                System.out.println("success");
	                model.addAttribute("success", "Register successfully!");
	                model.addAttribute("adminDto", adminDto);
	            }else{
	                model.addAttribute("adminDto", adminDto);
	                model.addAttribute("passwordError", "Your password maybe wrong! Check again!");
	                System.out.println("Password is not the same");
	                return "register";
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	            model.addAttribute("errors", "The server has been wrong!");
	        }
	        return "register";

	   }
}

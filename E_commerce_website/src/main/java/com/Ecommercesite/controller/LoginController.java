package com.Ecommercesite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Ecommercesite.global.GlobalData;
import com.Ecommercesite.model.Role;
import com.Ecommercesite.model.User;
import com.Ecommercesite.repository.RoleRepository;
import com.Ecommercesite.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login()
	{
		GlobalData.cart.clear();
		return "login";
	}
	@PostMapping("/login")
	public String logininto()
	{
		return "/";
	}
	
	@GetMapping("/register")
	public String resisterGet()
	{
		return "resister";
	}
	
	@PostMapping("/register")
	public String resisterPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException 
	{
		String password = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> roles= new ArrayList<>();
		roles.add(roleRepository.findById(2).get());
		user.setRoles(roles);
		userRepository.save(user);
		request.login(user.getEmail(), password);
		return "redirect:/";
		
	}
}

package com.daniel.eMusicStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.daniel.eMusicStore.entities.AdminModel;
import com.daniel.eMusicStore.repos.AdminRepository;
import com.daniel.eMusicStore.services.ProductService;

@Controller
public class AdminController {

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	ProductService productService;

	@RequestMapping("/showAdminLogin")
	public String adminLogin() {
		return "admin/adminLogin";
	}

	
	
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String adminLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap model) {
		
		AdminModel admin = adminRepository.findByEmailAndPassword(email,password);
		if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
			return "admin/index";
		} else {
			model.addAttribute("msg", "Invalid username or password. Please try again.");
		}
		return "admin/adminLogin";
	}
	
	@RequestMapping(value="/dashboard", method = {RequestMethod.GET})
	public String showDashBoard(Model model) {
		
		return "admin/index";
	}
	
	
}

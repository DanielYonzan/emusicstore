package com.daniel.eMusicStore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {


	@RequestMapping("/showUserReg")
	public String showCreateProduct() {
		return "frontend/registerUser";
	}

}

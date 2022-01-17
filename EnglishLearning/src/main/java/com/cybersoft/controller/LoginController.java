package com.cybersoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("")
	public String login() {
		
		return "login/login";
	}
	
	@GetMapping("/403")
	public String error403() {
		
		return "403/403";
	}
	@GetMapping("/admin")
	public String loginAdmin() {
		
		return "adminlogin/adminlogin";
	}
}

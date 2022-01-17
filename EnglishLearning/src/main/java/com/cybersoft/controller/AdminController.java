package com.cybersoft.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cybersoft.dto.UserDto;
import com.cybersoft.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private UserService userService;
	
	public AdminController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "adminlogin/adminlogin";
	}
	
	@GetMapping("")
	public String index(Model model) {
		List<UserDto> dtos = userService.getAllUser();
		model.addAttribute("dtos",dtos);
		
		return "adminlogin/welcome";
	}
}

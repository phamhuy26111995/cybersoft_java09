package com.cybersoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cybersoft.dto.UserDto;
import com.cybersoft.service.UserService;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	private UserService userService;
	
	public SignUpController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("")
	public String signUp(@RequestParam("username") String username, 
						@RequestParam("password") String password,
						@RequestParam("name") String name) {
		UserDto dto = new UserDto();
		dto.setName(name);
		dto.setUsername(username);
		dto.setPassword(password);
		
		userService.registerUser(dto);
		
		return "redirect:/login/login";
	}
	
	@GetMapping("")
	public String register() {
		
		return "login/signup";
	}
	
	
}

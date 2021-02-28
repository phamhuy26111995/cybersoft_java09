package com.cybersoft.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.dto.UserDto;
import com.cybersoft.service.UserService;

@RestController
@RequestMapping("api/register")
public class UserRegisterController {
	private UserService userService;
	public UserRegisterController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("")
	public Object post(@RequestBody UserDto dto) {
		try {
			userService.insertUserRegister(dto);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}

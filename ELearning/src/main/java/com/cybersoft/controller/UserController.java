package com.cybersoft.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.common.CurrentUser;
import com.cybersoft.common.IndentifyUser;
import com.cybersoft.dto.UserDto;
import com.cybersoft.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	private UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//Get user theo id
	@GetMapping("")
	public Object getById() {
		try {
			UserDto dto = userService.getById(IndentifyUser.getIdPrincipal());
			return new ResponseEntity<Object>(dto, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	//Lấy User Student hiện tại đang tương tác với hệ thống
	@GetMapping("current")
	public Object getCurrentUser() {
		try {
			UserDto dto = CurrentUser.getCurrentUser();



			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
}

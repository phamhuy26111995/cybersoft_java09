package com.cybersoft.service.impl;

import org.springframework.stereotype.Service;

import com.cybersoft.dto.UserDto;
import com.cybersoft.entity.User;
import com.cybersoft.repository.UserRepository;
import com.cybersoft.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void registerUser(UserDto dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		user.setUsername(dto.getUsername());
		user.setRole_id(2);
		
		userRepository.registerUser(user);
		
	}
	
	
}

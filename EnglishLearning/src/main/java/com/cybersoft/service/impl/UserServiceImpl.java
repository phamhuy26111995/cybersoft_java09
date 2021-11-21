package com.cybersoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cybersoft.dto.RoleDto;
import com.cybersoft.dto.UserDto;
import com.cybersoft.entity.Role;
import com.cybersoft.entity.User;
import com.cybersoft.repository.RoleRepository;
import com.cybersoft.repository.UserRepository;
import com.cybersoft.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	public void registerUser(UserDto dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		user.setUsername(dto.getUsername());
		user.setRole_id(2);
		
		userRepository.registerUser(user);
		
	}

	public List<UserDto> getAllUser() {
		List<User> entities = userRepository.listAllUser();
		List<UserDto> dtos = new ArrayList<UserDto>();
		Role roleAdmin = roleRepository.findById(1);
		Role roleUser = roleRepository.findById(2);
		
		for(User entity : entities) {
			UserDto dto = new UserDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setUsername(entity.getUsername());
			if(entity.getRole_id() == 1) {
				dto.setRoleName(roleAdmin.getName());
			}else {
				dto.setRoleName(roleUser.getName());
			}
			
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	
}

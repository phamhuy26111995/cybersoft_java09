package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.UserDto;

public interface UserService {
	void registerUser(UserDto dto);
	List<UserDto> getAllUser();
}

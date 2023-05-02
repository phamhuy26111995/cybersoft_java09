package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.UserDto;

public interface UserService {
	void insert(UserDto dto);
	List<UserDto> getAll();
	UserDto getById(Long id);
	void update(UserDto dto);
	void delete(Long id);
	List<UserDto> getStudentOfCourse(Long id);
	UserDto getTheLastUser();
	UserDto getByEmail(String email);
	void insertUserRegister(UserDto dto);
	void updateProfile(UserDto dto);
}

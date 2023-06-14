package com.cybersoft.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.cybersoft.dto.UserDto;
import com.cybersoft.dto.UserFilterDto;
import com.cybersoft.dto.UserSearchDto;
import com.cybersoft.model.users.UsersModel;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
	long save(UserDto dto, MultipartFile file);
	List<UserDto> getAll();
	UserDto getById(Long id);
	void update(UserDto dto, MultipartFile file);
	void delete(Long id);
	List<UserDto> getStudentOfCourse(Long id);
	UserDto getTheLastUser();
	UserDto getByEmail(String email);
	void insertUserRegister(UserDto dto);
	void registerAdminUser(UserDto dto);
	void updateProfile(UserDto dto);
	UsersModel getUsersByRole(UserFilterDto filterDto);
	UsersModel search(UserSearchDto searchDto) throws ExecutionException, InterruptedException;

}

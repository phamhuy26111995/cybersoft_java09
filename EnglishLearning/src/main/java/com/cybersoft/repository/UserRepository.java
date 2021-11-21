package com.cybersoft.repository;

import java.util.List;

import com.cybersoft.entity.User;

public interface UserRepository {
	User findById(int userId);
	User findByUserName(String username);
	void registerUser(User user);
	List<User> listAllUser();
}

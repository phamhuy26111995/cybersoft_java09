package com.cybersoft.repository;

import com.cybersoft.entity.User;

public interface UserRepository {
	User findById(int userId);
	User findByUserName(String username);
	void registerUser(User user);
}

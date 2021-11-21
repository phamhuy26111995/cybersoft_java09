package com.cybersoft.repository;

import java.util.List;

import com.cybersoft.entity.Role;
import com.cybersoft.entity.User;

public interface RoleRepository {
	Role findById(int id);
	List<Role> listAllroles();

	
}

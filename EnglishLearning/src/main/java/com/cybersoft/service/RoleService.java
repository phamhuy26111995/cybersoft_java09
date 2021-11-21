package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.RoleDto;


public interface RoleService {
	RoleDto findById(int id);
	List<RoleDto> getAllRoles();
}

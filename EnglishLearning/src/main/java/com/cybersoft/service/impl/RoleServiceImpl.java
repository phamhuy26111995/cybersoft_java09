package com.cybersoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cybersoft.dto.RoleDto;
import com.cybersoft.entity.Role;
import com.cybersoft.repository.RoleRepository;
import com.cybersoft.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public RoleDto findById(int id) {
		Role entity = roleRepository.findById(id);
		RoleDto dto = new RoleDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	public List<RoleDto> getAllRoles() {
		List<Role> entities = roleRepository.listAllroles();
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		for(Role entity : entities) {
			RoleDto dto = new RoleDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			
			dtos.add(dto);
		}
		
		return dtos;
	}

}

package com.cybersoft.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.cybersoft.consts.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.dto.RoleDto;
import com.cybersoft.service.RoleService;


@RestController
@RequestMapping(Consts.PREFIX_ADMIN + "/roles")
public class AdminRoleController {
	@Autowired
	private RoleService roleService;

	//Lấy ra toàn bộ role
	@GetMapping("/get-all")
	public Object get() {
		try {
			List<RoleDto> entities = roleService.getAll();
			return new ResponseEntity<Object>(entities, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	//Lấy role theo id
	@GetMapping("{id}")
	public Object get(@PathVariable int id) {
		try {
			RoleDto entity = roleService.getById(id);
			return new ResponseEntity<Object>(entity, HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
}

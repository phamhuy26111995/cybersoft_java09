package com.cybersoft.admin.controller;

import com.cybersoft.common.IndentifyUser;
import com.cybersoft.consts.Consts;
import com.cybersoft.dto.UserDetailFilterDto;
import com.cybersoft.dto.UserFilterDto;
import com.cybersoft.dto.UserLoginSuccessDto;
import com.cybersoft.model.users.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cybersoft.dto.UserDto;
import com.cybersoft.service.CourseService;
import com.cybersoft.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(Consts.PREFIX_ADMIN + "/users")
public class AdminUserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;

	@GetMapping("/get-by-role")
	public Object getUsers(@RequestBody UserFilterDto filter) {
		UsersModel result = userService.getUsersByRole(filter);
		return new ResponseEntity<UsersModel>(result, HttpStatus.OK);
	}

//Get User theo id
	@GetMapping("/detail")
	public Object getDetail(@RequestBody UserDetailFilterDto dto) {
		try {
			UserDto user = userService.getById(dto.getId());
			return new ResponseEntity<Object>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/get-current-user")
	public Object getCurrentUser() {
		try {
			UserDto userDto = userService.getById(IndentifyUser.getIdPrincipal());

			if(userDto == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			UserLoginSuccessDto successDto = new UserLoginSuccessDto();
			successDto.setEmail(userDto.getEmail());
			successDto.setAvatar(userDto.getAvatar());
			successDto.setFullname(userDto.getFullname());

			return new ResponseEntity<Object>(successDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


//Thêm mới user
	@PostMapping("/save")
	public Object save(@RequestPart UserDto dto, @RequestPart MultipartFile file) {
		try {
			userService.save(dto, file);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}


//Edit User
	@PutMapping("/update")
	public Object update(@RequestPart UserDto dto, @RequestPart MultipartFile file) {
		try {
			if(userService.getById(dto.getId()) == null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}

			userService.update(dto, file);
			return new ResponseEntity<Object>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
//Xóa User
	@DeleteMapping("/delete")
	public Object delete(@RequestBody UserDto dto) {
		try {

			userService.delete(dto.getId());
			return new ResponseEntity<Object>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}


}

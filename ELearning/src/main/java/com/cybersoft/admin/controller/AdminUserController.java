package com.cybersoft.admin.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.common.CurrentUser;
import com.cybersoft.common.IndentifyEmail;
import com.cybersoft.common.IndentifyRole;
import com.cybersoft.dto.CourseDto;
import com.cybersoft.dto.UserDto;
import com.cybersoft.entity.User;
import com.cybersoft.repository.UserRepository;
import com.cybersoft.service.CourseService;
import com.cybersoft.service.UserService;

@RestController
@RequestMapping("api/admin/user")

public class AdminUserController {
	private UserService userService;
	private CourseService courseService;


	public AdminUserController(UserService userService,CourseService courseService) {
		this.userService = userService;
		this.courseService = courseService;
	}



	@GetMapping("")
	public Object get() {
		try {
			List<UserDto> dtos ;

			if(IndentifyRole.getRolePrincipal().contains("ROLE_ADMIN")) { 
				dtos = userService.getAll(); 

			}
			else {
				dtos = new ArrayList<UserDto>();
				List<CourseDto> courseDtos = courseService.getCourseByUser(IndentifyEmail.getEmailPrincipal());
				for(CourseDto dto : courseDtos) {
					List<UserDto> students = userService.getStudentOfCourse(dto.getId());
					dtos.addAll(students);
				}


			}



			return new ResponseEntity<Object>(dtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}




	@GetMapping("{id}")
	public Object getById(@PathVariable int id) {
		try {
			UserDto user = userService.getById(id);


			return new ResponseEntity<Object>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}


	@GetMapping("current")
	public Object getByEmail() {
		try {
			UserDto dto = CurrentUser.getCurrentUser();



			return new ResponseEntity<Object>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}



	@PostMapping("")
	public Object post(@RequestBody UserDto dto) {
		try {
			userService.insert(dto);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}


	@PutMapping("{id}")
	public Object put(@PathVariable int id,@RequestBody UserDto dto) {
		try {
			if(userService.getById(id)==null) {

				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			else {


				userService.update(dto);
				System.out.println(dto.getPassword());
				return new ResponseEntity<Object>(HttpStatus.OK);
			}

		} catch (Exception e) {
			System.out.println("Lỗi ở đây");
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {

			userService.delete(id);
			return new ResponseEntity<Object>(HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("profile")
	public Object put(@RequestBody UserDto dto) {
		try {
			userService.updateProfile(dto);

			return new ResponseEntity<Object>(HttpStatus.OK);


		} catch (Exception e) {
			System.out.println("Lỗi update profile");
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}




}

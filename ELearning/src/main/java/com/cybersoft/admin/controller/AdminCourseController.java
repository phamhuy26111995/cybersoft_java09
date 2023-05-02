package com.cybersoft.admin.controller;

import java.util.List;

import com.cybersoft.consts.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.common.IndentifyEmail;
import com.cybersoft.common.IndentifyRole;
import com.cybersoft.dto.CourseDto;
import com.cybersoft.service.CourseService;

@RestController
@RequestMapping(Consts.PREFIX_ADMIN + "/course")
public class AdminCourseController {
	@Autowired
	private CourseService courseService;

	
	//Trả về đối tượng CourseDto
	@GetMapping("")
	public Object get() {
		try {
			List<CourseDto> dtos ;
			//Nếu user hiện tại có role là Admin thì lấy toàn bộ , còn nếu là Teacher thì sẽ lấy các khóa học thuộc về mình
			if(IndentifyRole.getRolePrincipal().contains("ROLE_ADMIN")) {
				 dtos = courseService.getAll();
			}
			else {
				dtos = courseService.getCourseByUser(IndentifyEmail.getEmailPrincipal());
			}

			return new ResponseEntity<Object>(dtos, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//Trả về khóa học cuối cùng nằm trong danh sách
	@GetMapping("last")
	public Object getLastCourse() {
		try {
			CourseDto dto = courseService.getTheLastCourse();

			return new ResponseEntity<Object>(dto, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//Thêm khóa học
	@PostMapping("/save")
	public Object save(@RequestBody CourseDto dto) {

		try {
			courseService.save(dto);;
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * @PutMapping("UserCourse") public Object saveUserCourse(@RequestBody CourseDto
	 * dto) {
	 * 
	 * try { courseService.updateUserCourse(dto);; return new
	 * ResponseEntity<Object>(HttpStatus.OK); } catch (Exception e) {
	 * e.printStackTrace(); } return new
	 * ResponseEntity<Object>(HttpStatus.BAD_REQUEST); }
	 */
	
	
	//Edit khóa học
	@PutMapping("{id}")
	public Object put(@PathVariable Long id,@RequestBody CourseDto dto) {
		try {
			if(courseService.getById(id)==null) {
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			else {

				courseService.update(dto);
				return new ResponseEntity<Object>(HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//Xóa khóa học
	@DeleteMapping("{id}")
	public Object delete(@PathVariable Long id) {
		try {
			
			courseService.delete(id);
			return new ResponseEntity<Object>(HttpStatus.OK);


		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}

package com.cybersoft.admin.controller;

import com.cybersoft.consts.Consts;
import com.cybersoft.dto.CourseContentDto;
import com.cybersoft.dto.SearchCourseDto;
import com.cybersoft.model.courses.CourseSearchModel;
import com.cybersoft.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cybersoft.dto.CourseDto;
import com.cybersoft.service.CourseService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(Consts.PREFIX_ADMIN + "/courses")
public class AdminCourseController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseContentService courseContentService;


	//Trả về khóa học cuối cùng nằm trong danh sách
	@GetMapping("/find-by-id")
	public Object getLastCourse(@RequestBody CourseDto dto) {
		try {
			CourseDto result = courseService.findById(dto.getId());

			return new ResponseEntity<Object>(result, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/search")
	public Object searchCourse(@RequestBody SearchCourseDto dto) {
		try {

			CourseSearchModel result = courseService.getCourseByUser(dto);
			return new ResponseEntity<Object>(result , HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}
	
	//Thêm khóa học
	@PostMapping("/save")
	public Object save(@RequestPart CourseDto dto, @RequestPart MultipartFile file) {
		try {
			courseService.save(dto, file);;
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/save-content")
	public Object saveContent(@RequestBody List<CourseContentDto> dto) {
		try {
			courseContentService.save(dto);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/edit")
	public Object edit(@RequestPart CourseDto dto, MultipartFile file) {
		try {
			courseService.update(dto, file);;
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	//Xóa khóa học
	@DeleteMapping("/delete")
	public Object delete(@RequestBody CourseDto dto) {
		try {
			courseService.delete(dto.getId());
			return new ResponseEntity<Object>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}

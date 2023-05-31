package com.cybersoft.admin.controller;

import com.cybersoft.common.AppUtils;
import com.cybersoft.common.BaseDTO;
import com.cybersoft.consts.Consts;
import com.cybersoft.dto.*;
import com.cybersoft.entity.CourseEntity;
import com.cybersoft.model.courses.CourseSearchModel;
import com.cybersoft.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	@PostMapping("/detail")
	public Object getLastCourse(@RequestBody CourseDto dto) {
		try {
			CourseDto result = courseService.findById(dto.getId());

			return new ResponseEntity<Object>(result, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/search")
	public Object searchCourse(@RequestBody SearchCourseDto dto) {
		try {

//			CourseSearchModel result = courseService.getCourseByUser(dto);
			CourseSearchModel result = courseService.search(dto);
			return new ResponseEntity<Object>(result , HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/search-all")
	public Object getAll(@RequestBody BaseDTO dto) {
		try {

			CourseSearchModel result = courseService.getAll(dto);
			AppUtils.createOrderNumber(result.getContent(), dto.getPageIndex(), dto.getPageSize());
			return new ResponseEntity<Object>(result , HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	
	//Thêm khóa học
	@PostMapping("/save")
	public Object save(@RequestPart CourseDto dto, @RequestPart MultipartFile file) {
		try {
			CourseEntity savedEntity = courseService.save(dto, file);;
			return new ResponseEntity<Object>(savedEntity.getId(),HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}



	@PostMapping("/save-content")
	public Object saveContent(@RequestBody CourseContentBody body) {
		try {
			courseContentService.save(body);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/edit-content")
	public Object updateContent(@RequestBody CourseContentBody body) {
		try {
			courseContentService.save(body);
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/edit")
	public Object edit(@RequestPart CourseDto dto, MultipartFile file) {
		try {
			CourseEntity entity =  courseService.update(dto, file);;
			return new ResponseEntity<Object>(entity.getId(),HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/edit-content")
	public Object editContent(@RequestBody CourseContentUpdateWraper wraperDto) {
		try {
			courseContentService.save(wraperDto.getCourseContentBody());
			courseContentService.delete(wraperDto.getCourseContentDeleteDto());
			return new ResponseEntity<Object>(HttpStatus.OK);
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

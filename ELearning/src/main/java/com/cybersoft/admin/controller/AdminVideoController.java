package com.cybersoft.admin.controller;

import com.cybersoft.consts.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.dto.VideoDto;
import com.cybersoft.service.CourseService;
import com.cybersoft.service.VideoService;

@RestController
@RequestMapping(Consts.PREFIX_ADMIN + "/video/")
public class AdminVideoController {
	@Autowired
	private VideoService videoService;
	@Autowired
	private CourseService courseService;



	//Lấy ra danh sách video


	//Thêm mới một video
	@PostMapping("")
	public Object save(@RequestBody VideoDto dto) {

		try {
			videoService.save(dto);;
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}

	//Edit một video
	@PutMapping("{id}")
	public Object put(@PathVariable int id,@RequestBody VideoDto dto) {
		try {
			videoService.edit(dto);

			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}

	//Xóa một video
	@DeleteMapping("{id}")
	public Object delete(@PathVariable int id) {
		try {
			System.out.println(id);
			videoService.delete(id);;
			return new ResponseEntity<Object>(HttpStatus.OK);


		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
}

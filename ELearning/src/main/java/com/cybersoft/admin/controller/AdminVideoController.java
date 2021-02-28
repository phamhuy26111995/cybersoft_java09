package com.cybersoft.admin.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.cybersoft.dto.UserDto;
import com.cybersoft.dto.VideoDto;
import com.cybersoft.service.CourseService;
import com.cybersoft.service.VideoService;

@RestController
@RequestMapping("api/admin/video")
public class AdminVideoController {
	private VideoService videoService;
	private CourseService courseService;

	public AdminVideoController(VideoService videoService,CourseService courseService) {
		this.videoService = videoService;
		this.courseService = courseService;
	}

	@GetMapping("")
	public Object get() {
		try {
			List<VideoDto> dtos ;
			if(IndentifyRole.getRolePrincipal().contains("ROLE_ADMIN")) {
				dtos = videoService.getAll();
			}

			else {
				dtos = new ArrayList<VideoDto>();
				List<CourseDto> courseDtos = courseService.getCourseByUser(IndentifyEmail.getEmailPrincipal());
				for(CourseDto dto : courseDtos) {
					List<VideoDto> videos = videoService.getByCourse(dto.getId());
					dtos.addAll(videos);
				}


			}
			return new ResponseEntity<Object>(dtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}

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

	@PutMapping("{id}")
	public Object put(@PathVariable int id,@RequestBody VideoDto dto) {
		try {
			videoService.edit(dto);

			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}

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

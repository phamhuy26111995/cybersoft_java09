package com.cybersoft.service;

import java.util.List;
import java.util.Optional;

import com.cybersoft.dto.CourseDto;
import com.cybersoft.entity.CourseEntity;
import com.cybersoft.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
	void save(CourseDto dto, MultipartFile file);
	List<CourseDto> getAll();
	CourseDto getById(Long id);
	void update(CourseDto dto, MultipartFile file);
	void delete(Long id);
	List<CourseDto> getCourseByUser(String email);
	List<CourseDto> getCourseByCategory(int id);
	CourseDto getTheLastCourse();

	CourseDto findById(Long id);
}

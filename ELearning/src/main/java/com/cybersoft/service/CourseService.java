package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.CourseDto;

public interface CourseService {
	void save(CourseDto dto);
	List<CourseDto> getAll();
	CourseDto getById(Long id);
	void update(CourseDto dto);
	void delete(Long id);
	List<CourseDto> getCourseByUser(String email);
	List<CourseDto> getCourseByCategory(int id);
	CourseDto getTheLastCourse();

}

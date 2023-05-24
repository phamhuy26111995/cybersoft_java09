package com.cybersoft.service;

import java.util.List;
import java.util.Optional;

import com.cybersoft.common.BaseDTO;
import com.cybersoft.dto.CourseDto;
import com.cybersoft.dto.SearchCourseDto;
import com.cybersoft.entity.CourseEntity;
import com.cybersoft.entity.User;
import com.cybersoft.model.courses.CourseSearchModel;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {
	void save(CourseDto dto, MultipartFile file);
	CourseSearchModel getAll(BaseDTO baseDTO);

	CourseSearchModel getCourseByUser(SearchCourseDto dto);

	CourseSearchModel search(SearchCourseDto dto);

	CourseDto getById(Long id);
	void update(CourseDto dto, MultipartFile file);
	void delete(Long id);
	List<CourseDto> getCourseByUserEmail(String email);
	List<CourseDto> getCourseByCategory(int id);
	CourseDto getTheLastCourse();

	CourseDto findById(Long id);
}

package com.cybersoft.service;

import com.cybersoft.dto.CourseContentDto;
import com.cybersoft.dto.CourseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseContentService {
	List<CourseContentDto> getByCourseId(Long courseId);
	Long save(List<CourseContentDto> dtos);
}

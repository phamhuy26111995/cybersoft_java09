package com.cybersoft.repository;

import com.cybersoft.dto.CourseDto;
import com.cybersoft.dto.SearchCourseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseRepositoryCustom {
    List<CourseDto> findCourseByCondition(SearchCourseDto searchCourseDto);
}

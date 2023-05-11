package com.cybersoft.repository;

import com.cybersoft.dto.CourseDto;
import com.cybersoft.entity.CourseContentEntity;
import com.cybersoft.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseContentRepository extends JpaRepository<CourseContentEntity,Long> {
	List<CourseContentEntity> findByCourseId(Long courseId);
}

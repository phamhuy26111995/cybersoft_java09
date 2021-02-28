package com.cybersoft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.entity.Course;
import com.cybersoft.entity.User;
import com.cybersoft.dto.CourseDto;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
	
	@Query("SELECT new com.cybersoft.dto.CourseDto(c.id, c.title, c.image, c.price) FROM UserCourse uc JOIN Course c ON uc.course.id = c.id JOIN User u ON uc.user.id = u.id WHERE u.email = ?1")
	public List<CourseDto> getCourseByUser(String email);
	
	@Query("SELECT new com.cybersoft.dto.CourseDto(c.id, c.title, c.image, c.price) FROM Course c JOIN Category ca ON c.category.id = ca.id WHERE ca.id = ?1 ")
	public List<CourseDto> getCourseByCategory(int id);
	
	public Course findTop1ByOrderByIdDesc();
}

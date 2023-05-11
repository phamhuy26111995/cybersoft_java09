package com.cybersoft.dto;

import com.cybersoft.entity.CourseEntity;
import com.cybersoft.entity.User;

public class UserCourseDto {
	private int id;
	private User user;
	private CourseEntity courseEntity;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CourseEntity getCourse() {
		return courseEntity;
	}
	public void setCourse(CourseEntity courseEntity) {
		this.courseEntity = courseEntity;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public UserCourseDto(int id, User user, CourseEntity courseEntity, int roleId) {
		super();
		this.id = id;
		this.user = user;
		this.courseEntity = courseEntity;
		this.roleId = roleId;
	}
	
	
	
	
	
}

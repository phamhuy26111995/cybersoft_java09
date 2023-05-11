package com.cybersoft.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	private Long id;
	@NotEmpty(message = "Vui lòng nhập tên")
	private String fullname;
	@NotEmpty(message = "Vui lòng nhập email")
	private String email;
	@NotEmpty(message = "Vui lòng nhập password")
	private String password;

	private String avatar;
	
	private String address;
	private String phone;
	
	private int roleId;
	private String roleDesc;

	private Set<CourseDto> courseList;
	
	public UserDto() {}
	
	public UserDto(Long id, String fullname, String email, String roleDesc) {

		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.roleDesc = roleDesc;
	}

	public UserDto(Long id, String fullname, String email, String roleDesc, int roleId) {
		this.roleId = roleId;
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.roleDesc = roleDesc;
	}

	public UserDto(Long id, String fullname, String email, String roleDesc, int roleId, Set<CourseDto> courseList) {
		this.roleId = roleId;
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.roleDesc = roleDesc;
		this.courseList = courseList;
	}
	
	public UserDto(Long id, String fullname, String email, String password, String avatar, int roleId) {

		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.roleId = roleId;
	}

	
	
	
	

	public UserDto(Long id, @NotEmpty(message = "Vui lòng nhập tên") String fullname,
			@NotEmpty(message = "Vui lòng nhập email") String email,
			@NotEmpty(message = "Vui lòng nhập password") String password, String avatar, String address, String phone, int roleId,
			String roleDesc) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.address = address;
		this.phone = phone;
		this.roleId = roleId;
		this.roleDesc = roleDesc;
	}






	public UserDto(Long id, @NotEmpty(message = "Vui lòng nhập tên") String fullname,
			@NotEmpty(message = "Vui lòng nhập email") String email) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
	}
	
	

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", fullname=" + fullname + ", email=" + email + ", password=" + password
				+ ", avatar=" + avatar + ", roleId=" + roleId + ", roleDesc=" + roleDesc + ", userCourses="
				+  "]";
	}


	
}

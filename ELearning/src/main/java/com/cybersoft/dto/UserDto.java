package com.cybersoft.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private Long id;
	@NotEmpty(message = "Vui lòng nhập tên")
	private String fullname;
	@NotEmpty(message = "Vui lòng nhập email")
	private String email;
	@NotEmpty(message = "Vui lòng nhập password")
	private String password;
	@NotEmpty(message = "Vui lòng nhập avatar")
	private String avatar;
	
	private String address;
	private String phone;
	
	private int roleId;
	private String roleDesc;
	
	public UserDto() {}
	
	public UserDto(Long id, String fullname, String email, String roleDesc) {

		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.roleDesc = roleDesc;
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
			@NotEmpty(message = "Vui lòng nhập password") String password,
			@NotEmpty(message = "Vui lòng nLonghập avatar") String avatar, String address, String phone, int roleId,
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

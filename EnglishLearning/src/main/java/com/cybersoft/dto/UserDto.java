package com.cybersoft.dto;

public class UserDto {
	private int id;
	private String name;
	private String password;
	private String username;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public UserDto(int id, String name, String password, String username) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.username = username;
	}
	
	public UserDto() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", password=" + password + ", username=" + username + "]";
	}
	
	
}

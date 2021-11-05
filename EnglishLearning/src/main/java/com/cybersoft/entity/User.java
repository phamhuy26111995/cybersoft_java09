package com.cybersoft.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	private String name;
	private String password;
	private String username;
	@Column(name = "role_id")
	private int role_id;
	
	@ManyToOne
	@JoinColumn(name = "role_id",insertable = false, updatable = false) //Quan hệ nhiều một với Role
	private Role role;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) //Quan hệ một nhiều với Vocabulary
	private Set<Vocabulary> vocabularies; 
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) //Quan hệ một nhiều với user_category
	private Set<UserCategory> userCategory;
	
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
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public User(int id, String name, int role_id) {
		super();
		this.id = id;
		this.name = name;
		this.role_id = role_id;
	}
	public User() {
		
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", role_id=" + role_id + "]";
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
	
	
}

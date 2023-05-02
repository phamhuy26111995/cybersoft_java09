package com.cybersoft.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	private String password;
	private String fullname;
	private String avatar;
	private String address;
	private String phone;
	
	@Column(name = "role_id")
	private int roleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Role role;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_course" ,
			joinColumns = {
			@JoinColumn(name = "user_id" , referencedColumnName = "id")
			},inverseJoinColumns = {
			@JoinColumn(name = "course_id", referencedColumnName = "id")
	})
	private Set<Course> userCourse;
	

	public User() {}

	
	
	public User(Long id, String email, String password, String fullname, String avatar, String address, String phone,
			int roleId, Role role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.address = address;
		this.phone = phone;
		this.roleId = roleId;
		this.role = role;
	}



	
}

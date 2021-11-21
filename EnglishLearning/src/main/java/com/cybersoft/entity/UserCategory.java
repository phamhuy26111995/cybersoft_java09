package com.cybersoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_category")
public class UserCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_category_id")
	private int id;
	
	@Column(name = "user_id")
	private int user_id;
	
	@Column(name = "cate_id")
	private int cate_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "cate_id", insertable = false, updatable = false)
	private Category category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	
	public UserCategory(int id, int user_id, int cate_id) {
		
		this.id = id;
		this.user_id = user_id;
		this.cate_id = cate_id;
	}
	
public UserCategory(int user_id, int cate_id) {
		
		
		this.user_id = user_id;
		this.cate_id = cate_id;
	}
	
	public UserCategory() {
		
	}
	@Override
	public String toString() {
		return "UserCategory [id=" + id + ", user_id=" + user_id + ", cate_id=" + cate_id + "]";
	}
	
	
	
}

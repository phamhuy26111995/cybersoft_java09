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
@Table(name = "vocabulary")
public class Vocabulary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vol_id")
	private int id;
	private String vietnamese;
	private String english;
	private String type;
	
	@Column(name = "cate_id")
	private int cate_id;
	@Column(name = "user_id")
	private int user_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "cate_id", insertable = false, updatable = false)
	private Category cate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVietnamese() {
		return vietnamese;
	}
	public void setVietnamese(String vietnamese) {
		this.vietnamese = vietnamese;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Vocabulary(int id, String vietnamese, String english, String type, int cate_id, int user_id) {
		super();
		this.id = id;
		this.vietnamese = vietnamese;
		this.english = english;
		this.type = type;
		this.cate_id = cate_id;
		this.user_id = user_id;
	}
	public Vocabulary() {
		
	}
	@Override
	public String toString() {
		return "Vocabulary [id=" + id + ", vietnamese=" + vietnamese + ", english=" + english + ", type=" + type
				+ ", cate_id=" + cate_id + ", user_id=" + user_id + "]";
	}
	
	
	
	
}

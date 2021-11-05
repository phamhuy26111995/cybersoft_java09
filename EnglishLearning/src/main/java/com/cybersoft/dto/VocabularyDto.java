package com.cybersoft.dto;


public class VocabularyDto {
	private int id;
	private String vietnamese;
	private String english;
	private String type;
	private int cate_id;
	private int user_id;
	public VocabularyDto(int id2, String vietnamese2, String english2, String type2, int user_id2, int cate_id2) {
		this.id = id2;
		this.vietnamese = vietnamese2;
		this.english = english2;
		this.type = type2;
		this.user_id = user_id2;
		this.cate_id = cate_id2;
	}
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
	
	
	public VocabularyDto() {
		
	}
	@Override
	public String toString() {
		return "VocabularyDto [id=" + id + ", vietnamese=" + vietnamese + ", english=" + english + ", type=" + type
				+ ", cate_id=" + cate_id + ", user_id=" + user_id + "]";
	}
	
	
}

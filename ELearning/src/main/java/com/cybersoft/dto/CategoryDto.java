package com.cybersoft.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryDto {
	private int id;
	@NotEmpty(message = "Vui lòng không được để trống title")
	private String title;
	private String icon;

	public CategoryDto(int id, String title, String icon) {
		this.id = id;
		this.title = title;
		this.icon = icon;
	}
	public CategoryDto() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", title=" + title + ", icon=" + icon + "]";
	}

}

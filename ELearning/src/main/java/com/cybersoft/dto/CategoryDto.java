package com.cybersoft.dto;

import com.cybersoft.common.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoryDto extends BaseDTO {
	private int id;
	private String title;
	private String icon;

	public CategoryDto(int id, String title, String icon) {
		this.id = id;
		this.title = title;
		this.icon = icon;
	}
	public CategoryDto() {
	}
	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", title=" + title + ", icon=" + icon + "]";
	}

}

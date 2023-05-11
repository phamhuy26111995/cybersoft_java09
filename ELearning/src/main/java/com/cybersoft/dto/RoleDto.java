package com.cybersoft.dto;

import com.cybersoft.common.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RoleDto extends BaseDTO {
	private int id;

	private String name;
	
	private String description;
	
	public RoleDto() {}
	
	public RoleDto(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

}
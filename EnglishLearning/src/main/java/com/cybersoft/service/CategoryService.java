package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.CategoryDto;

public interface CategoryService {
	List<CategoryDto> getAllCategory();
	void add(CategoryDto dto);
	CategoryDto getById(int id);
	void edit(CategoryDto dto);
	void delete(int id);
}

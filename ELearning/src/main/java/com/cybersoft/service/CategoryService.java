package com.cybersoft.service;

import java.util.List;

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.dto.RoleDto;
import org.springframework.web.multipart.MultipartFile;

public interface CategoryService {
	List<CategoryDto> getAll();
	CategoryDto getById(int id);
	CategoryDto save(CategoryDto dto, MultipartFile file);
	CategoryDto edit(CategoryDto dto, MultipartFile file);
	void delete(int id);
	
}

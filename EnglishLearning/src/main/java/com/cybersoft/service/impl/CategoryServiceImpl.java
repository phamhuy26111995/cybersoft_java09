package com.cybersoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.entity.Category;
import com.cybersoft.repository.CategoryRepository;
import com.cybersoft.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	public List<CategoryDto> getAllCategory() {
		List<CategoryDto> dtos = new ArrayList<CategoryDto>();
		
		for(Category entity : categoryRepository.listAllCategoryByUser()) {
			CategoryDto dto = new CategoryDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	public CategoryDto getById(int id) {
		Category entity = categoryRepository.findById(id);
		CategoryDto dto = new CategoryDto(entity.getId(), entity.getName());
		
		return dto;
	}
	
	public void add(CategoryDto dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		categoryRepository.save(entity);
	}
	
	public void edit(CategoryDto dto) {
		Category entity = categoryRepository.findById(dto.getId());
		if(entity != null) {
			entity.setName(dto.getName());
			categoryRepository.edit(entity);
		}
		
	}
	
	public void delete(int id) {
		categoryRepository.delete(id);
		
	}
	

}

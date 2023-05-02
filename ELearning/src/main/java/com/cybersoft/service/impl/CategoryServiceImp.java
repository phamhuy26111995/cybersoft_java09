package com.cybersoft.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.entity.Category;
import com.cybersoft.entity.User;
import com.cybersoft.repository.CategoryRepository;
import com.cybersoft.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
	private CategoryRepository categoryRepository;

	public CategoryServiceImp(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	//Lấy toàn bộ danh sách category
	@Override
	public List<CategoryDto> getAll() {
		List<CategoryDto> dtos = new ArrayList<CategoryDto>();
		try {
			List<Category> entities = categoryRepository.findAll();
			for(Category entity : entities) {
				CategoryDto dto = new CategoryDto();
				dto.setId(entity.getId());
				dto.setIcon(entity.getIcon());
				dto.setTitle(entity.getTitle());
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	//Lấy category theo id
	@Override
	public CategoryDto getById(int id) {
		Category entity = categoryRepository.findById(id).get();

		return new CategoryDto(entity.getId(),entity.getIcon(),entity.getTitle());
	}

	//Thêm mới một category
	@Override
	public void save(CategoryDto dto) {
		Category entity = createNew(dto);
		categoryRepository.save(entity);
	}

	private Category createNew(CategoryDto dto) {
		Category entity = new Category();
		entity.setIcon(dto.getIcon());
		entity.setTitle(dto.getTitle());

		return entity;
	}

	//Sửa một category
	@Override
	public void edit(CategoryDto dto) {
		Category entity = update(dto);
		categoryRepository.save(entity);
	}

	private Category update(CategoryDto dto) {
		Category entity = categoryRepository.findById(dto.getId()).get();

		if(dto.getTitle() != null && !dto.getTitle().equalsIgnoreCase("")) {
			entity.setTitle(dto.getTitle());
		}
		if(dto.getIcon() != null && !dto.getIcon().isEmpty()) {
			entity.setIcon(dto.getIcon());;
		}

		return entity;
	}



	@Override
	public void delete(int id) {
		categoryRepository.deleteById(id);
	}


}

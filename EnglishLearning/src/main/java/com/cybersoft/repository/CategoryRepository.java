package com.cybersoft.repository;

import java.util.List;

import com.cybersoft.dto.UserDetailsDto;
import com.cybersoft.entity.Category;

public interface CategoryRepository {
	List<Category> listAllCategory();
	Category findById(int id);
	Integer findLastId();
	void save(Category entity);
	void edit(Category entity);
	void delete(int id);
	
	List<Category> listAllCategoryByUser();
}

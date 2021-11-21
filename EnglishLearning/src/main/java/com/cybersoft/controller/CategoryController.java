package com.cybersoft.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cybersoft.common.CurrentUser;
import com.cybersoft.dto.CategoryDto;
import com.cybersoft.entity.Category;
import com.cybersoft.entity.User;
import com.cybersoft.entity.UserCategory;
import com.cybersoft.repository.CategoryRepository;
import com.cybersoft.repository.UserCategoryRepository;
import com.cybersoft.service.CategoryService;

@Controller
@RequestMapping(value= {"/","/category"})
public class CategoryController {
	
	private CategoryService categoryService;
	private UserCategoryRepository userCategoryRepository;
	private CategoryRepository categoryRepository;
	public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository,
			UserCategoryRepository userCategoryRepository) {
		this.categoryService = categoryService;
		this.categoryRepository = categoryRepository;
		this.userCategoryRepository = userCategoryRepository;
	}
	

	
	@RequestMapping("")
	public String index(Model model) {
		List<CategoryDto> dtos = categoryService.getAllCategory();
		
		model.addAttribute("dtos", dtos);
	
			
		
		
		return "category/category-list";
	}
	
	@GetMapping("/add")
	public String add() {
		
		return "category/category-add";
	}
	
	
	@GetMapping("/edit")
	public String edit(@RequestParam("id") int id, Model model) {
		CategoryDto dto = categoryService.getById(id);
		model.addAttribute("dto", dto);
		return "category/category-edit";
	}
	
	@PostMapping("/edit")
	public String edit(@RequestParam("name") String name, @RequestParam("id") int id) {
		CategoryDto dto = new CategoryDto(id, name);
		categoryService.edit(dto);
		return "redirect:/category";
	}
	
	
	
	@PostMapping("/add")
	public String add(@RequestParam("name") String name) {
		int id = categoryRepository.findLastId() + 1;
		int currenUserId = CurrentUser.getPrincipal().getId();
		
		
		
		CategoryDto dto = new CategoryDto();
		dto.setName(name);
		categoryService.add(dto);
		
		System.out.println("Thêm category thành công");
		
		UserCategory userCategory = new UserCategory(currenUserId, id);
		userCategoryRepository.save(userCategory);
		System.out.println("Thêm user Category thành công");
		
		return "redirect:/category";
	}
	
	@GetMapping("{id}")
	public String delete(@PathVariable int id) {
		categoryService.delete(id);
		
		return "redirect:/category";
	}
	
	@GetMapping("/api/category")
	@ResponseBody
	public Object listCateByUser() {
		Integer entity = categoryRepository.findLastId();
		
		
		
		return entity;
	}
	
}

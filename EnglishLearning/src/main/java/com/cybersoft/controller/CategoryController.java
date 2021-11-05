package com.cybersoft.controller;

import java.security.Principal;
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

import com.cybersoft.dto.CategoryDto;
import com.cybersoft.entity.Category;
import com.cybersoft.entity.User;
import com.cybersoft.repository.CategoryRepository;
import com.cybersoft.service.CategoryService;

@Controller
@RequestMapping(value= {"/","/category"})
public class CategoryController {
	
	private CategoryService categoryService;
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
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
		CategoryDto dto = new CategoryDto();
		dto.setName(name);
		categoryService.add(dto);
		
		return "redirect:/category";
	}
	
	@GetMapping("{id}")
	public String delete(@PathVariable int id) {
		categoryService.delete(id);
		
		return "redirect:/category";
	}
	
	
}
